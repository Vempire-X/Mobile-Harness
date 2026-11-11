package com.jarves.mh.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.ripple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarves.mh.ui.theme.PocketAmber
import com.jarves.mh.ui.theme.PocketGradients
import com.jarves.mh.ui.theme.PocketOrange

/**
 * Shared visual building blocks for the redesigned UI layer.
 *
 * Everything in this file is presentation-only: no business state is created or
 * mutated here, so screens can adopt these components without behavioural drift.
 */

/** Returns a 0.98f -> 1f press animation value for tactile feedback. */
@Composable
fun rememberPressScale(interactionSource: MutableInteractionSource): Float {
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.975f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "press-scale",
    )
    return scale
}

/** A soft, glassy card: gradient sheen, hairline border and a whisper of shadow. */
@Composable
fun PocketGlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    accent: Color? = null,
    onClick: (() -> Unit)? = null,
    contentPadding: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    val dark = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val interaction = remember { MutableInteractionSource() }
    val pressScale = rememberPressScale(interaction)
    val borderColor = accent?.copy(alpha = if (dark) 0.38f else 0.30f)
        ?: MaterialTheme.colorScheme.outlineVariant.copy(alpha = if (dark) 0.85f else 1f)

    Surface(
        modifier = modifier
            .graphicsLayer { scaleX = pressScale; scaleY = pressScale }
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interaction,
                        indication = ripple(),
                        onClick = onClick,
                    )
                } else {
                    Modifier
                },
            ),
        shape = shape,
        color = Color.Transparent,
        border = BorderStroke(1.dp, borderColor),
        tonalElevation = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .background(PocketGradients.glass(dark), shape)
                .then(
                    if (accent != null) {
                        Modifier.background(
                            Brush.linearGradient(
                                listOf(accent.copy(alpha = if (dark) 0.10f else 0.06f), Color.Transparent),
                            ),
                            shape,
                        )
                    } else {
                        Modifier
                    },
                )
                .padding(horizontal = contentPadding, vertical = contentPadding),
            content = content,
        )
    }
}

/** Gradient-tinted icon tile used for headers, list rows and empty states. */
@Composable
fun PocketIconTile(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = PocketOrange,
    size: Dp = 44.dp,
    corner: Dp = 14.dp,
    contentDescription: String? = null,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                Brush.linearGradient(
                    listOf(tint.copy(alpha = 0.26f), tint.copy(alpha = 0.08f)),
                ),
                RoundedCornerShape(corner),
            )
            .border(1.dp, tint.copy(alpha = 0.28f), RoundedCornerShape(corner)),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, contentDescription, tint = tint, modifier = Modifier.size(size * 0.48f))
    }
}

/** Small status pill (running, idle, error, language...). */
@Composable
fun PocketStatusChip(
    text: String,
    modifier: Modifier = Modifier,
    tint: Color = PocketOrange,
    icon: ImageVector? = null,
    filled: Boolean = false,
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = if (filled) tint.copy(alpha = 0.20f) else tint.copy(alpha = 0.11f),
        border = BorderStroke(1.dp, tint.copy(alpha = if (filled) 0.55f else 0.28f)),
        tonalElevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            if (icon != null) {
                Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(12.dp))
            } else {
                Box(Modifier.size(6.dp).background(tint, CircleShape))
            }
            Text(
                text = text,
                color = tint,
                fontSize = 10.5.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

/** Primary action: gradient fill, soft glow, 52dp tall for comfortable touch. */
@Composable
fun PocketPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    height: Dp = 52.dp,
) {
    val interaction = remember { MutableInteractionSource() }
    val pressScale = rememberPressScale(interaction)
    Surface(
        modifier = modifier
            .height(height)
            .graphicsLayer {
                scaleX = pressScale
                scaleY = pressScale
                alpha = if (enabled) 1f else 0.5f
            }
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                enabled = enabled,
                interactionSource = interaction,
                indication = ripple(),
                onClick = onClick,
            ),
        shape = RoundedCornerShape(16.dp),
        color = Color.Transparent,
        shadowElevation = 10.dp,
        tonalElevation = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PocketGradients.accent, RoundedCornerShape(16.dp))
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (loading) {
                    androidx.compose.material3.CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp,
                        color = Color(0xFF2A1206),
                    )
                }
                Text(
                    text = text,
                    color = Color(0xFF2A1206),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (icon != null && !loading) {
                    Icon(icon, contentDescription = null, tint = Color(0xFF2A1206), modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

/** Secondary action: quiet glass surface with a hairline accent border. */
@Composable
fun PocketSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    height: Dp = 52.dp,
) {
    val interaction = remember { MutableInteractionSource() }
    val pressScale = rememberPressScale(interaction)
    Surface(
        modifier = modifier
            .height(height)
            .graphicsLayer { scaleX = pressScale; scaleY = pressScale; alpha = if (enabled) 1f else 0.55f }
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                enabled = enabled,
                interactionSource = interaction,
                indication = ripple(),
                onClick = onClick,
            ),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.55f)),
        tonalElevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (icon != null) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

/** Section heading with optional trailing action. */
@Composable
fun PocketSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            if (subtitle != null) {
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        trailing?.invoke()
    }
}

/**
 * Segmented progress bar for onboarding flows.
 * Segments before [currentStep] read as complete, the current one animates a fill.
 */
@Composable
fun PocketStepProgress(
    currentStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier,
) {
    val stepCount = totalSteps.coerceAtLeast(1)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        repeat(stepCount) { index ->
            val completed = index < currentStep
            val active = index == currentStep
            val fill by animateFloatAsState(
                targetValue = when {
                    completed -> 1f
                    active -> 0.55f
                    else -> 0f
                },
                animationSpec = tween(420),
                label = "step-$index",
            )
            Box(
                Modifier
                    .weight(1f)
                    .height(6.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest),
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(fill)
                        .fillMaxHeight()
                        .background(
                            if (active) PocketGradients.accent else Brush.linearGradient(listOf(PocketOrange, PocketAmber)),
                        ),
                )
            }
        }
    }
}

/** Friendly empty state with an illustrated icon halo and an optional action. */
@Composable
fun PocketEmptyState(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    actionText: String? = null,
    onAction: (() -> Unit)? = null,
) {
    PocketGlassCard(modifier = modifier.fillMaxWidth(), contentPadding = 26.dp) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(
                    Modifier
                        .size(92.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(PocketOrange.copy(alpha = 0.22f), Color.Transparent),
                            ),
                            CircleShape,
                        ),
                )
                PocketIconTile(icon, size = 56.dp, corner = 18.dp)
            }
            Spacer(Modifier.height(6.dp))
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Text(
                description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
            )
            if (actionText != null && onAction != null) {
                Spacer(Modifier.height(8.dp))
                PocketSecondaryButton(text = actionText, onClick = onAction, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

/** Minimal description of one bottom-navigation destination. */
data class PocketNavItem(
    val key: Any,
    val label: String,
    val icon: ImageVector,
)

/**
 * Floating pill bottom navigation with a sliding active indicator.
 * Selection is driven entirely by the caller's state (no internal navigation state).
 */
@Composable
fun PocketFloatingNav(
    items: List<PocketNavItem>,
    selectedKey: Any,
    onSelect: (Any) -> Unit,
    modifier: Modifier = Modifier,
    showBadge: (Any) -> Boolean = { false },
    badgeDescription: String? = null,
) {
    if (items.isEmpty()) return
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        shape = RoundedCornerShape(30.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.97f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.9f)),
        shadowElevation = 20.dp,
        tonalElevation = 0.dp,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
        ) {
            val itemWidth = maxWidth / items.size
            val selectedIndex = items.indexOfFirst { it.key == selectedKey }.coerceAtLeast(0)
            val indicatorOffset by animateDpAsState(
                targetValue = itemWidth * selectedIndex,
                animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessMediumLow),
                label = "nav-indicator",
            )
            Box(
                Modifier
                    .offset(x = indicatorOffset)
                    .width(itemWidth)
                    .height(56.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.22f),
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                            ),
                        ),
                        RoundedCornerShape(24.dp),
                    ),
            )
            Row(Modifier.fillMaxWidth()) {
                items.forEach { item ->
                    val selected = item.key == selectedKey
                    val contentColor by animateColorAsState(
                        targetValue = if (selected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        label = "nav-color",
                    )
                    val iconScale by animateFloatAsState(
                        targetValue = if (selected) 1.08f else 1f,
                        label = "nav-scale",
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .clickable { onSelect(item.key) },
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Box {
                                Icon(
                                    item.icon,
                                    contentDescription = if (selected) item.label else null,
                                    tint = contentColor,
                                    modifier = Modifier
                                        .size(22.dp)
                                        .graphicsLayer { scaleX = iconScale; scaleY = iconScale },
                                )
                                if (showBadge(item.key)) {
                                    Badge(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .offset(x = 6.dp, y = (-4).dp),
                                    )
                                }
                            }
                            Spacer(Modifier.height(3.dp))
                            Text(
                                text = item.label,
                                color = contentColor,
                                fontSize = 10.sp,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                maxLines = 1,
                            )
                        }
                    }
                }
            }
        }
    }
}
