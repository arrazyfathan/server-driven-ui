package com.arrazyfathan.serverdrivenui.data.datasource.model

import com.google.firebase.firestore.PropertyName

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
data class ContentUi(
    @get:PropertyName("text_align")
    @PropertyName("text_align")
    val textAlign: String = "",

    @get:PropertyName("text_color")
    @PropertyName("text_color")
    val textColor: String = "",

    @get:PropertyName("text_size")
    @PropertyName("text_size")
    val textSize: Int = 0,
)
