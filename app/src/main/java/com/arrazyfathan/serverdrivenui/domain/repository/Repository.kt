package com.arrazyfathan.serverdrivenui.domain.repository

import com.arrazyfathan.serverdrivenui.data.datasource.model.CardLinksUi
import com.arrazyfathan.serverdrivenui.data.datasource.model.ContentUi
import com.arrazyfathan.serverdrivenui.data.datasource.model.FeaturedImageUi
import com.arrazyfathan.serverdrivenui.data.datasource.model.TopAppBarUi
import com.arrazyfathan.serverdrivenui.data.datasource.remote.Resources
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ar Razy Fathan Rabbani on 24/08/23.
 */
interface Repository {
    fun getTopAppBarUi(): Flow<Resources<TopAppBarUi?>>
    fun getFeaturedImageUi(): Flow<Resources<FeaturedImageUi?>>
    fun getContent(): Flow<Resources<ContentUi?>>
    fun getCardLinksUi(): Flow<Resources<CardLinksUi?>>
}
