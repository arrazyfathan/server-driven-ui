package com.arrazyfathan.serverdrivenui.presenstation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansRegular
import com.arrazyfathan.serverdrivenui.utils.ColorUtils
import com.arrazyfathan.serverdrivenui.utils.TextAlignUtils

/**
 * Created by Ar Razy Fathan Rabbani on 30/08/23.
 */

@Composable
fun ExpandingText(
    modifier: Modifier = Modifier,
    text: String,
    textSize: Int = 14,
    textColor: String = "white",
    textAlign: String = "left",
    expandable: Boolean = true,
    collapsedMaxLines: Int = 14,
    expandedMaxLines: Int = Int.MAX_VALUE,
) {
    var canTextExpand by remember(text) { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }

    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        color = ColorUtils.getColorFromString(textColor),
        fontSize = textSize.sp,
        textAlign = TextAlignUtils(textAlign),
        fontFamily = GoloSansRegular,
        maxLines = if (expanded) expandedMaxLines else collapsedMaxLines,
        modifier = Modifier
            .clickable(
                enabled = expandable && canTextExpand,
                onClick = { expanded = !expanded },
            )
            .animateContentSize(animationSpec = spring())
            .then(modifier),
        onTextLayout = {
            if (!expanded) {
                canTextExpand = it.hasVisualOverflow
            }
        },
    )
}
