package com.example.acedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.acedu.core.presentation.navigation.LocalNavigationManager
import com.example.acedu.core.presentation.navigation.rememberNavigationManager
import com.example.acedu.core.presentation.theme.AceduTheme
import com.example.acedu.core.presentation.theme.DeepDark
import com.example.acedu.navigation.BottomBarItem
import com.example.acedu.navigation.NavigationGraph
import com.example.acedu.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition { !viewModel.isReady.value }
        
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )

        setContent {
            val startDestination: String by viewModel.startDestination.collectAsStateWithLifecycle()
            val isReady: Boolean by viewModel.isReady.collectAsStateWithLifecycle()

            AceduTheme {
                if (isReady) {
                    val navController = rememberNavController()
                    val navigationManager = rememberNavigationManager(navController)
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    val bottomBarScreens = listOf(
                        BottomBarItem.Dashboard,
                        BottomBarItem.Calendar,
                        BottomBarItem.Schedule,
                        BottomBarItem.Notes,
                        BottomBarItem.Pomodoro
                    )

                    CompositionLocalProvider(
                        LocalNavigationManager provides navigationManager
                    ) {
                        Scaffold(
                            bottomBar = {
                                if (bottomBarScreens.any { it.screen.route == currentDestination?.route }) {
                                    NavigationBar(
                                        containerColor = DeepDark,
                                        contentColor = Color.White
                                    ) {
                                        bottomBarScreens.forEach { item ->
                                            NavigationBarItem(
                                                icon = { Icon(item.icon, contentDescription = item.title) },
                                                label = { Text(item.title) },
                                                selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true,
                                                onClick = {
                                                    navController.navigate(item.screen.route) {
                                                        popUpTo(navController.graph.findStartDestination().id) {
                                                            saveState = true
                                                        }
                                                        launchSingleTop = true
                                                        restoreState = true
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        ) { innerPadding ->
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                color = DeepDark
                            ) {
                                NavigationGraph(
                                    navController = navController,
                                    startDestination = startDestination
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
