package com.odin.yaki.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Ported 1:1 from yaki-final-mockup.html :root
val Bg = Color(0xFF0A0A0D)
val Bg2 = Color(0xFF0E0E12)
val Panel = Color(0xFF141419)
val Panel2 = Color(0xFF1A1A21)
val Inset = Color(0xFF101014)
val Line = Color(0xFF232329)
val Line2 = Color(0xFF2C2C34)
val Ink = Color(0xFFF4F2EE)
val Mut = Color(0xFF8E8E98)
val Dim = Color(0xFF5B5B66)
val Acc = Color(0xFFFF8A2A)
val Acc2 = Color(0xFFFFB25E)
val Ok = Color(0xFF6FD48B)

private val DarkColors = darkColorScheme(
    primary = Acc,
    onPrimary = Color(0xFF1A0E02),
    secondary = Acc2,
    onSecondary = Color(0xFF1A0E02),
    tertiary = Ok,
    background = Bg,
    onBackground = Ink,
    surface = Panel,
    onSurface = Ink,
    surfaceVariant = Panel2,
    onSurfaceVariant = Mut,
    outline = Line2,
    outlineVariant = Line,
)

@Composable
fun YakiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = YakiTypography,
        content = content
    )
}
