package com.example.acedu.feature.auth.presentation.signup

import androidx.lifecycle.viewModelScope
import com.example.acedu.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : BaseViewModel<SignUpUiState>() {

    override fun getInitialUiState(): SignUpUiState = SignUpUiState()

    fun onFullNameChange(value: String) {
        updateState { it.copy(fullName = value, fullNameError = null) }
    }

    fun onEmailChange(value: String) {
        updateState { it.copy(email = value, emailError = null) }
    }

    fun onPasswordChange(value: String) {
        updateState { it.copy(password = value, passwordError = null) }
    }

    fun onConfirmPasswordChange(value: String) {
        updateState { it.copy(confirmPassword = value, confirmPasswordError = null) }
    }

    fun onTermsAcceptedChange(value: Boolean) {
        updateState { it.copy(termsAccepted = value) }
    }

    fun onSignUpClick() {
        var hasError = false

        if (currentState.fullName.isBlank()) {
            updateState { it.copy(fullNameError = "Full name is required") }
            hasError = true
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(currentState.email).matches()) {
            updateState { it.copy(emailError = "Invalid email address") }
            hasError = true
        }

        if (currentState.password.length < 6) {
            updateState { it.copy(passwordError = "Password must be at least 6 characters") }
            hasError = true
        }

        if (currentState.password != currentState.confirmPassword) {
            updateState { it.copy(confirmPasswordError = "Passwords do not match") }
            hasError = true
        }

        if (!hasError) {
            execute(
                call = {
                    delay(2000) // Simulate network call
                },
                onSuccess = {
                    // Navigation or Success logic here
                }
            )
        }
    }
}
