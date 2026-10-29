package com.example.rrhh.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.rrhh.model.Permiso
import com.example.rrhh.viewmodel.PermisoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPermiso(
    viewModel: PermisoViewModel,
    onVolver: () -> Unit
) {
    var idEmpleado by remember { mutableStateOf("") }
    var horasStr by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("") }
    var esConGoce by remember { mutableStateOf(true) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Permiso Personal") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF142B4A),
                    titleContentColor = Color.White
                )

            )
        }
    )

    { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = idEmpleado,
                onValueChange = { idEmpleado = it },
                label = { Text("ID del Empleado") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    RadioButton(selected = esConGoce, onClick = { esConGoce = true })
                    Text("Con Goce (40h)", modifier = Modifier.padding(top = 12.dp))
                }
                Row {
                    RadioButton(selected = !esConGoce, onClick = { esConGoce = false })
                    Text("Sin Goce (480h)", modifier = Modifier.padding(top = 12.dp))
                }
            }

            OutlinedTextField(
                value = horasStr,
                onValueChange = { horasStr = it },
                label = { Text("Cantidad de Horas") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = motivo,
                onValueChange = { motivo = it },
                label = { Text("Motivo") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val horas = horasStr.toIntOrNull() ?: 0
                    if (idEmpleado.isBlank() || horas <= 0) {
                        Toast.makeText(context, "Ingresa un ID y horas validas", Toast.LENGTH_SHORT).show()
                    } else {
                        val permiso = Permiso(
                            idEmpleado = idEmpleado,
                            tipoPermiso = if (esConGoce) "CON_GOCE" else "SIN_GOCE",
                            horasSolicitadas = horas,
                            motivo = motivo,
                            fecha = "2026-10-03"
                        )
                        viewModel.registrarPermiso(permiso) { exito ->
                            if (exito) {
                                Toast.makeText(context, "Permiso procesado exitosamente", Toast.LENGTH_SHORT).show()
                                onVolver()
                            } else {
                                Toast.makeText(context, "Error: Saldo insuficiente o ID no existe", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Procesar Permiso")
            }
        }
    }
}
