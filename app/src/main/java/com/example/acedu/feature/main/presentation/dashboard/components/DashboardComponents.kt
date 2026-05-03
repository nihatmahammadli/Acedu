package com.example.acedu.feature.main.presentation.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.theme.*

@Composable
fun StatCard(
    label: String,
    value: String,
    subValue: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = SurfaceContainerHigh.copy(alpha = 0.4f),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, OutlineVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = label, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = ElectricTeal)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = TextWhite)
            Text(text = subValue, fontSize = 10.sp, color = TextBody)
        }
    }
}

@Composable
fun FocusHUDCard(
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Black.copy(alpha = 0.3f),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, ElectricTeal.copy(alpha = 0.3f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "DEEP WORK SESSION",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    color = ElectricTeal,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Introduction to Quantum Mechanics",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Assignment due in 2 days",
                    fontSize = 12.sp,
                    color = TextBody
                )
                Spacer(modifier = Modifier.height(24.dp))
                LinearProgressIndicator(
                    progress = { 0.65f },
                    modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                    color = ElectricTeal,
                    trackColor = ElectricTeal.copy(alpha = 0.1f),
                )
            }
        }
    }
}

@Composable
fun DeadlineItem() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = SurfaceContainerLow.copy(alpha = 0.5f),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(0.5.dp, OutlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(PrimaryBlue)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Final Project Submission", fontSize = 14.sp, color = TextWhite, fontWeight = FontWeight.Medium)
                Text(text = "Computer Science • 11:59 PM", fontSize = 11.sp, color = TextBody)
            }
            Text(text = "TOMORROW", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
        }
    }
}

@Composable
fun DashboardIconButton(icon: ImageVector) {
    Surface(
        modifier = Modifier.size(40.dp),
        color = SurfaceContainerHigh.copy(alpha = 0.6f),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, OutlineVariant)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(imageVector = icon, contentDescription = null, tint = TextWhite, modifier = Modifier.size(18.dp))
        }
    }
}
