package com.arrazyfathan.serverdrivenui.utils

import androidx.compose.ui.graphics.Color

/**
 * Created by Ar Razy Fathan Rabbani on 28/08/23.
 */

object ColorUtils {

    fun getColorFromString(color: String): Color {
        return when (color) {
            "red" -> Color.Red
            "black" -> Color.Black
            "blue" -> Color.Blue
            "yellow" -> Color.Yellow
            else -> Color.White
        }
    }
}
