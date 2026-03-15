package co.edu.unab.diegomeza.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable()
fun CalculadoraApp(){
    var numeroActual by remember { mutableStateOf("0") }
    var numeroAnterior by remember { mutableStateOf("") }
    var operacionPendiente by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopBarCalculator()
        }

    ) {
        innerPadding ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .padding(28.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp)

        ) {

            ResultsScreen(numeroActual,modifier = Modifier.weight(1f))

            Botonera(
                onAccion = { simbolo ->
                    when {
                        simbolo in "0123456789" -> {
                            numeroActual = if (numeroActual == "0") simbolo else numeroActual + simbolo
                        }
                        simbolo == "C" -> {
                            numeroActual = "0"
                            numeroAnterior = ""
                            operacionPendiente = ""
                        }
                        simbolo == "=" -> {
                            val n1 = numeroAnterior.toDoubleOrNull() ?: 0.0
                            val n2 = numeroActual.toDoubleOrNull() ?: 0.0
                            numeroActual = realizarCalculo(n1, n2, operacionPendiente)
                        }
                        else -> { // Operaciones (+, -, *, /)
                            numeroAnterior = numeroActual
                            numeroActual = "0"
                            operacionPendiente = simbolo
                        }
                    }
                }
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCalculator() {
    CenterAlignedTopAppBar(
        title = {
            Text("Calculadora Cientifica",
                style = MaterialTheme.typography.titleLarge, fontSize = 25.sp)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF2196F3),
            titleContentColor = Color(0xFFF0EDF5)
        )


    )
}

@Composable
fun ResultsScreen(texto: String, modifier: Modifier = Modifier){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp),
        color = Color(0xFF6C6A6A),
        shape = RoundedCornerShape(28.dp),
        border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = Color.Cyan)
    ){
        Box(contentAlignment = Alignment.BottomEnd ,
            modifier = Modifier.padding(28.dp)
                )
        {
                Text(text = texto,
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                    maxLines = 1, modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun Botonera(onAccion: (String) -> Unit) {
    val filas = listOf(
        listOf("7", "8", "9", "/"),
        listOf("4", "5", "6", "*"),
        listOf("1", "2", "3", "-"),
        listOf("C", "0", "=", "+")
    )
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        filas.forEach { fila ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                fila.forEach { simbolo ->
                    BotonIndividual(
                        texto = simbolo,
                        modifier = Modifier.weight(1f),
                        onClick = { onAccion(simbolo) }
                    )
                }
            }
        }
    }
}

@Composable
fun BotonIndividual(texto: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val esOperador = texto in "+-*/="
    val esLimpiar = texto == "C"

    Surface(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f),
        shape = CircleShape,
        color = when {
            esOperador -> MaterialTheme.colorScheme.primary
            esLimpiar -> Color(0xFDDE1010)
            else -> MaterialTheme.colorScheme.surfaceVariant
        },
        shadowElevation = 6.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = texto,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = if (esOperador) Color.White else if (esLimpiar) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

fun realizarCalculo(n1: Double, n2: Double, op: String): String {
    val res = when (op) {
        "+" -> n1 + n2
        "-" -> n1 - n2
        "*" -> n1 * n2
        "/" -> if (n2 != 0.0) n1 / n2 else Double.NaN
        else -> n2
    }
    return if (res.isNaN()) "Error" else res.toString().removeSuffix(".0")
}