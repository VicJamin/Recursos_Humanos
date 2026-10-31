package com.example.rrhh.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rrhh.viewmodel.UsuarioViewModel

@Composable
fun PantallaRegistro(
    viewModel: UsuarioViewModel,
    onRegistroExitoso: () -> Unit,
    onRegresar: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val registroExitoso by viewModel.registroExitoso.collectAsState()
    val usuarioExiste by viewModel.usuarioExiste.collectAsState()

    LaunchedEffect(registroExitoso) {
        if (registroExitoso) {
            onRegistroExitoso()
            viewModel.reiniciarEstado()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)

    ) {
        Spacer(modifier = Modifier.height(250.dp))

        Text(
            text = "Crear Cuenta",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,

        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF142B4A),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Gray,
                focusedLabelColor = Color(0xFF142B4A),
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray

            )

            )


        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF142B4A),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Gray,
                focusedLabelColor = Color(0xFF142B4A),
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray

            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF142B4A),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Gray,
                focusedLabelColor = Color(0xFF142B4A),
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (usuarioExiste) {
            Text(
                text = "El usuario ya existe. Elige otro nombre de usuario.",
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Button(
            onClick = {
                if (nombre.isNotBlank() && usuario.isNotBlank() && contrasena.isNotBlank()) {
                    viewModel.registrarUsuario(
                        nombre = nombre.trim(),
                        usuario = usuario.trim(),
                        contrasena = contrasena
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3B82F6),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Crear cuenta")
        }



        TextButton(
            onClick = onRegresar,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color(0xFF142B4A)
            )
        ) {
            Text(text = "Ya tengo una cuenta")
        }
    }
}
