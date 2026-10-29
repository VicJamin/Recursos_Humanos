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
import com.example.rrhh.viewmodel.EmpleadoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarEmpleado(
    idEmpleado: String,
    viewModel: EmpleadoViewModel,
    onVolver: () -> Unit
) {
    val listaEmpleados by viewModel.empleados.collectAsState(initial = emptyList())
    val empleado = listaEmpleados.find { it.idEmpleado == idEmpleado }

    val context = LocalContext.current
    val areas = listOf("Servicios Generales", "Agentes del CAM", "Administracion", "Catastro")

    if (empleado != null) {
        var nombre by remember { mutableStateOf(empleado.nombreCompleto) }
        var puesto by remember { mutableStateOf(empleado.puesto) }
        var areaSeleccionada by remember { mutableStateOf(empleado.area) }
        var expandedArea by remember { mutableStateOf(false) }

        Scaffold(
            containerColor = Color(0xFFF4F6F9),
            topBar = {
                TopAppBar(
                    title = { Text("Editar Expediente: ${empleado.idEmpleado}") },
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


        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre Completo") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Cambiar Area (Reasignacion):")
                ExposedDropdownMenuBox(
                    expanded = expandedArea,
                    onExpandedChange = { expandedArea = !expandedArea }
                ) {
                    OutlinedTextField(
                        value = areaSeleccionada,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedArea) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedArea,
                        onDismissRequest = { expandedArea = false }
                    ) {
                        areas.forEach { area ->
                            DropdownMenuItem(
                                text = { Text(area) },
                                onClick = {
                                    areaSeleccionada = area
                                    expandedArea = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = puesto,
                    onValueChange = { puesto = it },
                    label = { Text("Puesto Especifico") },
                    modifier = Modifier.fillMaxWidth()
                )

                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Saldo de Horas Disponible:", style = MaterialTheme.typography.titleMedium)
                        Text("• Con Goce de Sueldo: ${empleado.horasConGoceDisp} / 40 hrs")
                        Text("• Sin Goce de Sueldo: ${empleado.horasSinGoceDisp} / 480 hrs")
                    }
                }

                Button(
                    onClick = {
                        val empActualizado = empleado.copy(
                            nombreCompleto = nombre,
                            area = areaSeleccionada,
                            puesto = puesto
                        )
                        viewModel.guardarEmpleado(empActualizado)
                        Toast.makeText(context, "Empleado reasignado a: $areaSeleccionada", Toast.LENGTH_SHORT).show()
                        onVolver()
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {
                    Text("Guardar Cambios")
                }
            }
        }
    }
}
