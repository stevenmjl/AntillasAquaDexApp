package edu.ucne.antillasaquadexapp.presentation.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Extension
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

sealed class BottomBarTab(
    val route: String, 
    val title: String, 
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Map : BottomBarTab("map", "Mapa", Icons.Default.Map, Icons.Outlined.Map)
    data object Species : BottomBarTab("species", "Especies", Icons.Default.Search, Icons.Outlined.Search)
    data object Trivia : BottomBarTab("trivia", "Trivia", Icons.Default.Extension, Icons.Outlined.Extension)
    data object Favorites : BottomBarTab("favorites", "Favoritos", Icons.Default.Favorite, Icons.Outlined.FavoriteBorder)
    data object Profile : BottomBarTab("profile", "Perfil", Icons.Default.Person, Icons.Outlined.Person)
}

@Composable
fun BottomNavigationBar(
    currentTab: String,
    onTabSelected: (BottomBarTab) -> Unit
) {
    val tabs = listOf(
        BottomBarTab.Map,
        BottomBarTab.Species,
        BottomBarTab.Trivia,
        BottomBarTab.Favorites,
        BottomBarTab.Profile
    )
    NavigationBar(
        tonalElevation = 8.dp
    ) {
        tabs.forEach { tab ->
            val isSelected = currentTab == tab.route
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                icon = { 
                    Icon(
                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon, 
                        contentDescription = tab.title 
                    ) 
                },
                label = { Text(tab.title) },
                alwaysShowLabel = false // Solo muestra el texto de la pestaña activa
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    AntillasAquaDexAppTheme {
        BottomNavigationBar(
            currentTab = BottomBarTab.Map.route,
            onTabSelected = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationBarDarkPreview() {
    AntillasAquaDexAppTheme {
        BottomNavigationBar(
            currentTab = BottomBarTab.Species.route,
            onTabSelected = {}
        )
    }
}
