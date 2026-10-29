package com.example.rrhh.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rrhh.AlcaldiaApplication
import com.example.rrhh.viewmodel.EmpleadoViewModel
import com.example.rrhh.viewmodel.PermisoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navegacion(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "principal"
    ) {
        // --- PANTALLA PRINCIPAL (MENÚ INICIAL BARL SYSTEM) ---
        composable("principal") {
            PantallaPrincipal(
                onEmpleados = {
                    navController.navigate("empleados")
                },
                onPermisos = {
                    navController.navigate("permisosRegistrados")
                }
            )
        }

        // --- PANTALLA EMPLEADOS (CATÁLOGO Y FILTRO POR ÁREA) ---
        composable("empleados") {
            val app = LocalContext.current.applicationContext as AlcaldiaApplication
            val viewModel: EmpleadoViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return EmpleadoViewModel(app.empleadoRepository) as T
                    }
                }
            )

            PantallaEmpleados(
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                },
                onVerDetalle = { idEmpleado ->
                    navController.navigate("detalleEmpleado/$idEmpleado")
                },
                onNavegarAAgregar = {
                    navController.navigate("agregarEmpleado")
                }
            )
        }

        // --- RUTA DETALLE DE EMPLEADO ---
        composable("detalleEmpleado/{idEmpleado}") { backStackEntry ->
            val idEmpleado = backStackEntry.arguments?.getString("idEmpleado") ?: ""
            val app = LocalContext.current.applicationContext as AlcaldiaApplication
            val viewModel: EmpleadoViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return EmpleadoViewModel(app.empleadoRepository) as T
                    }
                }
            )

            val listaEmpleados by viewModel.empleados.collectAsState(initial = emptyList())
            val empleado = listaEmpleados.find { it.idEmpleado == idEmpleado }

            if (empleado != null) {
                PantallaDetalleEmpleado(
                    empleado = empleado,
                    onRegresar = { navController.popBackStack() },
                    navController = navController,
                    onEditar = { id ->
                        navController.navigate("editarEmpleado/$id")
                    },
                    onEliminar = { emp ->
                        viewModel.eliminarEmpleado(emp)
                        navController.popBackStack()
                    }
                )
            }
        }

        // --- RUTA AGREGAR EMPLEADO ---
        composable("agregarEmpleado") {
            val app = LocalContext.current.applicationContext as AlcaldiaApplication
            val viewModel: EmpleadoViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return EmpleadoViewModel(app.empleadoRepository) as T
                    }
                }
            )

            PantallaAgregarEmpleado(
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        // --- RUTA EDITAR / REASIGNAR EMPLEADO ---
        composable("editarEmpleado/{idEmpleado}") { backStackEntry ->
            val idEmpleado = backStackEntry.arguments?.getString("idEmpleado") ?: ""
            val app = LocalContext.current.applicationContext as AlcaldiaApplication
            val viewModel: EmpleadoViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return EmpleadoViewModel(app.empleadoRepository) as T
                    }
                }
            )

            PantallaEditarEmpleado(
                idEmpleado = idEmpleado,
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        // --- PANTALLA PERMISOS REGISTRADOS ---
        composable("permisosRegistrados") {
            val app = LocalContext.current.applicationContext as AlcaldiaApplication
            val viewModel: PermisoViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return PermisoViewModel(app.permisoRepository) as T
                    }
                }
            )

            PantallaPermisosRegistrados(
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                },
                onNavegarARegistrar = {
                    navController.navigate("registrarPermiso")
                }
            )
        }

        // --- RUTA MÓDULO REGISTRAR PERMISO ---
        composable("registrarPermiso") {
            val app = LocalContext.current.applicationContext as AlcaldiaApplication
            val viewModel: PermisoViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return PermisoViewModel(app.permisoRepository) as T
                    }
                }
            )

            PantallaPermiso(
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}
