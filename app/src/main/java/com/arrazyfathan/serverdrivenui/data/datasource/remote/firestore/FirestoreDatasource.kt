package com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore

import com.arrazyfathan.serverdrivenui.data.datasource.model.CardUi
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
interface FirestoreDatasource {
    suspend fun getCardUi(): Flow<FirestoreResult<CardUi?>>
}
