package com.ahmadrenhoran.pregnantz.di

import com.ahmadrenhoran.pregnantz.BuildConfig
import com.ahmadrenhoran.pregnantz.data.remote.ArticleApi
import com.ahmadrenhoran.pregnantz.data.repository.ArticleRepositoryImpl
import com.ahmadrenhoran.pregnantz.data.repository.AuthRepositoryImpl
import com.ahmadrenhoran.pregnantz.data.repository.HospitalLocationRepositoryImpl
import com.ahmadrenhoran.pregnantz.domain.repository.ArticleRepository
import com.ahmadrenhoran.pregnantz.domain.repository.AuthRepository
import com.ahmadrenhoran.pregnantz.domain.repository.HospitalLocationRepository
import com.ahmadrenhoran.pregnantz.domain.usecase.article.ArticleUseCases
import com.ahmadrenhoran.pregnantz.domain.usecase.article.GetArticles
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.*
import com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation.GetDetailPlace
import com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation.HospitalLocationUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirebaseDatabase() = Firebase.database

    @Provides
    fun provideFirebaseStorage() = Firebase.storage

    @Provides
    fun provideArticleBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    fun provideGoogleMapsApiBaseUrl(): String = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            false -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideArticleApi(retrofit: Retrofit): ArticleApi =
        retrofit.create(ArticleApi::class.java)

    @Provides
    fun provideArticleRepository(articleApi: ArticleApi): ArticleRepository =
        ArticleRepositoryImpl(articleApi)

    @Provides
    fun provideArticleUseCases(articleRepository: ArticleRepository): ArticleUseCases =
        ArticleUseCases(GetArticles(articleRepository))


    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        db: FirebaseDatabase,
        storage: FirebaseStorage
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        db = db,
        storage = storage
    )

    @Provides
    fun provideAuthUseCases(
        repository: AuthRepositoryImpl
    ) = AuthUseCases(
        SignInWithEmail(repository),
        SignUpWithEmail(repository),
        AddImageToStorage(repository),
        AddDataUserToDatabase(repository),
    )

    @Provides
    fun provideHospitalLocationRepository(): HospitalLocationRepository =
        HospitalLocationRepositoryImpl()

    @Provides
    fun provideHospitalLocationUseCases(repository: HospitalLocationRepository) = HospitalLocationUseCase(
        GetDetailPlace(repository)
    )


}