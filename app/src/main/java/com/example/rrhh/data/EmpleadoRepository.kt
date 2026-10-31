package com.example.rrhh.data

import com.example.rrhh.model.Empleado
import kotlinx.coroutines.flow.Flow

class EmpleadoRepository(private val dao: EmpleadoDao) {
    fun obtenerEmpleadosPorArea(area: String): Flow<List<Empleado>> {
        return if (area == "Todos") dao.obtenerTodos()
        else dao.obtenerPorArea(area)
    }

    fun obtenerEmpleadoPorIdFlow(id: String): Flow<Empleado?> = dao.obtenerPorIdFlow(id)

    suspend fun guardarOActualizar(empleado: Empleado) = dao.insertar(empleado)

    suspend fun eliminarEmpleado(empleado: Empleado) {
        dao.eliminar(empleado)
    }
}
