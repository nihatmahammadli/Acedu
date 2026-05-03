package com.example.acedu.feature.auth.presentation.signin

import com.example.acedu.core.presentation.base.BaseViewModel
import com.example.acedu.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : BaseViewModel<SignInUiState>() {

    override fun getInitialUiState(): SignInUiState = SignInUiState()

    fun onEmailChange(email: String) {
        updateState { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChange(password: String) {
        updateState { it.copy(password = password, passwordError = null) }
    }

    fun onRememberMeChange(rememberMe: Boolean) {
        updateState { it.copy(rememberMe = rememberMe) }
    }

    fun onLoginClick() {
        val email = currentState.email
        val password = currentState.password

        var hasError = false
        if (email.isBlank()) {
            updateState { it.copy(emailError = "Email cannot be empty") }
            hasError = true
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            updateState { it.copy(emailError = "Invalid email format") }
            hasError = true
        }

        if (password.isBlank()) {
            updateState { it.copy(passwordError = "Password cannot be empty") }
            hasError = true
        } else if (password.length < 6) {
            updateState { it.copy(passwordError = "Password must be at least 6 characters") }
            hasError = true
        }

        if (hasError) return

        execute(call = {
            delay(1000)
            "Success"
        }, onSuccess = {
            navigateTo(Screen.Dashboard, clearBackStack = true)
        })
    }
}

