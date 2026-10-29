package com.example.rrhh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rrhh.data.PermisoRepository
import com.example.rrhh.model.Permiso
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PermisoViewModel(private val repository: PermisoRepository) : ViewModel() {

    val listaPermisos: StateFlow<List<Permiso>> = repository.obtenerPermisos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun registrarPermiso(permiso: Permiso, onResultado: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exito = repository.registrarPermiso(permiso)
            onResultado(exito)
        }
    }
}
