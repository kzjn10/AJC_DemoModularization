package com.anhndt.systemdesign.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle

object MovieIcons {
    val AccountCircle = Icons.Outlined.AccountCircle
}


/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}