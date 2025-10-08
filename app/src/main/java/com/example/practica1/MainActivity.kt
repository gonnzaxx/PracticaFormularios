package com.example.practica1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practica1.ui.theme.Practica1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    //lista de imagenes
    val imagenes = listOf(
        R.drawable.lanzarocas,
        R.drawable.minipeka,
        R.drawable.valkiria,
        R.drawable.caballero,
        R.drawable.magohielo,
        R.drawable.megacaballero,
        R.drawable.barbaro
        )

    //variables
    var num by remember {
        mutableIntStateOf(0)
    }

    var indiceImagen by remember{
        mutableStateOf(0)
    }

    var mensaje by remember {
        mutableStateOf("")
    }

    var campoTexto by remember {
        mutableStateOf(TextFieldValue(""))
    }

    //Ponemos todo en una columna para que sea más facil posicionarlo

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = imagenes[indiceImagen]),
            contentDescription = "imagen $indiceImagen",
            modifier = Modifier.size(250.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))

        //Texto del dinero
        Text(
            text = "Dinero: $num"
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = campoTexto,
            onValueChange = {
                x -> campoTexto = x
            },
            label = {Text(text = "usuario")},
            placeholder = {Text(text= "Gonzalo")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField( //más bonito
            value = campoTexto,
            onValueChange = {
                    x -> campoTexto = x
            },
            label = {Text(text = "usuario")},
            placeholder = {Text(text= "Gonzalo")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Text(
            text = campoTexto.text
        )

        Spacer(modifier = Modifier.height(20.dp))


        //Boton al pulsar suma dinero o manda mensaje
        Button(
            onClick =
                {
                    if (indiceImagen < imagenes.size - 1) {
                        indiceImagen++
                        num += 50
                    } else {
                        mensaje = "Ya no te quedan más cartas para vender"
                    }
                }
        ){
            Text(text = "Vender carta")
        }

        //si la var mensaje no está vacia, le pone el texto
        if (mensaje.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = mensaje)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Practica1Theme {
        Greeting("Android")
    }
}