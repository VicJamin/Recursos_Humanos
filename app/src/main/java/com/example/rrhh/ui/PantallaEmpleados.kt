package com.example.rrhh.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rrhh.ui.componentes.TarjetaEmpleado
import com.example.rrhh.viewmodel.EmpleadoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEmpleados(
    viewModel: EmpleadoViewModel,
    onVolver: () -> Unit,
    onVerDetalle: (String) -> Unit,
    onNavegarAAgregar: () -> Unit
) {
    val listaEmpleados by viewModel.empleados.collectAsState(initial = emptyList())
    val areaActual by viewModel.areaSeleccionada.collectAsState()
    val areas = listOf("Todos", "Servicios Generales", "Agentes del CAM", "Administración")

    Scaffold(
        containerColor = Color(0xFFF4F6F9),
        topBar = {
            TopAppBar(
                title = { Text("Directorio de Empleados") },
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavegarAAgregar,
                containerColor = Color(0xFF142B4A),
                contentColor = Color.White) {
                Text("+")
            }

        }

    ) { padding ->

        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            // Fila de Filtros de Área
            ScrollableTabRow(selectedTabIndex = areas.indexOf(areaActual).coerceAtLeast(0), Modifier.fillMaxWidth(),
                containerColor = Color(0xFFF4F6F9),
                contentColor = Color.Black) {
                areas.forEach { area ->
                    Tab(
                        selected = areaActual == area,
                        onClick = { viewModel.cambiarArea(area) },
                        text = { Text(area, color = Color.Black) },

                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de Empleados
            LazyColumn {
                items(listaEmpleados) { empleado ->
                    TarjetaEmpleado(
                        empleado = empleado,
                        onClick = { onVerDetalle(empleado.idEmpleado) }
                    )
                }
            }
        }
    }
}
