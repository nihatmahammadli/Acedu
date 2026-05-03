package com.example.acedu.feature.main.presentation.dashboard

data class DashboardUiState(
    val userName: String = "Scholar",
    val focusHours: Float = 0f,
    val tasksCompleted: Int = 0,
    val upcomingDeadlines: List<String> = emptyList(),
    val isLoading: Boolean = false
)
