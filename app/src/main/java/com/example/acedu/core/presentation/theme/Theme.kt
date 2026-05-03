package com.example.acedu.core.presentation.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = Surface,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = Surface,
    secondary = SecondaryContainer,
    onSecondary = OnSecondaryContainer,
    background = Surface,
    onBackground = OnSurface,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceContainerLow,
    onSurfaceVariant = OnSurfaceVariant,
    error = ErrorRed,
    outline = OutlineVariant,
    outlineVariant = OutlineVariant
)

val LocalAceduColors = staticCompositionLocalOf { AceduColors() }
val LocalAceduDimens = staticCompositionLocalOf { Dimensions() }
val LocalAceduShapes = staticCompositionLocalOf { AceduCustomShapes() }
val LocalAceduTextStyles = staticCompositionLocalOf { AceduTextStyles() }

object AceduTheme {
    val colors: AceduColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAceduColors.current

    val dimens: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalAceduDimens.current

    val shapes: AceduCustomShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAceduShapes.current

    val textStyles: AceduTextStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalAceduTextStyles.current
}

@Composable
fun AceduTheme(
    colors: AceduColors = AceduColors(),
    dimens: Dimensions = Dimensions(),
    shapes: AceduCustomShapes = AceduCustomShapes(),
    textStyles: AceduTextStyles = AceduTextStyles(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = false
            insetsController.isAppearanceLightNavigationBars = false
        }
    }

    CompositionLocalProvider(
        LocalAceduColors provides colors,
        LocalAceduDimens provides dimens,
        LocalAceduShapes provides shapes,
        LocalAceduTextStyles provides textStyles
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = Typography,
            shapes = AceduShapes,
            content = content
        )
    }
}
