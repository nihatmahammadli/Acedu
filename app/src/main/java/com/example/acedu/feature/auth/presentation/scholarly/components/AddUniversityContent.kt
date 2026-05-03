package com.example.acedu.feature.auth.presentation.scholarly.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acedu.core.presentation.components.AceduLayeredBackground
import com.example.acedu.core.presentation.components.AceduTextField
import com.example.acedu.core.presentation.theme.*

@Composable
fun AddUniversityContent(
    onBackClick: () -> Unit = {},
    onContinueClick: (String, String, String) -> Unit = { _, _, _ -> }
) {
    var universityName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var domain by remember { mutableStateOf("") }

    AceduLayeredBackground(
        cardContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Top Navigation Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onBackClick() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryBlue,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Add University",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryBlue
                        )
                    }
                    Text(
                        text = "Scholarly",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextWhite.copy(alpha = 0.5f)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Section Label
                Text(
                    text = "PERSONALIZATION",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ElectricTeal,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = "Add Your Institution",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    letterSpacing = (-1).sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Description
                Text(
                    text = "Can't find your university? Enter the details below to personalize your academic workspace.",
                    fontSize = 14.sp,
                    color = TextBody.copy(alpha = 0.7f),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Form Fields
                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    AceduTextField(
                        label = "UNIVERSITY NAME",
                        value = universityName,
                        onValueChange = { universityName = it },
                        placeholder = "e.g., Academy of Fine Arts"
                    )

                    AceduTextField(
                        label = "CAMPUS LOCATION",
                        value = location,
                        onValueChange = { location = it },
                        placeholder = "City, Country",
                        leadingIcon = Icons.Default.LocationOn
                    )

                    AceduTextField(
                        label = "ACADEMIC DOMAIN",
                        value = domain,
                        onValueChange = { domain = it },
                        placeholder = "e.g., university.edu",
                        leadingIcon = Icons.Default.Search, // Using Search as a placeholder for Globe icon
                        labelAction = {
                            Text(
                                text = "GENERATE",
                                color = PrimaryBlue,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 0.5.sp,
                                modifier = Modifier.clickable { 
                                    if (universityName.isNotEmpty()) {
                                        domain = "${universityName.lowercase().replace(" ", "")}.edu"
                                    }
                                }
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // University Image Placeholder (Dark architectural style)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Black.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Minimalist building silhouette using Boxes
                        Box(
                            modifier = Modifier
                                .width(140.dp)
                                .height(100.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                repeat(4) {
                                    Box(
                                        modifier = Modifier
                                            .width(20.dp)
                                            .fillMaxHeight()
                                            .background(TextWhite.copy(alpha = 0.05f))
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Action Button
                Button(
                    onClick = { onContinueClick(universityName, location, domain) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    enabled = universityName.isNotEmpty() && location.isNotEmpty(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlue,
                        contentColor = DeepNavy,
                        disabledContainerColor = PrimaryBlue.copy(alpha = 0.2f),
                        disabledContentColor = DeepNavy.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Continue to Atelier",
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

                Spacer(modifier = Modifier.height(48.dp))

                // Bottom Quote
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "\"The beautiful thing about learning is that no one can take it away from you.\"",
                        fontSize = 14.sp,
                        color = TextBody.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 22.sp,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "— B.B. KING",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextBody.copy(alpha = 0.3f),
                        letterSpacing = 1.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    )
}

@Preview
@Composable
fun AddUniversityContentPreview() {
    AceduTheme {
        AddUniversityContent()
    }
}
