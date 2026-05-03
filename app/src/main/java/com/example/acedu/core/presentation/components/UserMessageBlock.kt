package com.example.acedu.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.base.UserMessageState
import com.example.acedu.core.presentation.theme.AceduTheme
import com.example.acedu.core.presentation.theme.ErrorRed
import com.example.acedu.core.presentation.theme.PrimaryBlue

@Composable
fun UserMessageBlock(
    state: UserMessageState,
    modifier: Modifier = Modifier
) {
    val (title, message, color) = when (state) {
        is UserMessageState.Success -> Triple(
            state.title ?: "Success",
            state.message ?: state.messageResId?.let { stringResource(it) } ?: "",
            PrimaryBlue
        )
        is UserMessageState.Error -> Triple(
            state.title ?: "Error",
            state.message ?: state.messageResId?.let { stringResource(it) } ?: "",
            ErrorRed
        )
        is UserMessageState.Warning -> Triple(
            state.title ?: "Warning",
            state.message ?: state.messageResId?.let { stringResource(it) } ?: "",
            Color(0xFFFFA000)
        )
        is UserMessageState.Info -> Triple(
            state.title ?: "Info",
            state.message ?: state.messageResId?.let { stringResource(it) } ?: "",
            Color(0xFF2196F3)
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title.uppercase(),
            style = AceduTheme.textStyles.labelSmall,
            color = color,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = message,
            style = AceduTheme.textStyles.bodyLarge,
            color = AceduTheme.colors.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}
