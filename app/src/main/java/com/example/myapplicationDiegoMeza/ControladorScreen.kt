package com.example.myapplicationDiegoMeza

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ──────────────────────────────────────────────
// Paleta de colores
// ──────────────────────────────────────────────
private val OxfordBlue   = Color(0xFF002147)
private val NavyAccent   = Color(0xFF0B3D7E)
private val SuccessGreen = Color(0xFF66BB6A)
private val SoftGreen    = Color(0xFFE8F5E9)
private val BackgroundWhite = Color(0xFFF9FAFB)
private val CardWhite    = Color(0xFFFFFFFF)
private val TextPrimary  = Color(0xFF1A1A2E)
private val TextSecondary = Color(0xFF6B7280)

private val gradientBorder = Brush.horizontalGradient(
    colors = listOf(Color(0xFF00B4D8), Color(0xFF00FBFF))
)

// ──────────────────────────────────────────────
// Modelo de datos
// ──────────────────────────────────────────────
data class Tarea(
    val id: Int,
    val nombre: String,
    var completada: Boolean = false
)

// ──────────────────────────────────────────────
// Pantalla principal
// ────────
// ──────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaGestorTareas(
    userViewModel: usuarioViewModel,
    navController: NavController = rememberNavController()
) {
    // ── Estado ──
    val tareas = remember {
        mutableStateListOf(
            Tarea(1, "Revisar documentación del proyecto"),
            Tarea(2, "Reunión con el equipo de diseño"),
            Tarea(3, "Actualizar dependencias del build"),
            Tarea(4, "Escribir pruebas unitarias"),
        )
    }

    var mostrarDialogo by remember { mutableStateOf(false) }
    var nuevaTareaTexto by remember { mutableStateOf("") }
    var contadorId by remember { mutableIntStateOf(10) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // ── Scaffold ──
    Scaffold(
        containerColor = BackgroundWhite,
        snackbarHost = { SnackbarHost(snackbarHostState) },

        // ── TopBar ──
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf( NavyAccent,OxfordBlue)
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Gestor de Tareas",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 13.sp,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "¡Bienvenido, ${userViewModel.usuario}!",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                ResumenTareas(tareas)
            }
        },

        // ── FAB: Agregar tarea ──
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarDialogo = true },
                containerColor = NavyAccent,
                contentColor = Color.White,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(6.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // ── Botón eliminar tarea completada aleatoria ──
            BotonEliminarAleatoria(tareas, snackbarHostState, scope)

            Spacer(modifier = Modifier.height(16.dp))

            // ── Lista de tareas ──
            if (tareas.isEmpty()) {
                ListaVaciaPlaceholder()
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    itemsIndexed(tareas, key = { _, t -> t.id }) { _, tarea ->
                        ItemTarea(
                            tarea = tarea,
                            onCheckedChange = { marcada ->
                                val idx = tareas.indexOfFirst { it.id == tarea.id }
                                if (idx != -1) tareas[idx] = tareas[idx].copy(completada = marcada)
                            },
                            onEliminar = { tareas.removeIf { it.id == tarea.id } }
                        )
                    }
                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
        }
    }

    // ── Diálogo: Nueva tarea ──
    if (mostrarDialogo) {
        DialogoNuevaTarea(
            texto = nuevaTareaTexto,
            onTextoChange = { nuevaTareaTexto = it },
            onConfirmar = {
                if (nuevaTareaTexto.isNotBlank()) {
                    tareas.add(Tarea(contadorId++, nuevaTareaTexto.trim()))
                    nuevaTareaTexto = ""
                    mostrarDialogo = false
                }
            },
            onDescartar = {
                nuevaTareaTexto = ""
                mostrarDialogo = false
            }
        )
    }
}

// ──────────────────────────────────────────────
// Chips de resumen (completadas / pendientes)
// ──────────────────────────────────────────────
@Composable
private fun ResumenTareas(tareas: SnapshotStateList<Tarea>) {
    val completadas = tareas.count { it.completada }
    val pendientes  = tareas.count { !it.completada }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ChipResumen("$completadas completadas", SuccessGreen)
        ChipResumen("$pendientes pendientes",  Color.White.copy(alpha = 0.3f))
    }
}

@Composable
private fun ChipResumen(texto: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color.copy(alpha = 0.45f))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(texto, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

// ──────────────────────────────────────────────
// Botón eliminar tarea completada aleatoria
// ──────────────────────────────────────────────
@Composable
private fun BotonEliminarAleatoria(
    tareas: SnapshotStateList<Tarea>,
    snackbarHostState: SnackbarHostState,
    scope: kotlinx.coroutines.CoroutineScope
) {
    OutlinedButton(
        onClick = {
            val completadas = tareas.filter { it.completada }
            if (completadas.isNotEmpty()) {
                val elegida = completadas.random()
                tareas.removeIf { it.id == elegida.id }
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "🗑️ \"${elegida.nombre}\" eliminada",
                        duration = SnackbarDuration.Short
                    )
                }
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "No hay tareas completadas para eliminar",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFD32F2F)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD32F2F).copy(alpha = 0.5f))
    ) {
        Icon(Icons.Filled.Delete, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Eliminar tarea completada (aleatoria)", fontSize = 13.sp)
    }
}

// ──────────────────────────────────────────────
// Ítem de tarea individual
// ──────────────────────────────────────────────
@Composable
fun ItemTarea(
    tarea: Tarea,
    onCheckedChange: (Boolean) -> Unit,
    onEliminar: () -> Unit
) {
    var visible by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = visible,
        exit = shrinkVertically(animationSpec = tween(300)) + fadeOut(tween(300))
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            border = if (!tarea.completada) BorderStroke(2.dp, gradientBorder) else null,
            colors = CardDefaults.cardColors(
                containerColor = if (tarea.completada) SoftGreen else CardWhite
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = if (tarea.completada) 0.dp else 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Checkbox
                Checkbox(
                    checked = tarea.completada,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = SuccessGreen,
                        uncheckedColor = NavyAccent
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))

                // Nombre de tarea
                Text(
                    text = tarea.nombre,
                    modifier = Modifier.weight(1f),
                    color = if (tarea.completada) TextSecondary else TextPrimary,
                    fontWeight = if (tarea.completada) FontWeight.Normal else FontWeight.Medium,
                    fontSize = 15.sp,
                    textDecoration = if (tarea.completada) TextDecoration.LineThrough else TextDecoration.None
                )

                // Botón eliminar individual
                IconButton(
                    onClick = {
                        scope.launch {
                            visible = false
                            delay(310)
                            onEliminar()
                        }
                    }
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Eliminar",
                        tint = Color(0xFFBDBDBD),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

// ──────────────────────────────────────────────
// Placeholder lista vacía
// ──────────────────────────────────────────────
@Composable
private fun ListaVaciaPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("✅", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "¡Sin tareas pendientes!",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Text(
                "Agrega una nueva tarea con el botón +",
                fontSize = 13.sp,
                color = TextSecondary
            )
        }
    }
}

// ──────────────────────────────────────────────
// Diálogo nueva tarea
// ──────────────────────────────────────────────
@Composable
private fun DialogoNuevaTarea(
    texto: String,
    onTextoChange: (String) -> Unit,
    onConfirmar: () -> Unit,
    onDescartar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDescartar,
        shape = RoundedCornerShape(20.dp),
        containerColor = CardWhite,
        title = {
            Text(
                "Nueva Tarea",
                fontWeight = FontWeight.Bold,
                color = OxfordBlue,
                fontSize = 20.sp
            )
        },
        text = {
            OutlinedTextField(
                value = texto,
                onValueChange = onTextoChange,
                placeholder = { Text("Escribe el nombre de la tarea…", color = TextSecondary) },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NavyAccent,
                    cursorColor = NavyAccent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirmar,
                colors = ButtonDefaults.buttonColors(containerColor = NavyAccent),
                shape = RoundedCornerShape(10.dp)
            ) { Text("Agregar") }
        },
        dismissButton = {
            TextButton(onClick = onDescartar) {
                Text("Cancelar", color = TextSecondary)
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaGestorTareasPreview() {
    var usuarioViewModelval = remember { usuarioViewModel() }

    PantallaGestorTareas(userViewModel = usuarioViewModelval)
}