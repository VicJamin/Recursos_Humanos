package com.example.rrhh

import android.app.Application
import com.example.rrhh.data.DatabaseProvider
import com.example.rrhh.data.EmpleadoRepository
import com.example.rrhh.data.PermisoRepository

class AlcaldiaApplication : Application() {
    val database by lazy { DatabaseProvider.getDatabase(this) }
    val empleadoRepository by lazy { EmpleadoRepository(database.empleadoDao()) }
    val permisoRepository by lazy { PermisoRepository(database.permisoDao(), database.empleadoDao()) }
}