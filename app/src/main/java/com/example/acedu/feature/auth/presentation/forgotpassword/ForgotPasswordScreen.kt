package com.example.acedu.feature.auth.presentation.forgotpassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.base.BaseScreen
import com.example.acedu.core.presentation.components.AceduButton
import com.example.acedu.core.presentation.components.AceduLayeredBackground
import com.example.acedu.core.presentation.components.AceduTextField
import com.example.acedu.core.presentation.theme.AceduTheme
import com.example.acedu.core.presentation.theme.PrimaryBlue
import com.example.acedu.core.presentation.theme.TextBody
import com.example.acedu.core.presentation.theme.TextWhite

@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit = {}
) {
    BaseScreen<ForgotPasswordUiState, ForgotPasswordViewModel> { uiState, viewModel ->
        ForgotPasswordContent(
            uiState = uiState,
            onEmailChange = viewModel::onEmailChange,
            onSendRecoveryLink = viewModel::onSendRecoveryLink,
            onNavigateBack = onNavigateBack
        )
    }
}

@Composable
fun ForgotPasswordContent(
    uiState: ForgotPasswordUiState,
    onEmailChange: (String) -> Unit = {},
    onSendRecoveryLink: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    AceduLayeredBackground(
        cardContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // 1. Back Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onNavigateBack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = PrimaryBlue,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = "Account Recovery",
                        color = PrimaryBlue,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(64.dp))

                // 2. Headings
                Text(
                    text = "Lost your key?",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    letterSpacing = (-1).sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Enter the email address associated with your scholarly account, and we will send you a recovery link.",
                    fontSize = 15.sp,
                    color = TextBody,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(48.dp))

                // 3. Form
                AceduTextField(
                    label = "EMAIL ADDRESS",
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    placeholder = "name@university.edu",
                    error = uiState.emailError,
                    leadingIcon = Icons.Default.Email
                )

                Spacer(modifier = Modifier.height(24.dp))

                AceduButton(
                    text = "Send Recovery Link",
                    onClick = onSendRecoveryLink,
                    enabled = uiState.email.isNotEmpty() && uiState.emailError == null
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 4. Back to Login
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateBack() },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = TextBody,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "BACK TO LOGIN",
                        color = TextBody,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    AceduTheme {
        ForgotPasswordContent(uiState = ForgotPasswordUiState())
    }
}
