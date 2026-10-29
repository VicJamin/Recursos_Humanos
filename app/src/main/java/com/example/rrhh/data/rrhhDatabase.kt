package com.example.rrhh.data

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.rrhh.model.Empleado
import com.example.rrhh.model.Permiso
import com.example.rrhh.model.Usuario

@Database(
    entities = [Empleado::class, Permiso::class, Usuario::class],
    version = 2,
    exportSchema = false
)
abstract class AlcaldiaDatabase : RoomDatabase() {
    abstract fun empleadoDao(): EmpleadoDao
    abstract fun permisoDao(): PermisoDao
    abstract fun usuarioDao(): UsuarioDao
}
