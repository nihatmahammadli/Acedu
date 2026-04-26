package com.example.acedu.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val screen: Screen,
    val title: String,
    val icon: ImageVector
) {
    data object Dashboard : BottomBarItem(Screen.Dashboard, "Home", Icons.Default.Home)
    data object Calendar : BottomBarItem(Screen.Calendar, "Calendar", Icons.Default.DateRange)
    data object Schedule : BottomBarItem(Screen.Schedule, "Schedule",
        Icons.AutoMirrored.Filled.List
    )
    data object Notes : BottomBarItem(Screen.Notes, "Notes", Icons.Default.Edit)
    data object Pomodoro : BottomBarItem(Screen.Pomodoro, "Focus", Icons.Default.Settings)
}
