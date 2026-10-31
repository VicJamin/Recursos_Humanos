package com.example.rrhh.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rrhh.model.Empleado
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpleadoDao {
    @Query("SELECT * FROM empleados ORDER BY nombreCompleto ASC")
    fun obtenerTodos(): Flow<List<Empleado>>

    @Query("SELECT * FROM empleados WHERE area = :areaNombre ORDER BY nombreCompleto ASC")
    fun obtenerPorArea(areaNombre: String): Flow<List<Empleado>>

    @Query("SELECT * FROM empleados WHERE idEmpleado = :id")
    suspend fun obtenerPorId(id: String): Empleado?

    @Query("SELECT * FROM empleados WHERE idEmpleado = :id")
    fun obtenerPorIdFlow(id: String): Flow<Empleado?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(empleado: Empleado)

    @Update
    suspend fun actualizar(empleado: Empleado)

    @Delete
    suspend fun eliminar(empleado: Empleado)
}
