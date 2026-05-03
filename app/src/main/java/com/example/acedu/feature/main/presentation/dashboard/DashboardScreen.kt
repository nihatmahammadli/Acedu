package com.example.acedu.feature.main.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.base.BaseScreen
import com.example.acedu.core.presentation.components.AceduLayeredBackground
import com.example.acedu.core.presentation.theme.*
import com.example.acedu.feature.main.presentation.dashboard.components.*

@Composable
fun DashboardScreen() {
    BaseScreen<DashboardUiState, DashboardViewModel> { uiState, viewModel ->
        AceduLayeredBackground(
            cardContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 24.dp)
                ) {
                    // Header Area
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "GOOD MORNING,",
                                style = MaterialTheme.typography.labelSmall,
                                color = ElectricTeal,
                                letterSpacing = 2.sp
                            )
                            Text(
                                text = uiState.userName.uppercase(),
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = (-1).sp
                                ),
                                color = TextWhite
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DashboardIconButton(icon = Icons.Default.Notifications)
                            DashboardIconButton(icon = Icons.Default.Settings)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        StatCard(
                            label = "FOCUS HOURS",
                            value = "24.5",
                            subValue = "+12% vs last week",
                            modifier = Modifier.weight(1f)
                        )
                        StatCard(
                            label = "COMPLETED",
                            value = "12",
                            subValue = "4 tasks today",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    FocusHUDCard(
                        onClick = { viewModel.onFocusHudClick() }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Upcoming Deadlines
                    Text(
                        text = "UPCOMING DEADLINES",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextBody,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    repeat(3) {
                        DeadlineItem()
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        )
    }
}
