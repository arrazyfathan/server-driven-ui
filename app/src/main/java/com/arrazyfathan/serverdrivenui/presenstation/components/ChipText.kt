package com.arrazyfathan.serverdrivenui.presenstation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.serverdrivenui.R
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansRegular

/**
 * Created by Ar Razy Fathan Rabbani on 18/06/23.
 */

@Composable
fun ChipText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 10.sp,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontFamily = GoloSansRegular,
        color = Color.White,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(colorResource(id = R.color.white).copy(alpha = 0.2f))
            .padding(horizontal = 16.dp, vertical = 4.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun ChipTextPreview() {
    ChipText(text = "Android")
}
