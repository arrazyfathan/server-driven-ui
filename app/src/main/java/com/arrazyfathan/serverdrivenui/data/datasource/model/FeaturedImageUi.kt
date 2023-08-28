package com.arrazyfathan.serverdrivenui.data.datasource.model

import com.google.firebase.firestore.PropertyName

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
data class FeaturedImageUi(
    @get:PropertyName("article_title")
    @PropertyName("article_title")
    val articleTitle: String = "",

    @get:PropertyName("caption_size")
    @PropertyName("caption_size")
    val captionSize: Int = 0,

    @get:PropertyName("article_title_size")
    @PropertyName("article_title_size")
    val articleTitleSize: Int = 0,

    @get:PropertyName("image_caption")
    @PropertyName("image_caption")
    val imageCaption: String = "",

    @get:PropertyName("image_url")
    @PropertyName("image_url")
    val imageUrl: String = "",

    @get:PropertyName("image_corner_radius")
    @PropertyName("image_corner_radius")
    val imageCornerRadius: Int = 0,

    @get:PropertyName("image_height")
    @PropertyName("image_height")
    val imageHeight: Int = 0,

    @get:PropertyName("image_padding")
    @PropertyName("image_padding")
    val imagePadding: Int = 0,

    @get:PropertyName("blurred_background")
    @PropertyName("blurred_background")
    val blurredBackground: Boolean = false,
)
