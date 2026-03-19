package com.example.myapplicationDiegoMeza

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistroScreen() {
    // 1. Estado para los inputs (¡Vital en Compose!)
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    // 2. Degradado más suave (Solo 3 tonos)
    val coloresFondo = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF050B18), // Casi Negro
            Color(0xFF0A1931), // Media noche
            Color(0xFF122C5A)  // Profundo
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(coloresFondo)
            .padding(24.dp), // Más aire a los lados
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen con un poco de elevación visual
        AsyncImage(
            model = "https://cdn-icons-png.flaticon.com/512/4345/4345573.png",
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Imagen Tecnológica de Fondo",
            modifier = Modifier
                .size(200.dp) // Tamaño de la imagen
                .clip(RoundedCornerShape(16.dp)) // Bordes redondeados
                .border(2.dp, Color(0xFF00FBFF), RoundedCornerShape(16.dp)), // ¡Borde Neón!
            contentScale = ContentScale.Crop // Para que la imagen llene el cuadro sin deformarse
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Crear Cuenta",
            fontSize = 32.sp,
            color = Color(0xFF00FBFF), // Título en Neón
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo: Nombre
        Text("Nombre de Usuario", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            placeholder = { Text("Ej: Diego Meza") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo: Correo
        Text("Correo Electrónico", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
        TextField(
            value = correo,
            onValueChange = { correo = it },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            placeholder = { Text("correo@ejemplo.com") }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de Registro (Aquí usamos el azul neón)
        Button(
            onClick = { /* Acción */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B4D8))
        ) {
            Text("EMPEZAR A CONTROLAR", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}