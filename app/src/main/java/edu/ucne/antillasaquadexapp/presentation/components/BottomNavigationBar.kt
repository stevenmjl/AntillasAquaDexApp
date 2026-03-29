package edu.ucne.antillasaquadexapp.presentation.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import edu.ucne.antillasaquadexapp.ui.theme.AntillasAquaDexAppTheme

sealed class BottomBarTab(val route: String, val title: String, val icon: ImageVector) {
    data object Map : BottomBarTab("map", "Mapa", Icons.Default.Map)
    data object Species : BottomBarTab("species", "Especies", Icons.Default.Search)
    data object Favorites : BottomBarTab("favorites", "Favoritos", Icons.Default.Favorite)
    data object Profile : BottomBarTab("profile", "Perfil", Icons.Default.Person)
}

@Composable
fun BottomNavigationBar(
    currentTab: String,
    onTabSelected: (BottomBarTab) -> Unit
) {
    val tabs = listOf(
        BottomBarTab.Map,
        BottomBarTab.Species,
        BottomBarTab.Favorites,
        BottomBarTab.Profile
    )
    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = currentTab == tab.route,
                onClick = { onTabSelected(tab) },
                icon = { Icon(tab.icon, contentDescription = tab.title) },
                label = { Text(tab.title) }
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
