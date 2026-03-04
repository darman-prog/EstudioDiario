package co.edu.unab.diegomeza.scaffold

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun MainRegisterScreen(){

    Scaffold(
        topBar = {
            TopBarRegister()
        }


    ){ paddingValues ->

        Column (modifier = Modifier.
        padding(paddingValues).
        padding(16.dp)){
            Middlebodyscreen()
            BodyScreenRegister()

        }



    }
}

@Composable
fun Middlebodyscreen(){

    Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Row { val painter = painterResource(R.drawable.unab)
        Image(painter, contentDescription = null, modifier = Modifier.size(150.dp))

    }

        Text("Registro De usuario", modifier = Modifier.padding(top = 32.dp, bottom = 18.dp), fontSize = 24.sp, color = Color.DarkGray)


    }

}

@Composable
fun TopBarRegister(){

    Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.padding(top = 25.dp, bottom = 8.dp, start = 8.dp, end = 8.dp).size(25.dp))


}

@Composable
fun BodyScreenRegister(){
    Spacer(modifier = Modifier.height(32.dp))
    Column(verticalArrangement = Arrangement.Center){

        OutlinedTextField(value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = {Text("Nombre")
        },
        leadingIcon = {
            Icon(Icons.Default.AccountCircle, contentDescription = null)

        }
    )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Correo Electrónico")
            },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null)

            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Contraseña")
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)

            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Confirmar Contraseña")
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)

            }
        ) }

        Spacer(modifier = Modifier.height(28.dp))


        Button(onClick = {},modifier = Modifier.fillMaxWidth()) {
                Text("Registrarse",modifier = Modifier.padding(vertical = 8.dp).clip(
                    RoundedCornerShape(12.dp)
                ))

        }



}