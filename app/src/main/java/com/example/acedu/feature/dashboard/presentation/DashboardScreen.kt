package com.example.acedu.feature.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.acedu.core.presentation.theme.DeepDark
import com.example.acedu.core.presentation.theme.TextWhite

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepDark)
            .padding(16.dp)
    ) {
        Text(
            text = "Academic Academy",
            style = MaterialTheme.typography.headlineMedium,
            color = TextWhite
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(text = "Today's Focus", color = TextWhite, style = MaterialTheme.typography.titleLarge)
            }
            // Bura gələcəkdə dərslər və tasklar kartları gələcək
        }
    }
}
