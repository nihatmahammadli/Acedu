package com.example.acedu.feature.main.presentation.dashboard

import com.example.acedu.core.presentation.base.BaseViewModel
import com.example.acedu.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : BaseViewModel<DashboardUiState>() {
    override fun getInitialUiState(): DashboardUiState = DashboardUiState()

    fun onFocusHudClick() {
        navigateTo(Screen.FocusHud)
    }

    init {
        // Initial data loading could happen here
    }
}
