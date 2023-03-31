package com.ahmadrenhoran.pregnantz.di

import com.ahmadrenhoran.pregnantz.data.repository.AuthRepositoryImpl
import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.AuthUseCases
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.SignInWithEmail
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.SignUpWithEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirebaseDatabase() = Firebase.database

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        db: FirebaseDatabase
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        db = db
    )

    @Provides
    fun provideAuthUseCases(
        repository: AuthRepositoryImpl
    ) = AuthUseCases(
        SignInWithEmail(repository),
        SignUpWithEmail(repository)
    )
}