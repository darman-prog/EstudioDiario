package com.example.myapplicationDiegoMeza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationDiegoMeza.ui.theme.ControladorDeTareas_appTheme
import com.example.myapplicationDiegoMeza.RegistroScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var usuarioViewModelval = remember { usuarioViewModel() }
            NavHost(
                navController = navController,
                startDestination = "register"
            ){

                composable("register") {
                    RegistroScreen(navController = navController , userViewModel = usuarioViewModelval)
                }
                composable("controlador") {
                    PantallaGestorTareas(
                        usuarioViewModelval,
                        navController = navController
                    )
                }

            }


        }
    }
}



