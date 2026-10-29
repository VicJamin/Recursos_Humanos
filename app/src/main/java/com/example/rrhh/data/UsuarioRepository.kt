package com.example.rrhh.data

import com.example.rrhh.model.Usuario

class UsuarioRepository(
    private val usuarioDao: UsuarioDao
) {
    suspend fun insertarUsuario(usuario: Usuario): Long {
        return usuarioDao.insertarUsuario(usuario)
    }

    suspend fun obtenerUsuarioPorNombre(nombreUsuario: String): Usuario? {
        return usuarioDao.obtenerUsuarioPorNombre(nombreUsuario)
    }

    suspend fun validarUsuario(nombreUsuario: String, contrasena: String): Usuario? {
        return usuarioDao.validarUsuario(nombreUsuario, contrasena)
    }
}
