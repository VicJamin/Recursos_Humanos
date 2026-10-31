package com.example.rrhh.model


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "empleados")
data class Empleado(
    @PrimaryKey val idEmpleado: String,
    val nombreCompleto: String,
    val area: String,
    val puesto: String,
    val horasConGoceDisp: Int = 40,
    val horasSinGoceDisp: Int = 480
)