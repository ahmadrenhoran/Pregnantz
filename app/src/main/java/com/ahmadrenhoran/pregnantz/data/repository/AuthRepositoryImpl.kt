package com.ahmadrenhoran.pregnantz.data.repository


import android.net.Uri
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.Utils.toUser
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.domain.repository.*
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseDatabase,
    private val storage: FirebaseStorage
) :
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

    override suspend fun firebaseAddImageToStorage(imageUri: Uri): AddImageToStorageResponse {
        return try {
            val uid = auth.currentUser?.uid
            val downloadUrl = storage.reference
                .child(Constants.STORAGE_PICTURES).child(Constants.STORAGE_PROFILE_PICTURES).child(uid ?: "none")
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            Response.Success(downloadUrl)
        } catch (e: FirebaseException) {
            Response.Failure(e)
        }
    }

    override suspend fun firebaseAddDataUserToDatabase(user: User): AddDataUserToDatabaseResponse {
        return try {
            val usersRef = db.getReference(Constants.DB_USERS_REF)
            val profileUpdates = userProfileChangeRequest {
                displayName = user.name
                photoUri = Uri.parse(user.photoUrl)
            }
            auth.currentUser?.apply {
                usersRef.child(user.uid).setValue(user).await() // to DB
                updateProfile(profileUpdates) // to Auth
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }


    private suspend fun addUserToDatabase() {
        val usersRef = db.getReference(Constants.DB_USERS_REF)
        auth.currentUser?.apply {
            val user = toUser(this)
            usersRef.child(user.uid).setValue(user).await()
        }
    }


}

