package com.ahmadrenhoran.pregnantz.data.repository

import com.ahmadrenhoran.pregnantz.data.remote.ArticleApi
import com.ahmadrenhoran.pregnantz.domain.model.Article
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val articleApi: ArticleApi) :
    ArticleRepository {
    override suspend fun getArticles(): Response<List<Article>> {
        return try {
            val response = articleApi.getNews()
            if (response.isSuccessful) {
                val body = response.body()!!.articles!!
                val articles = mutableListOf<Article>()
                body.forEach { articleResponse ->
                    if (articleResponse != null) {
                        articles.add (
                            Article(
                                publishedAt = articleResponse.publishedAt,
                                author = articleResponse.author,
                                urlToImage = articleResponse.urlToImage,
                                description = articleResponse.description,
                                source = articleResponse.source,
                                title = articleResponse.title,
                                url = articleResponse.url,
                                content = articleResponse.content,
                            )
                        )
                    }
                }
                Response.Success(articles)
            } else {
                Response.Failure(Exception("Failed to get news list"))
            }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}