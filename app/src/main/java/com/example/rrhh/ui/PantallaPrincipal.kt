package com.example.rrhh.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rrhh.ui.componentes.BotonMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(
    onCerrarSesion: () -> Unit,
    onEmpleados: () -> Unit,
    /*
*/    onPermisos: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        containerColor = Color(0xFFF4F6F9),
        topBar = {
            TopAppBar(
                title = { Text("Recursos Humanos - Alcaldía", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF142B4A)
                )

            )

                 },


        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),

        ) {
            Text(
                text = "RRHH SYSTEM",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Sistema de Gestión de Recursos Humanos",
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Consulta Empleados y administra los Permisos otorgados a los mismos.",
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(150.dp))

            BotonMenu(
                texto = "Empleados",
                onClick = onEmpleados
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonMenu(
                texto = "Permisos",
                onClick = onPermisos
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonMenu(
                texto = "Cerrar Sesión",
                onClick = onCerrarSesion
            )


        }
    }
}
