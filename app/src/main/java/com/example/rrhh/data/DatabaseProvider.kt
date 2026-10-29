package com.example.rrhh.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rrhh.model.Empleado
import com.example.rrhh.model.Permiso

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AlcaldiaDatabase? = null

    fun getDatabase(context: Context): AlcaldiaDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AlcaldiaDatabase::class.java,
                "alcaldia_rrhh_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
