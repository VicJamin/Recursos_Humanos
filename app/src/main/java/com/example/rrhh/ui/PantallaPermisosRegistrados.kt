package com.example.rrhh.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rrhh.model.Permiso
import com.example.rrhh.ui.componentes.TarjetaPermiso
import com.example.rrhh.viewmodel.PermisoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPermisosRegistrados(
    viewModel: PermisoViewModel,
    onVolver: () -> Unit,
    onNavegarARegistrar: () -> Unit
) {
    val listaPermisos by viewModel.listaPermisos.collectAsState()

    Scaffold(
        containerColor = Color(0xFFF4F6F9),
        topBar = {
            TopAppBar(
                title = { Text("Permisos Registrados") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF142B4A),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavegarARegistrar) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Registrar Permiso"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (listaPermisos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay permisos registrados actualmente.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listaPermisos) { permiso ->
                        TarjetaPermiso(permiso = permiso)
                    }
                }
            }
        }
    }
}
