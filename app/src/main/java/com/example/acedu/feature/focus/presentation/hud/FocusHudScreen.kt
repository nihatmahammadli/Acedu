package com.example.acedu.feature.focus.presentation.hud

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.base.BaseScreen
import com.example.acedu.core.presentation.theme.*

@Composable
fun FocusHudScreen() {
    BaseScreen<FocusHudUiState, FocusHudViewModel> { uiState, viewModel ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "DEEP WORK",
                    style = MaterialTheme.typography.labelMedium,
                    color = ElectricTeal,
                    letterSpacing = 4.sp,
                    fontWeight = FontWeight.Black
                )
                
                Spacer(modifier = Modifier.height(48.dp))
                
                // Timer or Placeholder for now
                Text(
                    text = uiState.remainingTime,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Light,
                        letterSpacing = (-2).sp
                    ),
                    color = TextWhite
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = uiState.sessionTitle.uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextBody,
                    letterSpacing = 1.sp
                )
                
                Spacer(modifier = Modifier.height(64.dp))
                
                Button(
                    onClick = { viewModel.onEndSession() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.05f),
                        contentColor = ErrorRed
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("END SESSION", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                }
            }
        }
    }
}
