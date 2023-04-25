package com.ahmadrenhoran.pregnantz.domain.repository


import com.ahmadrenhoran.pregnantz.domain.model.Article
import com.ahmadrenhoran.pregnantz.domain.model.Response
import retrofit2.http.GET

interface ArticleRepository {

    suspend fun getArticles(): Response<List<Article>>
}