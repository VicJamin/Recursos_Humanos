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
fun PantallaLogin(
    viewModel: UsuarioViewModel,
    onLoginExitoso: () -> Unit,
    onCrearCuenta: () -> Unit
) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val loginExitoso by viewModel.loginExitoso.collectAsState()
    val loginIncorrecto by viewModel.loginIncorrecto.collectAsState()

    LaunchedEffect(loginExitoso) {
        if (loginExitoso) {
            onLoginExitoso()
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
            text = "RRHH SYSTEM",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Recursos Humanos - Iniciar Sesión",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

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

        Spacer(modifier = Modifier.height(12.dp))

        if (loginIncorrecto) {
            Text(
                text = "Usuario o contraseña incorrectos.",
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                if (usuario.isNotBlank() && contrasena.isNotBlank()) {
                    viewModel.iniciarSesion(
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
            Text(text = "Iniciar sesión")
        }



        TextButton(
            onClick = onCrearCuenta,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color(0xFF142B4A)
            )
        ) {
            Text(text = "¿No tienes cuenta? Crear una")
        }
    }
}
