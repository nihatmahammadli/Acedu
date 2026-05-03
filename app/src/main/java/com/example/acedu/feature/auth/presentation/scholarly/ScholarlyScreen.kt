package com.example.acedu.feature.auth.presentation.scholarly

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.base.BaseScreen
import com.example.acedu.feature.auth.presentation.scholarly.components.AddUniversityContent
import com.example.acedu.core.presentation.components.AceduLayeredBackground
import com.example.acedu.core.presentation.components.AceduTextField
import com.example.acedu.core.presentation.theme.AceduTheme
import com.example.acedu.core.presentation.theme.DeepNavy
import com.example.acedu.core.presentation.theme.PrimaryBlue
import com.example.acedu.core.presentation.theme.SurfaceContainer
import com.example.acedu.core.presentation.theme.TextBody
import com.example.acedu.core.presentation.theme.TextWhite

@Composable
fun ScholarlyScreen(
    onNavigateToDashboard: () -> Unit = {}
) {
    BaseScreen<ScholarlyUiState, ScholarlyViewModel> { uiState, viewModel ->
        if (uiState.isAddingUniversity) {
            AddUniversityContent(
                onBackClick = viewModel::onBackFromAddUniversity,
                onContinueClick = { name, loc, dom -> 
                    viewModel.onCustomUniversityAdded(name, loc, dom)
                    onNavigateToDashboard()
                }
            )
        } else {
            ScholarlyContent(
                uiState = uiState,
                onSearchQueryChanged = viewModel::onSearchQueryChanged,
                onUniversitySelected = viewModel::onUniversitySelected,
                onNavigateToDashboard = onNavigateToDashboard
            )
        }
    }
}

@Composable
fun ScholarlyContent(
    uiState: ScholarlyUiState,
    onSearchQueryChanged: (String) -> Unit = {},
    onUniversitySelected: (String) -> Unit = {},
    onNavigateToDashboard: () -> Unit = {}
) {
    AceduLayeredBackground(
        cardContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Logo
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Black.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A",
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Scholarly",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "Enter the Atelier of Intelligence.",
                    fontSize = 15.sp,
                    color = TextBody.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(56.dp))

                Text(
                    text = "Where do you study?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Personalize your academic workspace by selecting your institution.",
                    fontSize = 14.sp,
                    color = TextBody.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Search Bar
                AceduTextField(
                    label = "",
                    value = uiState.searchQuery,
                    onValueChange = onSearchQueryChanged,
                    placeholder = "Search your university...",
                    leadingIcon = Icons.Default.Search,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Grid of Universities
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    val selectedUniversity =
                        uiState.universities.find { it.id == uiState.selectedUniversityId }
                    val isAnyRegularSelected =
                        selectedUniversity != null && !selectedUniversity.isOther

                    items(uiState.universities) { university ->
                        val isEnabled = if (university.isOther) !isAnyRegularSelected else true

                        UniversityCard(
                            university = university,
                            isSelected = university.id == uiState.selectedUniversityId,
                            enabled = isEnabled,
                            onClick = { onUniversitySelected(university.id) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Get Started Button
                Button(
                    onClick = onNavigateToDashboard,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    enabled = uiState.selectedUniversityId != null,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlue,
                        contentColor = DeepNavy,
                        disabledContainerColor = PrimaryBlue.copy(alpha = 0.3f),
                        disabledContentColor = DeepNavy.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Get Started",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun UniversityCard(
    university: University,
    isSelected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(0.95f)
            .alpha(if (enabled) 1f else 0.3f)
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.4f))
            .border(
                width = 2.dp,
                color = if (isSelected) PrimaryBlue else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(enabled = enabled, onClick = onClick)
            .padding(12.dp)
    ) {
        if (isSelected) {
            Surface(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.TopEnd),
                color = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = PrimaryBlue,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (university.isOther) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.05f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = TextWhite.copy(alpha = 0.6f),
                        modifier = Modifier.size(28.dp)
                    )
                }
            } else {
                // Logo Placeholder matching image style
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = university.name.take(1),
                        color = PrimaryBlue.copy(alpha = 0.7f),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = university.name,
                color = TextWhite,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScholarlyScreenPreview() {
    AceduTheme {
        ScholarlyContent(
            uiState = ScholarlyUiState(
                selectedUniversityId = "2",
                universities = initialUniversities
            )
        )
    }
}
