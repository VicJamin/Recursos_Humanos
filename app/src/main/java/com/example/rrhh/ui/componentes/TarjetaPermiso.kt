package com.example.rrhh.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rrhh.model.Permiso
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment

@Composable


fun TarjetaPermiso(permiso: Permiso) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF243B53),
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = "Formulario de permiso",
                    tint = Color(0xFF60A5FA),
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "Empleado ID: ${permiso.idEmpleado}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = if (permiso.tipoPermiso == "CON_GOCE") "Con Goce" else "Sin Goce",
                    fontWeight = FontWeight.SemiBold,
                    color = if (permiso.tipoPermiso == "CON_GOCE") Color.Red else Color.Green
                )
            }
            Text(text = "Horas solicitadas: ${permiso.horasSolicitadas} hrs")
            Text(text = "Motivo: ${permiso.motivo}")
            Text(
                text = "Fecha: ${permiso.fecha}",
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}

