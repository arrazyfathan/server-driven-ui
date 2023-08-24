package com.arrazyfathan.serverdrivenui.domain.repository

import com.arrazyfathan.serverdrivenui.data.datasource.model.CardUi
import com.arrazyfathan.serverdrivenui.data.datasource.remote.Resources
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ar Razy Fathan Rabbani on 24/08/23.
 */
interface Repository {
    fun getCardUi(): Flow<Resources<CardUi?>>
}
