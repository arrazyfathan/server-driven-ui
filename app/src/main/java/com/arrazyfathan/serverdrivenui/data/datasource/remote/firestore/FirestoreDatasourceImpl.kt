package com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore

import com.arrazyfathan.serverdrivenui.data.datasource.model.CardUi
import com.arrazyfathan.serverdrivenui.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * Created by Ar Razy Fathan Rabbani on 23/08/23.
 */
class FirestoreDatasourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : FirestoreDatasource {

    override suspend fun getCardUi(): Flow<FirestoreResult<CardUi?>> = callbackFlow {
        val listener =
            firestore.collection(Constants.CARD_UI_COLLECTION).document(Constants.CARD_DOCUMENT)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        trySend(FirestoreResult.Failure(e))
                        return@addSnapshotListener
                    }

                    if (snapshot != null && snapshot.exists()) {
                        val card = snapshot.toObject<CardUi>()
                        trySend(FirestoreResult.Success(card))
                    } else {
                        trySend(FirestoreResult.Failure(Exception("Snapshot is not exist")))
                    }
                }

        awaitClose { listener.remove() }
    }
}
