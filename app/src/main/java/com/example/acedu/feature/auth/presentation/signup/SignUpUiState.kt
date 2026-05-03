package com.example.acedu.feature.auth.presentation.signup

import androidx.compose.runtime.Immutable

@Immutable
data class SignUpUiState(
    val fullName: String = "",
    val fullNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val termsAccepted: Boolean = false
)
