package com.arrazyfathan.serverdrivenui.data.datasource.remote

import java.lang.Exception

/**
 * Created by Ar Razy Fathan Rabbani on 24/08/23.
 */
sealed class Resources<out T> {
    data class Success<T>(val data: T) : Resources<T>()
    data class Failure(val exception: Exception) : Resources<Nothing>()
}
