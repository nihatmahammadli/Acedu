package com.example.acedu.feature.auth.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.R
import com.example.acedu.core.presentation.base.BaseScreen
import com.example.acedu.core.presentation.components.*
import com.example.acedu.core.presentation.theme.*
import com.example.acedu.feature.auth.presentation.components.EncryptedBadge
import com.example.acedu.feature.auth.presentation.components.SocialButton
import com.example.acedu.feature.auth.presentation.components.SocialDivider

@Composable
fun SignUpScreen(
    onNavigateBack: () -> Unit = {},
    onNavigateToScholarly: () -> Unit = {}
) {
    BaseScreen<SignUpUiState, SignUpViewModel> { uiState, viewModel ->
        LaunchedEffect(uiState.isSignUpSuccess) {
            if (uiState.isSignUpSuccess) {
                onNavigateToScholarly()
            }
        }
        SignUpContent(
            uiState = uiState,
            onFullNameChange = viewModel::onFullNameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
            onSignUpClick = viewModel::onSignUpClick,
            onNavigateBack = onNavigateBack
        )
    }
}

@Composable
fun SignUpContent(
    uiState: SignUpUiState,
    onFullNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onConfirmPasswordChange: (String) -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    AceduLayeredBackground(
        cardContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextWhite
                        )
                    }

                    EncryptedBadge(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp)
                    )

                    // Logo Placeholder
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "A",
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // 2. Headings
                Column {
                    Text(
                        text = "START YOUR JOURNEY",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = ElectricTeal,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Create account.",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                        letterSpacing = (-1).sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    AceduTextField(
                        label = "Full Name",
                        value = uiState.fullName,
                        onValueChange = onFullNameChange,
                        placeholder = "John Doe",
                        error = uiState.fullNameError
                    )

                    AceduTextField(
                        label = "Email Address",
                        value = uiState.email,
                        onValueChange = onEmailChange,
                        placeholder = "name@university.edu",
                        error = uiState.emailError
                    )

                    AceduTextField(
                        label = "Password",
                        value = uiState.password,
                        onValueChange = onPasswordChange,
                        placeholder = "••••••••",
                        isPassword = true,
                        error = uiState.passwordError
                    )

                    AceduTextField(
                        label = "Confirm Password",
                        value = uiState.confirmPassword,
                        onValueChange = onConfirmPasswordChange,
                        placeholder = "••••••••",
                        isPassword = true,
                        error = uiState.confirmPasswordError
                    )

                    AceduButton(
                        text = "Create Account",
                        onClick = onSignUpClick,
                        enabled = uiState.fullName.isNotEmpty() && 
                                uiState.email.isNotEmpty() && 
                                uiState.password.isNotEmpty() && 
                                uiState.confirmPassword.isNotEmpty() &&
                                uiState.fullNameError == null &&
                                uiState.emailError == null &&
                                uiState.passwordError == null &&
                                uiState.confirmPasswordError == null,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 4. Social Section
                SocialDivider()

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SocialButton(
                        text = "Google",
                        iconRes = R.drawable.ic_google,
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    )
                    SocialButton(
                        text = "Apple",
                        iconRes = R.drawable.ic_apple,
                        iconSize = 28.dp,
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account? ",
                        color = TextBody,
                        fontSize = 13.sp
                    )
                    TextButton(
                        onClick = onNavigateBack,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Sign In",
                            color = PrimaryBlue,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Bottom Privacy Text
                Text(
                    text = "By creating an account, you agree to our Terms of Service and Privacy Policy.",
                    fontSize = 11.sp,
                    color = TextBody.copy(alpha = 0.5f),
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    AceduTheme {
        SignUpContent(uiState = SignUpUiState())
    }
}
