package com.ahmadrenhoran.pregnantz.data.remote

import com.ahmadrenhoran.pregnantz.BuildConfig
import com.ahmadrenhoran.pregnantz.data.model.ArticleResponse
import com.ahmadrenhoran.pregnantz.data.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleApi {
    @GET("everything?q=pregnancy tips")
    suspend fun getNews(@Query("apiKey") apiKey: String = BuildConfig.MY_API_TOKEN): Response<ArticlesResponse>
}