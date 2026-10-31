package com.example.rrhh.ui


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rrhh.model.Empleado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalleEmpleado(
    empleado: Empleado,
    onRegresar: () -> Unit,
    navController: NavHostController,
    onEditar: (String) -> Unit,
    onEliminar: (Empleado) -> Unit
) {
    val context = LocalContext.current
    var mostrarDiálogoEliminar by remember { mutableStateOf(false) }

    // Manejo del mensaje emergente devuelto desde PantallaEditarEmpleado
    val mensajeConfirmación = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<String?>("mensaje", null)
        ?.collectAsState()

    LaunchedEffect(mensajeConfirmación?.value) {
        mensajeConfirmación?.value?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            // Limpiamos el mensaje después de mostrarlo
            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.remove<String>("mensaje")
        }
    }

    Scaffold(
        containerColor = Color(0xFFF4F6F9),
        topBar = {
            TopAppBar(

                title = { Text("Expediente de Empleado") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF142B4A),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onRegresar) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onEditar(empleado.idEmpleado) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar o Reasignar Área",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { mostrarDiálogoEliminar = true }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar Empleado",
                            tint = Color.Red
                        )
                    }
                }
            )
        }


    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tarjeta de Información General
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF142B4A),
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = empleado.nombreCompleto,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "ID Código: ${empleado.idEmpleado}",
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                    Text(
                        text = "Área Asignada: ${empleado.area}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    Text(
                        text = "Puesto / Cargo: ${empleado.puesto}",
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }
            }

            // Tarjeta de Control de Permisos y Horas
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF142B4A),
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Balance de Horas para Permisos",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Con Goce de Sueldo:", color = Color.White)
                        Text(
                            text = "${empleado.horasConGoceDisp} / 40 hrs",
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Sin Goce de Sueldo:", color = Color.White)
                        Text(
                            text = "${empleado.horasSinGoceDisp} / 480 hrs",
                            fontWeight = FontWeight.Bold,
                            color = Color.Green
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón de Reasignación de Área Directo
            Button(
                onClick = { onEditar(empleado.idEmpleado) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B82F6),
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Editar Expediente o Trasladar de Área")


            }
        }
    }

    // Diálogo de confirmación para eliminar expediente
    if (mostrarDiálogoEliminar) {
        AlertDialog(
            onDismissRequest = { mostrarDiálogoEliminar = false },
            title = { Text("¿Eliminar expediente?") },
            text = { Text("Esta acción eliminará permanentemente a ${empleado.nombreCompleto} del sistema de Recursos Humanos.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDiálogoEliminar = false
                        onEliminar(empleado)
                    }
                ) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDiálogoEliminar = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}