package com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore

import com.arrazyfathan.serverdrivenui.data.datasource.model.CardLinksUi
import com.arrazyfathan.serverdrivenui.data.datasource.model.ContentUi
import com.arrazyfathan.serverdrivenui.data.datasource.model.FeaturedImageUi
import com.arrazyfathan.serverdrivenui.data.datasource.model.TopAppBarUi
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
interface FirestoreDatasource {
    suspend fun getTopAppBarUi(): Flow<FirestoreResult<TopAppBarUi?>>
    suspend fun getFeaturedImageUi(): Flow<FirestoreResult<FeaturedImageUi?>>
    suspend fun getContentUi(): Flow<FirestoreResult<ContentUi?>>
    suspend fun getCardLinksUi(): Flow<FirestoreResult<CardLinksUi?>>
}
