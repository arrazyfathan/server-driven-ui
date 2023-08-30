package com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore

import com.arrazyfathan.serverdrivenui.data.datasource.model.ContentUi
import com.arrazyfathan.serverdrivenui.data.datasource.model.FeaturedImageUi
import com.arrazyfathan.serverdrivenui.data.datasource.model.TopAppBarUi
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

    override suspend fun getTopAppBarUi(): Flow<FirestoreResult<TopAppBarUi?>> = callbackFlow {
        val listener = EventListener<DocumentSnapshot> { snapshot, error ->
            if (error != null) {
                trySend(FirestoreResult.Failure(error))
                // cancel() or cancel
                return@EventListener
            }

            if (snapshot != null && snapshot.exists()) {
                val data = snapshot.toObject<TopAppBarUi>()
                trySend(FirestoreResult.Success(data))
            } else {
                trySend(FirestoreResult.Failure(Exception("Snapshot is not exist")))
            }
        }
        val registration = firestore
            .collection(Constants.HOME_SCREEN_COLLECTION)
            .document(Constants.TOP_APP_BAR)
            .addSnapshotListener(listener)

        awaitClose { registration.remove() }
    }

    override suspend fun getFeaturedImageUi(): Flow<FirestoreResult<FeaturedImageUi?>> =
        callbackFlow {
            val listener = EventListener<DocumentSnapshot> { snapshot, error ->
                if (error != null) {
                    trySend(FirestoreResult.Failure(error))
                    // cancel() or cancel
                    return@EventListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val data = snapshot.toObject<FeaturedImageUi>()
                    trySend(FirestoreResult.Success(data))
                } else {
                    trySend(FirestoreResult.Failure(Exception("Snapshot is not exist")))
                }
            }
            val registration = firestore
                .collection(Constants.HOME_SCREEN_COLLECTION)
                .document(Constants.FEATURED_IMAGE)
                .addSnapshotListener(listener)

            awaitClose { registration.remove() }
        }

    override suspend fun getContentUi(): Flow<FirestoreResult<ContentUi?>> =
        callbackFlow {
            val listener = EventListener<DocumentSnapshot> { snapshot, error ->
                if (error != null) {
                    trySend(FirestoreResult.Failure(error))
                    // cancel() or cancel
                    return@EventListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val data = snapshot.toObject<ContentUi>()
                    trySend(FirestoreResult.Success(data))
                } else {
                    trySend(FirestoreResult.Failure(Exception("Snapshot is not exist")))
                }
            }
            val registration = firestore
                .collection(Constants.HOME_SCREEN_COLLECTION)
                .document(Constants.CONTENT)
                .addSnapshotListener(listener)

            awaitClose { registration.remove() }
        }
}
