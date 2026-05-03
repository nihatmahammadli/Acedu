package com.example.acedu.feature.auth.presentation.splash

import androidx.lifecycle.viewModelScope
import com.example.acedu.core.presentation.base.BaseViewModel
import com.example.acedu.navigation.Screen
import com.example.acedu.core.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : BaseViewModel<SplashUiState>() {

    override fun getInitialUiState(): SplashUiState = SplashUiState()

    init {
        startInitialization()
    }

    private fun startInitialization() {
        viewModelScope.launch {
            val steps = listOf(
                "CONNECTING TO SECURE NODE" to 0.3f,
                "INITIALIZING DATABASE" to 0.6f,
                "AUTHENTICATING SESSION" to 0.9f,
                "READY" to 1.0f
            )

            steps.forEach { (text, targetProgress) ->
                updateState { it.copy(statusText = text) }
                while (currentState.progress < targetProgress) {
                    delay(30)
                    updateState { it.copy(progress = it.progress + 0.02f) }
                }
                delay(200)
            }

            delay(500)
            val prefs = userPreferencesRepository.userPreferencesFlow.first()
            if (prefs.isLoggedIn) {
                navigateTo(Screen.Dashboard, popUpTo = Screen.Splash, inclusive = true)
            } else {
                navigateTo(Screen.SignIn, popUpTo = Screen.Splash, inclusive = true)
            }
        }
    }
}
