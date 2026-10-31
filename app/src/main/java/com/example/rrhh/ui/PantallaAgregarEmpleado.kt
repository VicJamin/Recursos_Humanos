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
import com.example.rrhh.model.Empleado
import com.example.rrhh.viewmodel.EmpleadoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAgregarEmpleado(
    viewModel: EmpleadoViewModel,
    onVolver: () -> Unit
) {
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var puesto by remember { mutableStateOf("") }

    val areas = listOf("Servicios Generales", "Agentes del CAM", "Administracion", "Catastro")
    var areaSeleccionada by remember { mutableStateOf(areas[0]) }
    var expandedArea by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        containerColor = Color(0xFFF4F6F9),
        topBar = {
            TopAppBar(
                title = { Text("Agregar Nuevo Empleado", color = Color.White)
                },
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
            Spacer(modifier = Modifier.height(4.dp))
            Text("Datos del Empleado:", color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("ID Empleado (ej: EMP-001)") },
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
            Spacer(modifier = Modifier.height(4.dp))
            Text("Area Asignada:", color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))

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

            Button(
                onClick = {
                    if (id.isBlank() || nombre.isBlank()) {
                        Toast.makeText(context, "Llena los campos requeridos", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.guardarEmpleado(
                            Empleado(
                                idEmpleado = id,
                                nombreCompleto = nombre,
                                area = areaSeleccionada,
                                puesto = puesto
                            )
                        )
                        Toast.makeText(context, "Empleado registrado", Toast.LENGTH_SHORT).show()
                        onVolver()
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B82F6),
                    contentColor = Color.Black
                )
            ) {
                Text("Guardar Empleado")
            }
        }
    }
}
