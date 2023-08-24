package com.arrazyfathan.serverdrivenui.di

import com.arrazyfathan.serverdrivenui.data.datasource.RepositoryImpl
import com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore.FirestoreDatasource
import com.arrazyfathan.serverdrivenui.data.datasource.remote.firestore.FirestoreDatasourceImpl
import com.arrazyfathan.serverdrivenui.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ar Razy Fathan Rabbani on 24/08/23.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractionModule {

    @Binds
    @Singleton
    abstract fun bindFirestoreDatasorce(firestoreDatasourceImpl: FirestoreDatasourceImpl): FirestoreDatasource

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}
