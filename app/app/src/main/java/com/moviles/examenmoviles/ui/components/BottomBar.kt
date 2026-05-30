package com.moviles.examenmoviles.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.moviles.examenmoviles.ui.theme.*

@Composable
fun BottomBar(
    currentRoute: String,
    onHomeClick: () -> Unit,
    onFlagsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        NavigationBarItem(
            selected = currentRoute == "ticket_list",
            onClick = onHomeClick,
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Tickets") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppPrimary,
                selectedTextColor = AppPrimary,
                unselectedIconColor = AppNavUnselected,
                unselectedTextColor = AppNavUnselected,
                indicatorColor = AppBackground
            )
        )
        NavigationBarItem(
            selected = currentRoute == "feature_flags",
            onClick = onFlagsClick,
            icon = { Icon(Icons.Outlined.Flag, contentDescription = "Flags") },
            label = { Text("Flags") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppPrimary,
                selectedTextColor = AppPrimary,
                unselectedIconColor = AppNavUnselected,
                unselectedTextColor = AppNavUnselected,
                indicatorColor = AppBackground
            )
        )
        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = onProfileClick,
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AppPrimary,
                selectedTextColor = AppPrimary,
                unselectedIconColor = AppNavUnselected,
                unselectedTextColor = AppNavUnselected,
                indicatorColor = AppBackground
            )
        )
    }
}