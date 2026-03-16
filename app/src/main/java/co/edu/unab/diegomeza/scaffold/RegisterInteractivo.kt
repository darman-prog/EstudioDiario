package co.edu.unab.diegomeza.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun RegisterScreen(){
    var nombrePaciente by remember() { mutableStateOf("") }
    var sintomasPaciente by remember() { mutableStateOf("") }
    var fechaIngresoPaciente by remember() { mutableStateOf("") }
    var cedulaPaciente by remember() { mutableStateOf(0) }
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
                onCedulaChange = { cedulaPaciente = it }
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
                    style = TextStyle(fontSize = 24.sp , fontWeight = FontWeight.Bold
                ) )
            },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF00BCD4),
            titleContentColor = Color(0xFFFFFFFF)
        )

    )
}

@Composable
fun BodyRegisterScreen(nombre: String, onNombreChange: (String) -> Unit
                       , sintomas: String, onSintomasChange: (String) -> Unit, cedula: Int, onCedulaChange: (Int) -> Unit
                       , fecha: String
                       ,onFechaChange: (String) -> Unit){

        Column() {


            Text("Nombre Paciente",
                style = TextStyle(fontSize = 24.sp , fontWeight = FontWeight.Bold) )

            OutlinedTextField(value = nombre,
                            onValueChange = { }
            )

            Text("Sintomas",
                style = TextStyle(fontSize = 24.sp , fontWeight = FontWeight.Bold) )

            OutlinedTextField(value = sintomas,
                            onValueChange = { }

            )

            Text("Fecha Ingreso",
                style = TextStyle(fontSize = 24.sp , fontWeight = FontWeight.Bold) )

            OutlinedTextField(value = sintomas,
                onValueChange = { }

            )
            Text("Cedula",
                style = TextStyle(fontSize = 24.sp , fontWeight = FontWeight.Bold) )

            OutlinedTextField(value = sintomas,
                onValueChange = { }

            )
        }

}