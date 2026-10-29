package com.example.rrhh.ui.componentes



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rrhh.model.Empleado

@Composable

fun TarjetaEmpleado(
    empleado: Empleado,
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF243B53),
            contentColor = Color.White
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {


                // Icono que representa al estudiante
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Estudiante",
                    modifier = Modifier.size(60.dp)
                )


                Spacer(
                    modifier = Modifier.width(30.dp)
                )

                Column(modifier = Modifier.weight(1f)) {


                    Text(text = empleado.nombreCompleto)

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ID: ${empleado.idEmpleado} | Área: ${empleado.area}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Horas disponibles: "

                    )

            Row(verticalAlignment = Alignment.CenterVertically) {

             Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${empleado.horasConGoceDisp}h (C/G) | ", color = Color.Red
                   )

                  Text(
                  text = "${empleado.horasSinGoceDisp}h (S/G)", color = Color.Red

                    )

                    }
                }
            }

        }
    }
}