package com.example.acedu.feature.focus.presentation.hud

import com.example.acedu.core.presentation.base.BaseViewModel
import com.example.acedu.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FocusHudViewModel @Inject constructor() : BaseViewModel<FocusHudUiState>() {
    override fun getInitialUiState(): FocusHudUiState = FocusHudUiState(
        sessionTitle = "Introduction to Quantum Mechanics",
        remainingTime = "25:00",
        progress = 0.65f,
        isActive = true
    )

    fun onEndSession() {
        navigateTo(Screen.Dashboard, popUpTo = Screen.FocusHud, inclusive = true)
    }
}
