package com.example.acedu.feature.auth.presentation.legal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.components.AceduLayeredBackground
import com.example.acedu.core.presentation.theme.PrimaryBlue
import com.example.acedu.core.presentation.theme.TextWhite
import com.example.acedu.core.presentation.theme.TextBody

@Composable
fun TermsScreen(
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
                    text = "Terms of Service",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )

                Spacer(modifier = Modifier.height(24.dp))

                LegalSection(
                    title = "1. Acceptance of Terms",
                    content = "By accessing and using Acedu, you agree to be bound by these terms. If you do not agree, please do not use the application."
                )

                LegalSection(
                    title = "2. User Conduct",
                    content = "You agree to use Acedu only for lawful purposes related to your academic productivity. Any misuse of the service may lead to account termination."
                )

                LegalSection(
                    title = "3. Intellectual Property",
                    content = "All content and functionality within Acedu are the exclusive property of Acedu Inc. and are protected by international copyright laws."
                )

                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    )
}
