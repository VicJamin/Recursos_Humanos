package com.example.rrhh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rrhh.data.EmpleadoRepository
import com.example.rrhh.model.Empleado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class EmpleadoViewModel(private val repository: EmpleadoRepository) : ViewModel() {

    private val _areaSeleccionada = MutableStateFlow("Todos")
    val areaSeleccionada: StateFlow<String> = _areaSeleccionada

    val empleados = _areaSeleccionada.flatMapLatest { area ->
        repository.obtenerEmpleadosPorArea(area)
    }

    fun cambiarArea(nuevaArea: String) {
        _areaSeleccionada.value = nuevaArea
    }

    fun guardarEmpleado(empleado: Empleado) {
        viewModelScope.launch {
            repository.guardarOActualizar(empleado)
        }
    }

    fun eliminarEmpleado(empleadoEliminar: Empleado) {
        viewModelScope.launch {
            repository.eliminarEmpleado(empleadoEliminar)
        }
    }
}
