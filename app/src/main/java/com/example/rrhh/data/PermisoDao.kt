package com.example.rrhh.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rrhh.model.Permiso
import kotlinx.coroutines.flow.Flow

@Dao
interface PermisoDao {
    @Query("SELECT * FROM permisos ORDER BY idPermiso DESC")
    fun obtenerTodos(): Flow<List<Permiso>>

    @Insert
    suspend fun insertar(permiso: Permiso)
}
