package co.edu.unab.diegomeza.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainScreen() {

    Scaffold(

        bottomBar = {
            MyBottonBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {Icon(Icons.Default.Add, contentDescription = null) }
        }
        ,

        topBar = {
            MyTopBar()
        }

    ){ paddingValues ->

        Column (modifier = Modifier.
        padding(paddingValues).
        padding(16.dp)){

            Bodyscreen()

        }

    }


}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(){
    TopAppBar(

            title = {Text("Top app bar")},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.onSurfaceVariant
            )

    )


}


@Composable
fun MyBottonBar(){
    NavigationBar(){
        NavigationBarItem(
            selected = false,
            icon = {
                Icon(Icons.Default.Home, contentDescription = null)
            },
            onClick = {},
            label = {Text("Inicio")}
        )

        NavigationBarItem(
            selected = false,
            icon = {
                Icon(Icons.Default.Favorite, contentDescription = null)
            },
            onClick = {},
            label = {Text("Favoritos")}

        )

        NavigationBarItem(
            selected = false,
            icon = {
                Icon(Icons.Default.Settings, contentDescription = null)
            },
            onClick = {},
            label = {Text("Confi")}

        )


    }


}

@Composable
fun Bodyscreen(){


    Card(elevation = CardDefaults.elevatedCardElevation(10.dp),) {


        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp).fillMaxWidth()){


            Icon(
                imageVector = Icons.Default.AccountCircle, contentDescription = "account",
                modifier = Modifier.size(60.dp)
            )


            Text("Tarjeta de presentacion", modifier = Modifier.weight(1f))

            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)

        }

    }

    Spacer(modifier = Modifier.height(18.dp))
    TextField(value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = {Text("buscar o digite cualquier cosa")
        },
        trailingIcon = {
            Icon(Icons.Default.Close, contentDescription = null)

        }
    )
    OutlinedTextField(value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = {Text("buscar o digite cualquier cosa")
        },
        trailingIcon = {
            Icon(Icons.Default.Close, contentDescription = null)

        }
    )



}