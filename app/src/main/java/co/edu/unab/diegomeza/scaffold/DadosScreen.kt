package co.edu.unab.diegomeza.scaffold

import android.media.Image
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainScreenDado() {
    var image_id by remember { mutableStateOf(R.drawable.dice_1) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarDice()
        }
    )
    { innerpadding ->
        Column (modifier = Modifier.padding(innerpadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)

            {


                Image(painter = painterResource(image_id), contentDescription = null)


                Button(onClick = {

                    val number = (1..6).random()

                     image_id = when (number){
                        1 -> R.drawable.dice_1
                        2 -> R.drawable.dice_2
                        3 -> R.drawable.dice_3
                        4 -> R.drawable.dice_4
                        5 -> R.drawable.dice_5
                        6 -> R.drawable.dice_6
                        else -> R.drawable.dice_1
                    }

                    Toast
                        .makeText(context, "Lanzaste el dado", Toast.LENGTH_SHORT).show()



                }) {
                    Text("Lanzar Dado")

                }


            }

    }

}
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBarDice(){
        TopAppBar(

            title = {Text("Lanzar Dado App" , color = Color.White)},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.onSurfaceVariant
            )

        )
    }









