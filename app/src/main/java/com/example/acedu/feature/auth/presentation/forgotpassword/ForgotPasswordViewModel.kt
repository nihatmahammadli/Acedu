package com.example.acedu.feature.auth.presentation.forgotpassword

import com.example.acedu.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() : BaseViewModel<ForgotPasswordUiState>() {

    override fun getInitialUiState(): ForgotPasswordUiState = ForgotPasswordUiState()

    fun onEmailChange(email: String) {
        updateState { it.copy(email = email, emailError = null) }
    }

    fun onSendRecoveryLink() {
        if (currentState.email.isBlank()) {
            updateState { it.copy(emailError = "Email cannot be empty") }
            return
        }
        // Logic for sending recovery link would go here
        updateState { it.copy(isLoading = true) }
        // Simulate network call
        updateState { it.copy(isLoading = false, isSuccess = true) }
    }
}
