package com.ahmadrenhoran.pregnantz.domain.repository


import com.ahmadrenhoran.pregnantz.domain.model.Response

typealias SignInWithEmailResponse = Response<Boolean>
typealias SignUpWithEmailResponse = Response<Boolean>

interface AuthRepository {

    suspend fun firebaseSignInWithEmail(email: String, password: String): SignInWithEmailResponse

    suspend fun firebaseSignUpWithEmail(email: String, password: String): SignUpWithEmailResponse

}