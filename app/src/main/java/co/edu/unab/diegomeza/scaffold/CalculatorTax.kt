package co.edu.unab.diegomeza.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun CalculatorTax() {


    var Entradacantidad by remember { mutableStateOf("") }
    var Entradaporcentaje by remember { mutableStateOf("") }
    val cantidad = Entradacantidad.toDoubleOrNull() ?: 0.0
    val porcentaje = Entradaporcentaje.toDoubleOrNull() ?: 0.0

    var resultado = cantidad * porcentaje / 100


    val resultadoRedondeado = kotlin.math.ceil(resultado)

    var estadoSwitch by remember { mutableStateOf(false) }

    val textoAMostrar= if (!estadoSwitch) "Total: $$resultado" else "Total Redondeado: $$resultadoRedondeado"

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(30.dp)
        ) {


            Text("Calculadora de impuestos", fontSize = 20.sp, color = Color.Blue)

            OutlinedTextField(
                value = Entradacantidad,
                onValueChange = {Entradacantidad = it},
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal
                )
                , modifier = Modifier.fillMaxWidth()

            )

            OutlinedTextField(
                value = Entradaporcentaje
                , onValueChange = {Entradaporcentaje = it}
                , label = { Text("Porcentaje") }
                , modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
                )


            )
            Spacer(modifier = Modifier.height(30.dp))

            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){

                Text("Redondear")
                Switch(

                    checked = estadoSwitch,
                    onCheckedChange = {
                            estadoSwitch = it
                    }
                )
            }

            Text(text = textoAMostrar , fontSize = 20.sp)

            


        }

    }


}