package com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore

import com.arrazyfathan.serverdrivenui.data.datasource.model.CardUi
import com.arrazyfathan.serverdrivenui.utils.Constants
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
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
        val listener = EventListener<DocumentSnapshot> { snapshot, error ->
            if (error != null) {
                trySend(FirestoreResult.Failure(error))
                // cancel() or cancel
                return@EventListener
            }

            if (snapshot != null && snapshot.exists()) {
                val card = snapshot.toObject<CardUi>()
                trySend(FirestoreResult.Success(card))
            } else {
                trySend(FirestoreResult.Failure(Exception("Snapshot is not exist")))
            }
        }
        val registration = firestore
            .collection(Constants.HOME_SCREEN_COLLECTION)
            .document(Constants.HOME_CARD)
            .addSnapshotListener(listener)

        awaitClose { registration.remove() }
    }
}
