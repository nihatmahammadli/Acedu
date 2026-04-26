package com.example.acedu.navigation

sealed class Screen(val route: String) {
    // Auth/Onboarding (Lazım olsa gələcəkdə)
    data object Onboarding : Screen("onboarding")
    
    // Main Features
    data object Dashboard : Screen("dashboard")
    data object Calendar : Screen("calendar")
    data object Schedule : Screen("schedule")
    data object Tasks : Screen("tasks")
    data object GpaCalculator : Screen("gpa_calculator")
    data object Pomodoro : Screen("pomodoro")
    data object Notes : Screen("notes")
    
    // Details
    data object AddTask : Screen("add_task")
    data object AddNote : Screen("add_note")
    data object ClassDetails : Screen("class_details/{classId}") {
        fun createRoute(classId: String) = "class_details/$classId"
    }
}
