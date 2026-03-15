package co.edu.unab.diegomeza.scaffold

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Preview(showBackground = true, showSystemUi = true)
@Composable()
fun CalculadoraApp(){

    Scaffold(
        topBar = {
            TopBarCalculator()
        }

    ) {
        innerPadding ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            ResultsScreen()
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

@Preview(showBackground = true , showSystemUi = true, name = "ResultadosPreview")
@Composable
fun ResultsScreen(){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp),
        color = Color(0xFF3F3E3E),
        shape = RoundedCornerShape(28.dp),
        border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = Color.Cyan)
    ){
        Box(contentAlignment = Alignment.BottomEnd ,
            modifier = Modifier.padding(28.dp)
                )
        {

                Text(text = "texto",
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.Cyan,
                    maxLines = 1, modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)


        }


    }
}

