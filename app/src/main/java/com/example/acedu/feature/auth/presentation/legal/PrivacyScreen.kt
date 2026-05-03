package com.example.acedu.feature.auth.presentation.legal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.components.AceduLayeredBackground
import com.example.acedu.core.presentation.theme.PrimaryBlue
import com.example.acedu.core.presentation.theme.TextBody
import com.example.acedu.core.presentation.theme.TextWhite

@Composable
fun PrivacyScreen(
    onNavigateBack: () -> Unit
) {
    AceduLayeredBackground(
        cardContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextWhite
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Privacy Policy",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )

                Spacer(modifier = Modifier.height(24.dp))

                LegalSection(
                    title = "1. Information Collection",
                    content = "We collect information you provide directly to us when you create an account, such as your name and email address. We also collect data related to your productivity habits within the app to provide a personalized experience."
                )

                LegalSection(
                    title = "2. Data Usage",
                    content = "Your data is used to maintain your account, provide our services, and improve the app's functionality. We do not sell your personal data to third parties."
                )

                LegalSection(
                    title = "3. Security",
                    content = "We implement industry-standard security measures to protect your data. However, no method of transmission over the Internet is 100% secure."
                )

                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    )
}

@Composable
fun LegalSection(title: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlue
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            fontSize = 14.sp,
            color = TextBody,
            lineHeight = 20.sp
        )
    }
}
