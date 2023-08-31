package com.arrazyfathan.serverdrivenui.data.datasource.model

import com.google.firebase.firestore.PropertyName

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
data class CardLinksUi(
    @get:PropertyName("card_background_color")
    @PropertyName("card_background_color")
    val cardBackgroundColor: String = "",

    @get:PropertyName("card_border_color")
    @PropertyName("card_border_color")
    val cardBorderColor: String = "",

    @get:PropertyName("card_border_size")
    @PropertyName("card_border_size")
    val cardBorderSize: Double = 0.0,

    @get:PropertyName("corner_radius")
    @PropertyName("corner_radius")
    val cornerRadius: Int = 0,

    @get:PropertyName("icon_share_size")
    @PropertyName("icon_share_size")
    val iconShareSize: Int = 0,

    @get:PropertyName("icon_share_tint")
    @PropertyName("icon_share_tint")
    val iconShareTint: String = "",

    @get:PropertyName("image_links_corner_radius")
    @PropertyName("image_links_corner_radius")
    val imageLinksCornerRadius: Int = 0,

    @get:PropertyName("image_links_size")
    @PropertyName("image_links_size")
    val imageLinksSize: Int = 0,

    @get:PropertyName("link_label")
    @PropertyName("link_label")
    val linkLabel: String = "",

    @get:PropertyName("link_label_text_color")
    @PropertyName("link_label_text_color")
    val linkLabelTextColor: String = "",

    @get:PropertyName("link_label_text_size")
    @PropertyName("link_label_text_size")
    val linkLabelTextSize: Int = 0,

    @get:PropertyName("link_text_size")
    @PropertyName("link_text_size")
    val linkTextSize: Int = 0,

    @get:PropertyName("links_text_color")
    @PropertyName("links_text_color")
    val linksTextColor: String = "",

    @get:PropertyName("links_title")
    @PropertyName("links_title")
    val linksTitle: String = "",

)
