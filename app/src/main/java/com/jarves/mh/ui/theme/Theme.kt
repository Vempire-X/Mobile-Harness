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
    primary = Color(0xFFFF9152),
    onPrimary = Color(0xFF2A1206),
    primaryContainer = Color(0xFF4A2410),
    onPrimaryContainer = Color(0xFFFFDAC6),
    secondary = PocketBlue,
    onSecondary = Color(0xFF08204F),
    secondaryContainer = Color(0xFF1D2A4D),
    onSecondaryContainer = Color(0xFFD8E2FF),
    tertiary = PocketGreen,
    onTertiary = Color(0xFF00391E),
    tertiaryContainer = Color(0xFF0F3D2A),
    onTertiaryContainer = Color(0xFFB9F5D8),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF5C1119),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF090C12),
    onBackground = Color(0xFFE7EDF5),
    surface = Color(0xFF10151E),
    onSurface = Color(0xFFE7EDF5),
    surfaceVariant = Color(0xFF1A212C),
    onSurfaceVariant = Color(0xFFA2ACBB),
    surfaceTint = Color(0xFFFF9152),
    inverseSurface = Color(0xFFE7EDF5),
    inverseOnSurface = Color(0xFF171C24),
    inversePrimary = Color(0xFFB4470F),
    surfaceDim = Color(0xFF090C12),
    surfaceBright = Color(0xFF252D3A),
    surfaceContainerLowest = Color(0xFF0B0F16),
    surfaceContainerLow = Color(0xFF121821),
    surfaceContainer = Color(0xFF161D28),
    surfaceContainerHigh = Color(0xFF1B2330),
    surfaceContainerHighest = Color(0xFF212A38),
    outline = Color(0xFF3B4554),
    outlineVariant = Color(0xFF262F3C),
    scrim = Color(0xFF000000),
)

private val LightColors = lightColorScheme(
    primary = Color(0xFFB4470F),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFE2D1),
    onPrimaryContainer = Color(0xFF3B1305),
    secondary = Color(0xFF2F5FCC),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDCE5FF),
    onSecondaryContainer = Color(0xFF0B1F4A),
    tertiary = Color(0xFF10714A),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD1F5E3),
    onTertiaryContainer = Color(0xFF00301C),
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),
    background = Color(0xFFF7F9FC),
    onBackground = Color(0xFF141A22),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF141A22),
    surfaceVariant = Color(0xFFEDF1F7),
    onSurfaceVariant = Color(0xFF525E6D),
    surfaceTint = Color(0xFFB4470F),
    inverseSurface = Color(0xFF2A313B),
    inverseOnSurface = Color(0xFFF1F4F9),
    inversePrimary = Color(0xFFFFB68D),
    surfaceDim = Color(0xFFE4E9F2),
    surfaceBright = Color(0xFFFFFFFF),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFFBFCFE),
    surfaceContainer = Color(0xFFF3F6FB),
    surfaceContainerHigh = Color(0xFFEDF2F8),
    surfaceContainerHighest = Color(0xFFE7EDF5),
    outline = Color(0xFFBAC4D1),
    outlineVariant = Color(0xFFDFE5EE),
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
