package com.example.rrhh.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AlcaldiaDatabase? = null

    fun getDatabase(context: Context): AlcaldiaDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AlcaldiaDatabase::class.java,
                "alcaldia_rrhh_db"
            )
            .fallbackToDestructiveMigration(true)
            .build()
            INSTANCE = instance
            instance
        }
    }
}
