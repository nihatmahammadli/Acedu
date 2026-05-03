package com.example.acedu.feature.auth.presentation.signin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.acedu.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.base.BaseScreen
import com.example.acedu.core.presentation.components.AceduButton
import com.example.acedu.core.presentation.components.AceduLayeredBackground
import com.example.acedu.core.presentation.components.AceduTextField
import com.example.acedu.core.presentation.theme.*
import com.example.acedu.feature.auth.presentation.components.EncryptedBadge
import com.example.acedu.feature.auth.presentation.components.SocialButton
import com.example.acedu.feature.auth.presentation.components.SocialDivider

@Composable
fun SignInScreen(
    onNavigateToSignUp: () -> Unit = {},
    onNavigateToPrivacy: () -> Unit = {},
    onNavigateToTerms: () -> Unit = {}
) {
    BaseScreen<SignInUiState, SignInViewModel> { uiState, viewModel ->
        AceduLayeredBackground(
            cardContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    // 1. Top Badge & Logo
                    Box(modifier = Modifier.fillMaxWidth()) {
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

                    Spacer(modifier = Modifier.height(48.dp))

                    // 2. Headings
                    Column {
                        Text(
                            text = "WELCOME BACK",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = ElectricTeal,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Enter your workspace.",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite,
                            letterSpacing = (-1).sp
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    // 3. Form
                    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                        AceduTextField(
                            label = "Email Address",
                            value = uiState.email,
                            onValueChange = viewModel::onEmailChange,
                            placeholder = "name@university.edu",
                            error = uiState.emailError
                        )

                        AceduTextField(
                            label = "Password",
                            value = uiState.password,
                            onValueChange = viewModel::onPasswordChange,
                            placeholder = "••••••••",
                            isPassword = true,
                            error = uiState.passwordError,
                            labelAction = {
                                Text(
                                    text = "FORGOT?",
                                    modifier = Modifier.clickable { /* Forgot Password */ },
                                    color = PrimaryBlue,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        )

                        AceduButton(
                            text = "Sign In to Acedu",
                            onClick = viewModel::onLoginClick,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // 4. Social Divider
                    SocialDivider()

                    Spacer(modifier = Modifier.height(32.dp))

                    // 5. Social Buttons
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

                    // 6. Create Account
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "New to Acedu? ", color = TextBody, fontSize = 13.sp
                        )
                        TextButton(
                            onClick = onNavigateToSignUp, contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "Create an account",
                                color = PrimaryBlue,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    // 7. Footer
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "© 2024 ACEDU INC.",
                            fontSize = 10.sp,
                            color = TextBody.copy(alpha = 0.3f),
                            letterSpacing = 0.5.sp
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                text = "PRIVACY",
                                fontSize = 10.sp,
                                color = TextWhite.copy(alpha = 0.5f),
                                letterSpacing = 0.5.sp,
                                modifier = Modifier.clickable { onNavigateToPrivacy() }
                            )
                            Text(
                                text = "TERMS",
                                fontSize = 10.sp,
                                color = TextWhite.copy(alpha = 0.5f),
                                letterSpacing = 0.5.sp,
                                modifier = Modifier.clickable { onNavigateToTerms() }
                            )
                        }
                    }
                }
            })
    }
}
