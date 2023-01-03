package com.anhndt.systemdesign.extensions

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Extension for shimmer loading
 */
fun Modifier.shimmerBackground(shape: Shape = RectangleShape): Modifier = composed {
    val transition = rememberInfiniteTransition()

    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
            RepeatMode.Restart
        ),
    )
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.4f),
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation, translateAnimation),
        end = Offset(translateAnimation + 100f, translateAnimation + 100f),
        tileMode = TileMode.Mirror,
    )
    return@composed this.then(background(brush, shape))
}

fun Modifier.backdropGradientBackground(colors: List<Color>? = null): Modifier = composed {
    return@composed this.then(
        background(
            brush = Brush.verticalGradient(
                colors = colors ?: listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.surfaceVariant,
                )
            )
        )
    )
}

fun Modifier.contentPadding(
    horizontal: Dp = 12.dp,
    vertical: Dp = 0.dp
): Modifier = composed {
    return@composed this.then(
        padding(horizontal = horizontal, vertical = vertical)
    )
}

inline fun Modifier.ifTrue(predicate: Boolean, builder: () -> Modifier) =
    then(if (predicate) builder() else Modifier)