package com.example.acedu.feature.auth.presentation.splash

import androidx.compose.runtime.Immutable

@Immutable
data class SplashUiState(
    val progress: Float = 0f,
    val statusText: String = "INITIALIZING ENVIRONMENT"
)
