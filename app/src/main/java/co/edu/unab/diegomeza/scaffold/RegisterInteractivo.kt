package co.edu.unab.diegomeza.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun RegisterScreen(){
    var nombrePaciente by remember() { mutableStateOf("") }
    var sintomasPaciente by remember() { mutableStateOf("") }
    var fechaIngresoPaciente by remember() { mutableStateOf("") }
    var cedulaPaciente by remember() { mutableStateOf("") }
    var epsPaciente by remember() { mutableStateOf("") }
    Scaffold(

        topBar = {

            TopBarRegisterScreen()
        }

    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            BodyRegisterScreen(nombre = nombrePaciente,
                onNombreChange = { nombrePaciente = it },
                sintomas = sintomasPaciente,
                onSintomasChange = { sintomasPaciente = it },
                fecha = fechaIngresoPaciente,
                onFechaChange = { fechaIngresoPaciente = it },
                cedula = cedulaPaciente,
                onCedulaChange = { cedulaPaciente = it },
                eps = epsPaciente,
                onepsChange = { epsPaciente = it }

            )

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarRegisterScreen(){

    CenterAlignedTopAppBar(
            title = {

                Text("Registro Pacientes",
                    style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.Bold
                ) ,
                    modifier = Modifier.padding(12.dp))

            },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF00BCD4),
            titleContentColor = Color(0xE6167770)
        )

    )
}

@Composable
fun BodyRegisterScreen(nombre: String, onNombreChange: (String) -> Unit
                       , sintomas: String, onSintomasChange: (String) -> Unit, cedula: String, onCedulaChange: (String) -> Unit
                       , fecha: String
                       ,onFechaChange: (String) -> Unit,eps: String , onepsChange: (String) -> Unit){

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp), // antes era 8.dp
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // antes era 10.dp
            .background(Color(0xFF00796B), RoundedCornerShape(12.dp))
    ) {

        Spacer(modifier = Modifier.height(12.dp)) // antes era 5.dp

        Text(
            "Nombre Paciente",
            style = TextStyle(
                fontSize = 18.sp, // antes era 24.sp
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE9EFEE)
            )
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { onNombreChange(it) },
            label = { Text("Ingresa Nombre del paciente") },
            modifier = Modifier
                .fillMaxWidth() // agregado para que ocupe todo el ancho
                .padding(horizontal = 12.dp) // agregado para respirar del borde
                .background(color = Color(0xE1FFFFFF), shape = RoundedCornerShape(4.dp))
                .border(5.dp, color = Color(0xFF3F51B5), RoundedCornerShape(4.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
        )

        Spacer(modifier = Modifier.height(4.dp)) // antes era 5.dp

        Text(
            text = "Sintomas",
            style = TextStyle(
                fontSize = 18.sp, // antes era 24.sp
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE9EFEE)
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        OutlinedTextField(
            value = sintomas,
            onValueChange = { onSintomasChange(it) },
            label = { Text("Ingresa Sintomas del paciente") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .background(color = Color(0xE1FFFFFF), shape = RoundedCornerShape(4.dp))
                .border(5.dp, color = Color(0xFF3F51B5), RoundedCornerShape(4.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "Fecha Ingreso",
            style = TextStyle(
                fontSize = 18.sp, // antes era 24.sp
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE9EFEE) // agregado para que sea legible sobre el fondo verde
            )
        )

        OutlinedTextField(
            value = fecha,
            onValueChange = { onFechaChange(it) },
            label = { Text("Ingresa Fecha Ingreso") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .background(color = Color(0xE1FFFFFF), shape = RoundedCornerShape(4.dp))
                .border(5.dp, color = Color(0xFF3F51B5), RoundedCornerShape(4.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "Cedula",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE9EFEE) // agregado para legibilidad
            )
        )

        OutlinedTextField(
            value = cedula,
            onValueChange = { onCedulaChange(it) },
            label = { Text("Ingresa Cedula Paciente") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .background(color = Color(0xE1FFFFFF), shape = RoundedCornerShape(4.dp))
                .border(5.dp, color = Color(0xFF3F51B5), RoundedCornerShape(4.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "EPS",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE9EFEE) // agregado para legibilidad
            )
        )

        OutlinedTextField(
            value = eps,
            onValueChange = { onepsChange(it) },
            label = { Text("Ingresa EPS") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .background(color = Color(0xE1FFFFFF), shape = RoundedCornerShape(4.dp))
                .border(5.dp, color = Color(0xFF3F51B5), RoundedCornerShape(4.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
        )

        Spacer(modifier = Modifier.height(16.dp)) // antes era 5.dp, más aire al final
    }

}