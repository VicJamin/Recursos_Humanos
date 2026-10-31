package com.example.rrhh.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "permisos")
data class Permiso(
    @PrimaryKey(autoGenerate = true) val idPermiso: Int = 0,
    val idEmpleado: String,
    val tipoPermiso: String, // "CON_GOCE" o "SIN_GOCE"
    val horasSolicitadas: Int,
    val motivo: String,
    val fecha: String
)


