package com.example.acedu.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import com.example.acedu.navigation.Screen

@Stable
interface NavigationManager {

    fun navigateTo(
        screen: Screen,
        popUpTo: Screen? = null,
        inclusive: Boolean = false,
        clearBackStack: Boolean = false
    )

    fun navigateBack(): Boolean
}

class NavigationManagerImpl(
    private val navController: NavHostController
) : NavigationManager {

    override fun navigateTo(
        screen: Screen,
        popUpTo: Screen?,
        inclusive: Boolean,
        clearBackStack: Boolean
    ) {
        navController.navigate(screen.route) {
            when {
                clearBackStack -> popUpTo(0) { this.inclusive = true }
                popUpTo != null -> popUpTo(popUpTo.route) { this.inclusive = inclusive }
            }
        }
    }

    override fun navigateBack(): Boolean = navController.navigateUp()
}

val LocalNavigationManager = staticCompositionLocalOf<NavigationManager> {
    error("No NavigationManager provided")
}

@Composable
fun rememberNavigationManager(navController: NavHostController): NavigationManager {
    return remember(navController) { NavigationManagerImpl(navController) }
}
