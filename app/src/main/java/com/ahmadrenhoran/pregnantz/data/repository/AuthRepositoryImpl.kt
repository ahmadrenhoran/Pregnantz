package com.ahmadrenhoran.pregnantz.data.repository


import com.ahmadrenhoran.pregnantz.core.Utils.toUser
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository
import com.ahmadrenhoran.pregnantz.domain.repository.SignInWithEmailResponse
import com.ahmadrenhoran.pregnantz.domain.repository.SignUpWithEmailResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseDatabase) :
    AuthRepository {
    override suspend fun firebaseSignInWithEmail(
        email: String,
        password: String
    ): SignInWithEmailResponse {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: FirebaseAuthException) {
            Response.Failure(e)
        }
    }

    override suspend fun firebaseSignUpWithEmail(
        email: String,
        password: String
    ): SignUpWithEmailResponse {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            addUserToDatabase()
            Response.Success(true)
        } catch (e: FirebaseAuthException) {
            Response.Failure(e)
        }
    }


    private suspend fun addUserToDatabase() {
        val usersRef = db.getReference("users")
        auth.currentUser?.apply {
            val user = toUser(this)
            usersRef.child(user.uid).setValue(user).await()
        }
    }

    companion object {
        const val TAG = "AuthRepositoryImplLog"
    }
}

