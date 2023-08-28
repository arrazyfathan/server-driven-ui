package com.arrazyfathan.serverdrivenui.data.datasource.model

import androidx.compose.ui.graphics.Color
import com.google.firebase.firestore.PropertyName

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
data class TopAppBarUi(
    @get:PropertyName("share_button_flags")
    @PropertyName("share_button_flags")
    val shareButtonFlags: Boolean = true,

    @get:PropertyName("text_color")
    @PropertyName("text_color")
    val textColor: String = "",

    @get:PropertyName("icon_color")
    @PropertyName("icon_color")
    val iconColor: String = "",

    @get:PropertyName("text_size")
    @PropertyName("text_size")
    val textSize: Int = 0,

    @get:PropertyName("icon_size")
    @PropertyName("icon_size")
    val iconSize: Int = 0,

    @get:PropertyName("title")
    @PropertyName("title")
    val title: String = "",

    @get:PropertyName("text_color_string")
    @PropertyName("text_color_string")
    val textColorStrin: String = "",
)
