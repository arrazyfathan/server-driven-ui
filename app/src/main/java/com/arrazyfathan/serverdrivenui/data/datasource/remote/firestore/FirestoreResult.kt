package com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore

/**
 * Created by Ar Razy Fathan Rabbani on 24/08/23.
 */
sealed class FirestoreResult<out T> {
    data class Success<T>(val data: T) : FirestoreResult<T>()
    data class Failure(val exception: Exception) : FirestoreResult<Nothing>()
}
