package uk.co.dancingganesh.pocketvedas

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ── Warm parchment palette matching iOS ────────────────────────────────────────

private val ParchmentLight = Color(0xFFF5F0E7)
private val ParchmentDark = Color(0xFFF9F3ED)
val AccentGreen = Color(0xFF0B775E)
val LinkRed = Color(0xFF7E2B23)
val WarmOrange = Color(0xFFE8A85C)

private val LightColorScheme = lightColorScheme(
    primary = AccentGreen,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC8E6DB),
    onPrimaryContainer = AccentGreen,
    secondary = WarmOrange,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFF0DB),
    onSecondaryContainer = Color(0xFF5C3D00),
    tertiary = LinkRed,
    onTertiary = Color.White,
    background = ParchmentLight,
    onBackground = Color(0xFF1F1D1A),
    surface = ParchmentDark,
    onSurface = Color(0xFF1F1D1A),
    surfaceVariant = Color(0xFFF0E8DA),
    onSurfaceVariant = Color(0xFF4A4540),
    outline = Color(0xFFD4C9B8),
    outlineVariant = Color(0xFFE8DFD1),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8DD4B8),
    onPrimary = Color(0xFF003828),
    primaryContainer = AccentGreen,
    onPrimaryContainer = Color.White,
    secondary = Color(0xFFFFCC80),
    onSecondary = Color(0xFF3D2200),
    background = Color.Black,
    onBackground = Color(0xFFF7F2EA),
    surface = Color.Black,
    onSurface = Color(0xFFF7F2EA),
    surfaceVariant = Color(0xFF1F1D1A),
    onSurfaceVariant = Color(0xFFCCC5B8),
    outline = Color(0xFF4A4540),
    outlineVariant = Color(0xFF363330),
)

@Composable
fun PocketVedasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
