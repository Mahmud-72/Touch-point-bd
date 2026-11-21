package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.data.ThemeMode

private val DarkColorScheme = darkColorScheme(
    primary = EmeraldMint,
    onPrimary = OnEmerald,
    primaryContainer = EmeraldContainer,
    onPrimaryContainer = EmeraldMint,
    secondary = ElectricCyan,
    onSecondary = OnCyan,
    secondaryContainer = CyanContainer,
    onSecondaryContainer = ElectricCyan,
    tertiary = AmberWarning,
    onTertiary = Color(0xFF261800),
    tertiaryContainer = AmberContainer,
    onTertiaryContainer = AmberWarning,
    error = CoralLocked,
    onError = Color(0xFF33060F),
    errorContainer = CoralContainer,
    onErrorContainer = CoralLocked,
    background = ObsidianBg,
    onBackground = TextPrimary,
    surface = ObsidianSurface,
    onSurface = TextPrimary,
    surfaceVariant = ObsidianSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    surfaceContainerHigh = ObsidianSurfaceHigh,
    outline = ObsidianOutline,
    outlineVariant = ObsidianOutlineVariant
)

private val LightColorScheme = lightColorScheme(
    primary = LightEmerald,
    onPrimary = Color.White,
    primaryContainer = LightEmeraldContainer,
    onPrimaryContainer = Color(0xFF064E3B),
    secondary = LightCyan,
    onSecondary = Color.White,
    secondaryContainer = LightCyanContainer,
    onSecondaryContainer = Color(0xFF0C4A6E),
    tertiary = Color(0xFFD97706),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFEF3C7),
    onTertiaryContainer = Color(0xFF78350F),
    error = Color(0xFFE11D48),
    onError = Color.White,
    errorContainer = Color(0xFFFFE4E6),
    onErrorContainer = Color(0xFF881337),
    background = LightBg,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightTextSecondary,
    surfaceContainerHigh = Color(0xFFE2E8F0),
    outline = LightOutline,
    outlineVariant = Color(0xFFE2E8F0)
)

val AppShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun TouchShieldTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        isDark -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
