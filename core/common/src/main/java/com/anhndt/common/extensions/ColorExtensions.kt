package com.anhndt.common.extensions

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun Color.Companion.randomColor() =
    Color(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))