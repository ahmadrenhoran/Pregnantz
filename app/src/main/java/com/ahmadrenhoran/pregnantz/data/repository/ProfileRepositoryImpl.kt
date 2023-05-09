package com.ahmadrenhoran.pregnantz.data.repository

import android.net.Uri
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.ahmadrenhoran.pregnantz.domain.model.Weight
import com.ahmadrenhoran.pregnantz.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseDatabase): ProfileRepository {


    private val uid = auth.currentUser?.uid.toString()
    private val usersRef = db.reference.child("users").child(uid)
    override suspend fun signOut(): Response<Boolean> {
        return try {
            auth.signOut()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getUserData(): Response<User> {
        return try {
            val user = usersRef.get().await().getValue<User>()!!
            Response.Success(user)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun updateUserData(user: User): Response<Boolean> {
        return try {
            val profileUpdates = userProfileChangeRequest {
                displayName = user.name
                photoUri = Uri.parse(user.photoUrl)

            }

            usersRef.setValue(user).await()
            auth.currentUser!!.updateProfile(profileUpdates).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}