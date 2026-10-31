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
    val empleado by viewModel.obtenerEmpleadoPorIdFlow(idEmpleado).collectAsState(initial = null)

    val context = LocalContext.current
    val areas = listOf("Servicios Generales", "Agentes del CAM", "Administracion", "Catastro")

    if (empleado != null) {
        val emp = empleado!!
        var nombre by remember { mutableStateOf(emp.nombreCompleto) }
        var puesto by remember { mutableStateOf(emp.puesto) }
        var areaSeleccionada by remember { mutableStateOf(emp.area) }
        var expandedArea by remember { mutableStateOf(false) }

        Scaffold(
            containerColor = Color(0xFFF4F6F9),
            topBar = {
                TopAppBar(
                    title = { Text("Editar Expediente: ${emp.idEmpleado}") },
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
                        modifier = Modifier.menuAnchor().fillMaxWidth(),
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

                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF142B4A),
                        contentColor = Color.White
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Saldo de Horas Disponible:")
                        Text("• Con Goce de Sueldo: ${emp.horasConGoceDisp} / 40 hrs")
                        Text("• Sin Goce de Sueldo: ${emp.horasSinGoceDisp} / 480 hrs")
                    }
                }

                Button(
                    onClick = {
                        val empActualizado = emp.copy(
                            nombreCompleto = nombre,
                            area = areaSeleccionada,
                            puesto = puesto
                        )
                        viewModel.guardarEmpleado(empActualizado)
                        Toast.makeText(context, "Empleado reasignado a: $areaSeleccionada", Toast.LENGTH_SHORT).show()
                        onVolver()
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3B82F6),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Guardar Cambios")
                }
            }
        }
    }
}
