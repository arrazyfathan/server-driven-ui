package com.arrazyfathan.serverdrivenui.utils

import androidx.compose.ui.text.style.TextAlign

/**
 * Created by Ar Razy Fathan Rabbani on 30/08/23.
 */
object TextAlignUtils {

    operator fun invoke(align: String): TextAlign {
        return when (align) {
            "left" -> TextAlign.Left
            "right" -> TextAlign.Right
            "center" -> TextAlign.Center
            "justify" -> TextAlign.Justify
            "start" -> TextAlign.Start
            "end" -> TextAlign.End
            else -> TextAlign.Start
        }
    }
}
