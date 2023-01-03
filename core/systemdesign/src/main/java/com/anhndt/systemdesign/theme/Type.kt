package com.anhndt.systemdesign.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.anhndt.systemdesign.R


private val Lato = FontFamily(
    Font(R.font.lato_regular),
    Font(R.font.lato_bold, FontWeight.W500),
    Font(R.font.lato_light, FontWeight.W400)
)

val defaultTextStyle = TextStyle(
    fontFamily = Lato,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

internal val MovieTypography = Typography(
    displayLarge = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = defaultTextStyle.copy(
        fontWeight = FontWeight.W700,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = defaultTextStyle.copy(
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),
    titleSmall = defaultTextStyle.copy(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = defaultTextStyle.copy(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = defaultTextStyle.copy(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.W500,
        fontSize = 10.sp,
        lineHeight = 16.sp
    )
)