package com.example.acedu.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.acedu.core.presentation.theme.*

@Composable
fun AceduLayeredBackground(
    modifier: Modifier = Modifier,
    topContent: @Composable (ColumnScope.() -> Unit)? = null,
    cardContent: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNavy)
            .drawBehind {
                // Dot pattern
                val dotSize = 2.dp.toPx()
                val spacing = 24.dp.toPx()
                for (x in 0 until (size.width / spacing).toInt()) {
                    for (y in 0 until (size.height / spacing).toInt()) {
                        drawCircle(
                            color = Color.White.copy(alpha = 0.03f),
                            radius = dotSize / 2,
                            center = Offset(x * spacing, y * spacing)
                        )
                    }
                }
            }
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(vertical = 12.dp, horizontal = 12.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(32.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0A1A21),
                            Color(0xFF041015)
                        )
                    )
                )
                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(32.dp))
                .padding(24.dp),
            content = cardContent
        )
    }
}
