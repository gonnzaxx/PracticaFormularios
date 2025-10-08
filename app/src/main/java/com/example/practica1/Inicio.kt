package com.example.practica1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica1.ui.theme.Practica1Theme

private const val PREFS = "sharedPrefs"
private const val CLAVE_USUARIO = "usuario"
private const val CLAVE_EMAIL = "email"
private const val CLAVE_CONTRASENA = "contrasena"
class Inicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    var contexto1 = LocalContext.current

    val imagen1 = R.drawable.logoluisvives

    var campoUsuario by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var campoEmail by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var campoContrasena by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var mensajeError by remember {
        mutableStateOf("")
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){

        Text(
            text = "RESERVIVES",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0),
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = imagen1),
            contentDescription = "logo",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        OutlinedTextField(
            value = campoUsuario,
            onValueChange = {
                    x -> campoUsuario = x
            },
            label = {Text(text = "Usuario")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = campoContrasena,
            onValueChange = {
                    x -> campoContrasena = x
            },
            label = {Text(text = "Contraseña")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation('*')
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val prefs = contexto1.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

                //creamos variables que guarden el valor del usuario y la contraseña que tiene SharedPreferences para poder compararla
                val usuarioGuardado = prefs.getString(CLAVE_USUARIO, null)
                val contrasenaGuardada = prefs.getString(CLAVE_CONTRASENA, null)

                //creamos variables que guarden lo que el usuario ponga en el formulario para poder comparar
                val inputUsuario = campoUsuario.text.trim()
                val inputContrasena = campoContrasena.text

                when {
                    //si el usuario o contraseña no está en SharedPreferences, salta el error
                    usuarioGuardado.isNullOrEmpty() || contrasenaGuardada.isNullOrEmpty() -> {
                        mensajeError = "No hay ninguna cuenta registrada. Pulsa 'Registrarse'."
                    }
                    //si coincide lo que guarda SharedPreferences con lo que escribe el usuario, pasa a la activity Datos
                    inputUsuario == usuarioGuardado && inputContrasena == contrasenaGuardada -> {
                        mensajeError = ""
                        contexto1.startActivity(Intent(contexto1, Datos::class.java))
                    }
                    else -> {
                        mensajeError = "Usuario o contraseña incorrectos."
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 181, green = 36, blue = 106),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(55.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 10.dp
            )
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))


        Text(
            text = "¿No tienes cuenta?"
        )
        Text(
            text = "Registrarse",
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable() {
                val intent1 = Intent(contexto1, Registro::class.java)
                contexto1.startActivity(intent1)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Practica1Theme {
        Greeting2("Android")
    }
}