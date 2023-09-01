package com.arrazyfathan.serverdrivenui.data.datasource.model

import com.google.firebase.firestore.PropertyName

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
data class FooterUi(
    @get:PropertyName("type")
    @PropertyName("type")
    val componentType: String = "",

)
