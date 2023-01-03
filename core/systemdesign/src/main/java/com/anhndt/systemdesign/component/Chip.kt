package com.anhndt.systemdesign.component

import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoChip(label: String?) {
    AssistChip(
        onClick = {},
        label = { Text(text = label ?: "") },
        leadingIcon = {}
    )


}