package com.anhndt.systemdesign.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.anhndt.systemdesign.extensions.contentPadding

@Composable
fun SectionInfo(modifier: Modifier = Modifier, title: String, color: Color? = null) {
    Box(modifier = modifier.contentPadding()) {
        Text(
            text = title,
            modifier = modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium.copy(
                color = color ?: MaterialTheme.colorScheme.primary
            ),
        )
    }
}