package com.example.acedu.feature.focus.presentation.hud

data class FocusHudUiState(
    val sessionTitle: String = "",
    val remainingTime: String = "25:00",
    val progress: Float = 0f,
    val isActive: Boolean = false
)
