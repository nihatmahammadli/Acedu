package com.example.acedu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.acedu.core.presentation.base.EmptyScreen
import com.example.acedu.feature.dashboard.presentation.DashboardScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Main Features
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        
        composable(Screen.Calendar.route) {
            EmptyScreen("Calendar View")
        }
        
        composable(Screen.Schedule.route) {
            EmptyScreen("Class Schedule")
        }
        
        composable(Screen.Tasks.route) {
            EmptyScreen("Task Manager")
        }
        
        composable(Screen.GpaCalculator.route) {
            EmptyScreen("GPA Calculator")
        }
        
        composable(Screen.Pomodoro.route) {
            EmptyScreen("Focus Mode")
        }
        
        composable(Screen.Notes.route) {
            EmptyScreen("Notes")
        }

        // Auth / Onboarding
        composable(Screen.Onboarding.route) {
            EmptyScreen("Welcome to Acedu")
        }
    }
}
