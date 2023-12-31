package com.arrazyfathan.serverdrivenui.presenstation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.serverdrivenui.R
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansRegular
import com.arrazyfathan.serverdrivenui.utils.ColorUtils.getColorFromString

/**
 * Created by Ar Razy Fathan Rabbani on 18/06/23.
 */

@Composable
fun ChipText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 10.sp,
    textColor: String,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontFamily = GoloSansRegular,
        color = getColorFromString(textColor),
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(colorResource(id = R.color.white).copy(alpha = 0.2f))
            .padding(horizontal = 16.dp, vertical = 4.dp),
    )
}
