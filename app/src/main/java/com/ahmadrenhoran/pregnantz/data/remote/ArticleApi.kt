package com.ahmadrenhoran.pregnantz.data.remote

import com.ahmadrenhoran.pregnantz.data.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticleApi {
    @GET("news")
    suspend fun getNews(): Response<List<ArticleResponse>>
}