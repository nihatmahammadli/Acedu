package com.example.acedu.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object SignIn : Screen("signIn")
    data object SignUp : Screen("signUp")
    data object ForgotPassword : Screen("forgotPassword")
    data object Scholarly: Screen("scholarly")
    data object Privacy : Screen("privacy")
    data object Terms : Screen("terms")
    data object Dashboard : Screen("dashboard")
    data object FocusHud : Screen("focusHud")
}
