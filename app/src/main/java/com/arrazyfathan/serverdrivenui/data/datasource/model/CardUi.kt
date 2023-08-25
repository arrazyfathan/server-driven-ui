package com.arrazyfathan.serverdrivenui.data.datasource.model

import com.google.firebase.firestore.PropertyName

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
data class CardUi(
    @get:PropertyName("corner_radius")
    @PropertyName("corner_radius")
    val cornerRadius: Int = 0,

    @get:PropertyName("text_size")
    @PropertyName("text_size")
    val textSize: Int = 0,
)
