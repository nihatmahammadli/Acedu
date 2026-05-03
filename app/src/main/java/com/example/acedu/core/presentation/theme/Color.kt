package com.example.acedu.core.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AceduColors(
    val primary: Color = PrimaryBlue,
    val surface: Color = Surface,
    val surfaceContainerLow: Color = SurfaceContainerLow,
    val surfaceContainerHigh: Color = SurfaceContainerHigh,
    val onSurface: Color = OnSurface,
    val onSurfaceVariant: Color = OnSurfaceVariant,
    val error: Color = ErrorRed
)

// Primary & Containers
val PrimaryBlue = Color(0xFF64ACFF)
val PrimaryContainer = Color(0xFF429EFD)
val SecondaryContainer = Color(0xFF0D2933)
val OnSecondaryContainer = Color(0xFF8DA8B5)

// Atmospheric Depth (Tonal Layering)
val Surface = Color(0xFF041015)                   // Base Layer
val SurfaceContainerLow = Color(0xFF06151C)        // Structural Sections
val SurfaceContainer = Color(0xFF0C222B)           // Intermediate
val SurfaceContainerHigh = Color(0xFF102933)      // Interactive Elements (Cards)
val SurfaceBright = Color(0xFFD0E9F7)             // Used for Glass & Highlights

// Accents
val ElectricTeal = Color(0xFF8EFFE6)              // Success / Focus Mode
val ErrorRed = Color(0xFFE57373)                  // Error states

// Typography
val OnSurface = Color(0xFFD0E9F7)                 // Eye-strain reduction white
val OnSurfaceVariant = Color(0xFF8DA8B5)          // Metadata & Secondary text
val OutlineVariant = Color(0x33D0E9F7)            // Ghost Borders (20% opacity)

// Legacy (Will be migrated)
val DeepNavy = Surface
val DeepSlate = SurfaceContainerLow
val SecondarySurface = SurfaceContainerHigh
val TextWhite = OnSurface
val TextBody = OnSurfaceVariant
val TextHeading = OnSurface
val PrimaryBlueGradient = listOf(PrimaryBlue, PrimaryContainer)
