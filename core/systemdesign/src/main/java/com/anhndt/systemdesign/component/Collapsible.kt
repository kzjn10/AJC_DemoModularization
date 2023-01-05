package com.anhndt.systemdesign.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.anhndt.systemdesign.R

@Composable
fun Collapsible(modifier: Modifier = Modifier, content: String, style: TextStyle? = null) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .animateContentSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = content,
                style = style ?: LocalTextStyle.current,
                textAlign = TextAlign.Justify,
                maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                overflow = if (!isExpanded) TextOverflow.Ellipsis else TextOverflow.Clip,
            )
            Spacer(modifier = modifier.height(12.dp))
            TextButton(onClick = { isExpanded = !isExpanded }) {
                Text(
                    text =
                    stringResource(id = if (isExpanded) R.string.mfa_show_less else R.string.mfa_show_more),
                    style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}