package com.example.acedu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.acedu.feature.auth.presentation.legal.PrivacyScreen
import com.example.acedu.feature.auth.presentation.legal.TermsScreen
import com.example.acedu.feature.auth.presentation.signin.SignInScreen
import com.example.acedu.feature.auth.presentation.signup.SignUpScreen
import com.example.acedu.feature.auth.presentation.splash.SplashScreen
import com.example.acedu.feature.focus.presentation.hud.FocusHudScreen
import com.example.acedu.feature.main.presentation.dashboard.DashboardScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen()
        }

        // Auth
        composable(Screen.SignIn.route) {
            SignInScreen(
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
                onNavigateToPrivacy = {
                    navController.navigate(Screen.Privacy.route)
                },
                onNavigateToTerms = {
                    navController.navigate(Screen.Terms.route)
                }
            )
        }
        
        composable(Screen.SignUp.route) {
            SignUpScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Privacy.route) {
            PrivacyScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Terms.route) {
            TermsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }

        composable(Screen.FocusHud.route) {
            FocusHudScreen()
        }
    }
}
