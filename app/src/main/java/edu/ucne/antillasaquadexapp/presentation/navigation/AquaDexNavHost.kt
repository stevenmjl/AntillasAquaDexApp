package edu.ucne.antillasaquadexapp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import edu.ucne.antillasaquadexapp.presentation.components.BottomBarTab
import edu.ucne.antillasaquadexapp.presentation.components.BottomNavigationBar
import edu.ucne.antillasaquadexapp.presentation.favoritos.FavoritosScreen
import edu.ucne.antillasaquadexapp.presentation.main.PaisDetailScreen
import edu.ucne.antillasaquadexapp.presentation.main.MainScreen
import edu.ucne.antillasaquadexapp.presentation.main.ZonaDetailScreen
import edu.ucne.antillasaquadexapp.presentation.especies.detail.EspecieDetailScreen
import edu.ucne.antillasaquadexapp.presentation.especies.list.EspecieListScreen
import edu.ucne.antillasaquadexapp.presentation.usuario.PerfilScreen
import edu.ucne.antillasaquadexapp.presentation.titulo.BienvenidaScreen

@Composable
fun AquaDexNavHost(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mostrarBottomBar = currentRoute != null && !currentRoute.contains("Bienvenida")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (mostrarBottomBar) {
                BottomNavigationBar(
                    currentTab = when {
                        currentRoute?.contains("Mapa") == true -> "map"
                        currentRoute?.contains("EspecieLista") == true -> "species"
                        currentRoute?.contains("Favoritos") == true -> "favorites"
                        currentRoute?.contains("Perfil") == true -> "profile"
                        else -> "map"
                    },
                    onTabSelected = { tab ->
                        val destination = when (tab) {
                            BottomBarTab.Map -> Screen.Mapa
                            BottomBarTab.Species -> Screen.EspecieLista
                            BottomBarTab.Favorites -> Screen.Favoritos
                            BottomBarTab.Profile -> Screen.Perfil
                        }
                        navController.navigate(destination) {
                            popUpTo(Screen.Mapa) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Screen.Bienvenida
            ) {
                composable<Screen.Bienvenida> {
                    BienvenidaScreen(
                        onContinue = {
                            navController.navigate(Screen.Mapa) {
                                popUpTo(Screen.Bienvenida) { inclusive = true }
                            }
                        }
                    )
                }

                composable<Screen.Mapa> {
                    MainScreen(
                        onPaisClick = { paisNombre ->
                            navController.navigate(Screen.PaisDetalle(paisNombre))
                        }
                    )
                }

                composable<Screen.PaisDetalle> { backStackEntry ->
                    val args = backStackEntry.toRoute<Screen.PaisDetalle>()
                    PaisDetailScreen(
                        nombrePais = args.paisNombre,
                        onNavigateBack = { navController.popBackStack() },
                        onZoneClick = { zona ->
                            navController.navigate(
                                Screen.ZonaDetalle(
                                    zonaNombre = zona.nombre,
                                    latitud = zona.latitud,
                                    longitud = zona.longitud,
                                    especieIds = zona.especieIds
                                )
                            )
                        }
                    )
                }

                composable<Screen.ZonaDetalle> { backStackEntry ->
                    val args = backStackEntry.toRoute<Screen.ZonaDetalle>()
                    ZonaDetailScreen(
                        zoneName = args.zonaNombre,
                        lat = args.latitud,
                        lon = args.longitud,
                        listaEspeciesID = args.especieIds,
                        onNavigateBack = { navController.popBackStack() },
                        onEspecieClick = { especieId ->
                            navController.navigate(Screen.EspecieDetalle(especieId))
                        }
                    )
                }

                composable<Screen.EspecieLista> {
                    EspecieListScreen(
                        onEspecieClick = { especieId ->
                            navController.navigate(Screen.EspecieDetalle(especieId))
                        }
                    )
                }

                composable<Screen.EspecieDetalle> { backStackEntry ->
                    val args = backStackEntry.toRoute<Screen.EspecieDetalle>()
                    EspecieDetailScreen(
                        especieId = args.especieId,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }

                composable<Screen.Favoritos> {
                    FavoritosScreen(
                        onEspecieClick = { especieId ->
                            navController.navigate(Screen.EspecieDetalle(especieId))
                        }
                    )
                }

                composable<Screen.Perfil> {
                    PerfilScreen()
                }
            }
        }
    }
}