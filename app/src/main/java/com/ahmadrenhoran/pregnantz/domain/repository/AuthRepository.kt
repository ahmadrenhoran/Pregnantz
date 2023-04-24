package com.ahmadrenhoran.pregnantz.domain.repository


import android.net.Uri
import com.ahmadrenhoran.pregnantz.domain.model.Response

typealias SignInWithEmailResponse = Response<Boolean>
typealias SignUpWithEmailResponse = Response<Boolean>
typealias AddImageToStorageResponse = Response<Uri>
typealias AddDataUserToDatabaseResponse = Response<Boolean>

interface AuthRepository {

    suspend fun firebaseSignInWithEmail(email: String, password: String): SignInWithEmailResponse

    suspend fun firebaseSignUpWithEmail(email: String, password: String): SignUpWithEmailResponse

    suspend fun firebaseAddImageToStorage(imageUri: Uri): AddImageToStorageResponse

    suspend fun firebaseAddDataUserToDatabase(name: String, age: String, dueDate: String, imageUri: Uri): AddDataUserToDatabaseResponse

}