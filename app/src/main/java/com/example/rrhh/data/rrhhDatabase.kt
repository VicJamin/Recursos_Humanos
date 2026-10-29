package com.example.rrhh.data

import androidx.room.Database
import androidx.room.RoomDatabase

//importamos los empleado y permiso
import com.example.rrhh.model.Empleado
import com.example.rrhh.model.Permiso


@Database(
    entities = [Empleado::class, Permiso::class],
    version = 1,
    exportSchema = false)

abstract class AlcaldiaDatabase : RoomDatabase() {
    abstract fun empleadoDao(): EmpleadoDao
    abstract fun permisoDao(): PermisoDao
}