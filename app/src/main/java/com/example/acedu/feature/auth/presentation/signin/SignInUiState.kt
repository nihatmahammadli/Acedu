package com.example.acedu.feature.auth.presentation.signin

import androidx.compose.runtime.Immutable

@Immutable
data class SignInUiState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val rememberMe: Boolean = false
)
