package com.jarves.mh.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.jarves.mh.R

// Brand palette. These names are referenced across the UI layer, so they keep
// their historical values; theme-aware surfaces should prefer
// MaterialTheme.colorScheme.* instead of hard-coded colors.
val PocketOrange = Color(0xFFF28C52)
val PocketBlue = Color(0xFF8EA8FF)
val PocketGreen = Color(0xFF69D69E)
val PocketBackground = Color(0xFF0B0E14)
val PocketSurface = Color(0xFF131821)
val PocketSurfaceVariant = Color(0xFF1B222D)
val PocketOutline = Color(0xFF2A3240)

// Extra accent stops used for gradient fills, glows and chart-like highlights.
val PocketEmber = Color(0xFFFF6A2B)
val PocketAmber = Color(0xFFFFC08A)
val PocketInk = Color(0xFF06080C)
val PocketTeal = Color(0xFF4FD1C5)
val PocketViolet = Color(0xFFA78BFA)

/** Monospace family used by the terminal and any code-shaped surface. */
val PocketMono = FontFamily(
    Font(R.font.jetbrains_mono_regular, FontWeight.Normal),
    Font(R.font.jetbrains_mono_medium, FontWeight.Medium),
    Font(R.font.jetbrains_mono_bold, FontWeight.Bold),
)

/** Reusable gradient brushes for the accent treatment. */
object PocketGradients {
    val accent = Brush.linearGradient(listOf(PocketEmber, PocketOrange, PocketAmber))
    val accentSoft = Brush.linearGradient(listOf(Color(0xFFF2874B), Color(0xFFF2A65A)))
    val cool = Brush.linearGradient(listOf(Color(0xFF4C8DFF), Color(0xFF8EA8FF)))
    val mint = Brush.linearGradient(listOf(Color(0xFF2FBF8F), PocketGreen))
    val violet = Brush.linearGradient(listOf(Color(0xFF7C5CFF), PocketViolet))

    /** Subtle top-down sheen used by glass cards. */
    fun glass(dark: Boolean) = if (dark) {
        Brush.verticalGradient(listOf(Color(0xFF1A2231), Color(0xFF121824)))
    } else {
        Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFFF4F7FC)))
    }
}

private val DarkColors = darkColorScheme(
    primary = Color(0xFF4D6BFE),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF23305F),
    onPrimaryContainer = Color(0xFFDCE3FF),
    secondary = Color(0xFF9AA9D8),
    onSecondary = Color(0xFF141B33),
    secondaryContainer = Color(0xFF232A3D),
    onSecondaryContainer = Color(0xFFDDE3F5),
    tertiary = Color(0xFF69D69E),
    onTertiary = Color(0xFF00391E),
    tertiaryContainer = Color(0xFF12301F),
    onTertiaryContainer = Color(0xFFB9F5D8),
    error = Color(0xFFFF6B6B),
    onError = Color(0xFF3B0A0A),
    errorContainer = Color(0xFF3A1515),
    onErrorContainer = Color(0xFFFFDAD6),
    // DeepSeek flat: page and cards share the same near-black canvas.
    background = Color(0xFF0F0F0F),
    onBackground = Color(0xFFECECEC),
    surface = Color(0xFF0F0F0F),
    onSurface = Color(0xFFECECEC),
    // Muted grey used for the user bubble and inline code chips.
    surfaceVariant = Color(0xFF2A2A2A),
    onSurfaceVariant = Color(0xFF9E9E9E),
    surfaceTint = Color(0xFF4D6BFE),
    inverseSurface = Color(0xFFECECEC),
    inverseOnSurface = Color(0xFF1A1A1A),
    inversePrimary = Color(0xFF2E49C4),
    surfaceDim = Color(0xFF0A0A0A),
    surfaceBright = Color(0xFF2E2E2E),
    surfaceContainerLowest = Color(0xFF0A0A0A),
    surfaceContainerLow = Color(0xFF161616),
    surfaceContainer = Color(0xFF1C1C1C),
    surfaceContainerHigh = Color(0xFF232323),
    surfaceContainerHighest = Color(0xFF2A2A2A),
    outline = Color(0xFF4A4A4A),
    outlineVariant = Color(0xFF2A2A2A),
    scrim = Color(0xFF000000),
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF4D6BFE),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFDCE3FF),
    onPrimaryContainer = Color(0xFF001453),
    secondary = Color(0xFF4457A0),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDCE3FF),
    onSecondaryContainer = Color(0xFF0A1636),
    tertiary = Color(0xFF10714A),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD1F5E3),
    onTertiaryContainer = Color(0xFF00301C),
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF141414),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF141414),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF6B6B6B),
    surfaceTint = Color(0xFF4D6BFE),
    inverseSurface = Color(0xFF2A2A2A),
    inverseOnSurface = Color(0xFFF5F5F5),
    inversePrimary = Color(0xFF9FB0FF),
    surfaceDim = Color(0xFFEDEDED),
    surfaceBright = Color(0xFFFFFFFF),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFFAFAFA),
    surfaceContainer = Color(0xFFF5F5F5),
    surfaceContainerHigh = Color(0xFFF0F0F0),
    surfaceContainerHighest = Color(0xFFEBEBEB),
    outline = Color(0xFFBDBDBD),
    outlineVariant = Color(0xFFE4E4E4),
    scrim = Color(0xFF000000),
)

/** Type scale tuned for dense developer surfaces on phones and tablets. */
val PocketTypography = Typography(
    displayLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 52.sp, lineHeight = 58.sp, letterSpacing = (-1).sp),
    displayMedium = TextStyle(fontWeight = FontWeight.Bold, fontSize = 42.sp, lineHeight = 48.sp, letterSpacing = (-0.6).sp),
    displaySmall = TextStyle(fontWeight = FontWeight.Bold, fontSize = 34.sp, lineHeight = 40.sp, letterSpacing = (-0.4).sp),
    headlineLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp, lineHeight = 36.sp, letterSpacing = (-0.4).sp),
    headlineMedium = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp, lineHeight = 31.sp, letterSpacing = (-0.3).sp),
    headlineSmall = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 21.sp, lineHeight = 27.sp, letterSpacing = (-0.2).sp),
    titleLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 19.sp, lineHeight = 25.sp, letterSpacing = (-0.1).sp),
    titleMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp),
    titleSmall = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.Normal, fontSize = 15.sp, lineHeight = 22.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    bodySmall = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.5.sp, lineHeight = 18.sp),
    labelLarge = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),
    labelMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.2.sp),
    labelSmall = TextStyle(fontWeight = FontWeight.Medium, fontSize = 11.sp, lineHeight = 15.sp, letterSpacing = 0.3.sp),
)

/** Rounded, soft-cornered shape scale (16-24dp for cards). */
val PocketShapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(22.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
)

enum class AppThemeMode { SYSTEM, DARK, LIGHT }

@Composable
fun PocketTheme(themeMode: AppThemeMode = AppThemeMode.SYSTEM, content: @Composable () -> Unit) {
    val isDark = when (themeMode) {
        AppThemeMode.DARK -> true
        AppThemeMode.LIGHT -> false
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !isDark
            insetsController.isAppearanceLightNavigationBars = !isDark
        }
    }

    MaterialTheme(
        colorScheme = if (isDark) DarkColors else LightColors,
        typography = PocketTypography,
        shapes = PocketShapes,
        content = content,
    )
}
