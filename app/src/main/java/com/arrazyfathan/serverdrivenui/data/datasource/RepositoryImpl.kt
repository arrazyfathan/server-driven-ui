package com.arrazyfathan.serverdrivenui.data.datasource

import com.arrazyfathan.serverdrivenui.data.datasource.model.CardUi
import com.arrazyfathan.serverdrivenui.data.datasource.remote.Resources
import com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore.FirestoreDatasource
import com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore.FirestoreResult
import com.arrazyfathan.serverdrivenui.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Ar Razy Fathan Rabbani on 24/08/23.
 */
class RepositoryImpl @Inject constructor(
    private val firestoreDatasource: FirestoreDatasource,
) : Repository {

    override fun getCardUi(): Flow<Resources<CardUi?>> {
        return flow {
            firestoreDatasource.getCardUi().collect { result ->
                try {
                    when (result) {
                        is FirestoreResult.Success -> emit(Resources.Success(result.data))

                        is FirestoreResult.Failure -> emit(Resources.Failure(result.exception))
                    }
                } catch (e: Exception) {
                    emit(Resources.Failure(e))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
