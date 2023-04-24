package com.ahmadrenhoran.pregnantz.domain.usecase.article

import com.ahmadrenhoran.pregnantz.domain.model.Article
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.repository.ArticleRepository

class GetArticles(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke(): Response<List<Article>> = articleRepository.getArticles()
}