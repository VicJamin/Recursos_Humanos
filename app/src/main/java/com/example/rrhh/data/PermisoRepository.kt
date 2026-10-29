package com.example.rrhh.data

import com.example.rrhh.model.Permiso
import kotlinx.coroutines.flow.Flow

class PermisoRepository(
    private val permisoDao: PermisoDao,
    private val empleadoDao: EmpleadoDao
) {
    fun obtenerPermisos(): Flow<List<Permiso>> = permisoDao.obtenerTodos()

    suspend fun registrarPermiso(permiso: Permiso): Boolean {
        val empleado = empleadoDao.obtenerPorId(permiso.idEmpleado) ?: return false

        if (permiso.tipoPermiso == "CON_GOCE") {
            if (empleado.horasConGoceDisp < permiso.horasSolicitadas) return false
            val empActualizado = empleado.copy(
                horasConGoceDisp = empleado.horasConGoceDisp - permiso.horasSolicitadas
            )
            empleadoDao.actualizar(empActualizado)
        } else {
            if (empleado.horasSinGoceDisp < permiso.horasSolicitadas) return false
            val empActualizado = empleado.copy(
                horasSinGoceDisp = empleado.horasSinGoceDisp - permiso.horasSolicitadas
            )
            empleadoDao.actualizar(empActualizado)
        }

        permisoDao.insertar(permiso)
        return true
    }
}
