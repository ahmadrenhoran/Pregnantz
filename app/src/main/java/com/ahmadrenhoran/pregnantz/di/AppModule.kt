package com.ahmadrenhoran.pregnantz.di

import com.ahmadrenhoran.pregnantz.BuildConfig
import com.ahmadrenhoran.pregnantz.data.remote.ArticleApi
import com.ahmadrenhoran.pregnantz.data.remote.GoogleMapApi
import com.ahmadrenhoran.pregnantz.data.repository.*
import com.ahmadrenhoran.pregnantz.domain.repository.*
import com.ahmadrenhoran.pregnantz.domain.usecase.article.ArticleUseCases
import com.ahmadrenhoran.pregnantz.domain.usecase.article.GetArticles
import com.ahmadrenhoran.pregnantz.domain.usecase.auth.*
import com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation.GetDetailPlace
import com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation.GetNearbyHospital
import com.ahmadrenhoran.pregnantz.domain.usecase.hospitallocation.HospitalLocationUseCases
import com.ahmadrenhoran.pregnantz.domain.usecase.tool.*
import com.ahmadrenhoran.pregnantz.domain.usecase.weight.AddWeight
import com.ahmadrenhoran.pregnantz.domain.usecase.weight.DeleteWeight
import com.ahmadrenhoran.pregnantz.domain.usecase.weight.GetWeightHistory
import com.ahmadrenhoran.pregnantz.domain.usecase.weight.WeightUseCases
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
import javax.inject.Qualifier

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
    @BaseUrlV1
    fun provideArticleBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @BaseUrlV2
    fun provideGoogleMapsApiBaseUrl(): String =
        "https://maps.googleapis.com/maps/api/place/nearbysearch/"


    // Article

    @Provides
    @HttpClientV1
    fun provideOkHttpClientArticle(): OkHttpClient {
        val loggingInterceptor = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            false -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @RetrofitV1
    fun provideArticleRetrofit(
        @HttpClientV1 okHttpClient: OkHttpClient,
        @BaseUrlV1 articleBaseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(articleBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideArticleApi(@RetrofitV1 articleRetrofit: Retrofit): ArticleApi =
        articleRetrofit.create(ArticleApi::class.java)

    @Provides
    fun provideArticleRepository(articleApi: ArticleApi): ArticleRepository =
        ArticleRepositoryImpl(articleApi)

    @Provides
    fun provideArticleUseCases(articleRepository: ArticleRepository): ArticleUseCases =
        ArticleUseCases(GetArticles(articleRepository))


    // Auth
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

    // Tool
    @Provides
    fun provideToolRepository(
        auth: FirebaseAuth,
        db: FirebaseDatabase,
    ): ToolRepository = ToolRepositoryImpl(
        auth = auth,
        db = db,
    )

    @Provides
    fun provideToolUseCases(
        repository: ToolRepository, weightRepository: WeightRepository
    ): ToolUseCases = ToolUseCases(
        getCurrentWeight = GetCurrentWeight(repository),
        addWeight = AddWeight(weightRepository),
        insertPanicNumber = InsertPanicNumber(repository),
        deletePanicNumber = DeletePanicNumber(repository),
        getListPanicNumbers = GetListPanicNumbers(repository)
    )


    // Hospital
    @Provides
    @HttpClientV2
    fun provideOkHttpClientGoogleMap(): OkHttpClient {
        val loggingInterceptor = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            false -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @RetrofitV2
    fun provideGoogleMapRetrofit(
        @HttpClientV2 okHttpClient: OkHttpClient,
        @BaseUrlV2 googleMapsApiBaseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(googleMapsApiBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideGoogleMapApi(@RetrofitV2 googleMapRetrofit: Retrofit): GoogleMapApi =
        googleMapRetrofit.create(GoogleMapApi::class.java)

    @Provides
    fun provideHospitalLocationRepository(googleMapApi: GoogleMapApi): HospitalLocationRepository =
        HospitalLocationRepositoryImpl(googleMapApi)

    @Provides
    fun provideHospitalLocationUseCases(repository: HospitalLocationRepository) =
        HospitalLocationUseCases(
            GetDetailPlace(repository), GetNearbyHospital(repository)
        )

    // Weight
    @Provides
    fun provideWeightRepository(auth: FirebaseAuth, db: FirebaseDatabase): WeightRepository =
        WeightRepositoryImpl(auth, db)

    @Provides
    fun provideWeightUseCase(repository: WeightRepository) = WeightUseCases(
        addWeight = AddWeight(repository),
        getWeightHistory = GetWeightHistory(repository), deleteWeight = DeleteWeight(repository)
    )


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlV1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlV2

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpClientV1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpClientV2

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitV1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitV2