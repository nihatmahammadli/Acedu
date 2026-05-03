package com.example.acedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.acedu.core.presentation.navigation.LocalNavigationManager
import com.example.acedu.core.presentation.navigation.rememberNavigationManager
import androidx.compose.ui.graphics.toArgb
import com.example.acedu.core.presentation.theme.AceduTheme
import com.example.acedu.core.presentation.theme.DeepNavy
import com.example.acedu.navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(DeepNavy.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(DeepNavy.toArgb())
        )

        setContent {
            val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()

            AceduTheme {
                val navController = rememberNavController()
                val navigationManager = rememberNavigationManager(navController)

                CompositionLocalProvider(
                    LocalNavigationManager provides navigationManager
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = DeepNavy
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
