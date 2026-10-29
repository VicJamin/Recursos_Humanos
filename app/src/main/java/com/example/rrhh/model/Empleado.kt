package com.example.rrhh.model


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "empleados")
data class Empleado(
    @PrimaryKey val idEmpleado: String, // ID personalizado
    val nombreCompleto: String,
    val area: String,                   // ej: "Servicios Generales", "CAM"
    val puesto: String,
    val horasConGoceDisp: Int = 40,     // Límite 40 horas
    val horasSinGoceDisp: Int = 480     // Límite 480 horas
)