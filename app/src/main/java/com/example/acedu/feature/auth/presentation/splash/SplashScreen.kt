package com.example.acedu.feature.auth.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.base.BaseScreen
import com.example.acedu.core.presentation.theme.*

@Composable
fun SplashScreen() {
    BaseScreen<SplashUiState, SplashViewModel> { uiState, _ ->
        val animatedProgress by animateFloatAsState(
            targetValue = uiState.progress,
            animationSpec = tween(durationMillis = 300),
            label = "ProgressAnimation"
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepNavy),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1B2B33))
                        .border(1.dp, Color(0x33D0E9F7), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Acedu",
                        color = PrimaryBlue,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "The Atelier",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryBlue,
                    letterSpacing = (-1.5).sp
                )

                Text(
                    text = "THE ATELIER OF INTELLIGENCE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextBody.copy(alpha = 0.6f),
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(60.dp))

                Column(
                    modifier = Modifier.width(200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .clip(RoundedCornerShape(1.dp)),
                        color = PrimaryBlue,
                        trackColor = Color(0xFF102933)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = uiState.statusText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextBody.copy(alpha = 0.4f),
                        letterSpacing = 1.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = TextBody.copy(alpha = 0.3f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SECURED ACADEMIC NODE",
                    fontSize = 10.sp,
                    color = TextBody.copy(alpha = 0.3f),
                    letterSpacing = 1.sp
                )
            }
        }
    }
}
