package com.anhndt.systemdesign.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MfaOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    border: BorderStroke? = MfaButtonDefaults.outlinedButtonBorder(enabled = enabled),
    colors: ButtonColors = MfaButtonDefaults.outlinedButtonColors(),
    contentPadding: PaddingValues = MfaButtonDefaults.buttonContentPadding(small = small),
    content: @Composable RowScope.() -> Unit
) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = MfaButtonDefaults.SmallButtonHeight)
        } else {
            modifier
        },
        enabled = enabled,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

object MfaButtonDefaults {
    val SmallButtonHeight = 32.dp
    const val DisabledButtonContainerAlpha = 0.12f
    const val DisabledButtonContentAlpha = 0.38f
    val ButtonHorizontalPadding = 24.dp
    val ButtonHorizontalIconPadding = 16.dp
    val ButtonVerticalPadding = 8.dp
    val SmallButtonHorizontalPadding = 16.dp
    val SmallButtonHorizontalIconPadding = 12.dp
    val SmallButtonVerticalPadding = 7.dp
    val ButtonContentSpacing = 8.dp
    val ButtonIconSize = 18.dp
    fun buttonContentPadding(
        small: Boolean,
        leadingIcon: Boolean = false,
        trailingIcon: Boolean = false
    ): PaddingValues {
        return PaddingValues(
            start = when {
                small && leadingIcon -> SmallButtonHorizontalIconPadding
                small -> SmallButtonHorizontalPadding
                leadingIcon -> ButtonHorizontalIconPadding
                else -> ButtonHorizontalPadding
            },
            top = if (small) SmallButtonVerticalPadding else ButtonVerticalPadding,
            end = when {
                small && trailingIcon -> SmallButtonHorizontalIconPadding
                small -> SmallButtonHorizontalPadding
                trailingIcon -> ButtonHorizontalIconPadding
                else -> ButtonHorizontalPadding
            },
            bottom = if (small) SmallButtonVerticalPadding else ButtonVerticalPadding
        )
    }

    @Composable
    fun filledButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.onBackground,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = DisabledButtonContainerAlpha
        ),
        disabledContentColor: Color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun outlinedButtonBorder(
        enabled: Boolean,
        width: Dp = 1.dp,
        color: Color = MaterialTheme.colorScheme.onBackground,
        disabledColor: Color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = DisabledButtonContainerAlpha
        )
    ): BorderStroke = BorderStroke(
        width = width,
        color = if (enabled) color else disabledColor
    )

    @Composable
    fun outlinedButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.outlinedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun textButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.textButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}