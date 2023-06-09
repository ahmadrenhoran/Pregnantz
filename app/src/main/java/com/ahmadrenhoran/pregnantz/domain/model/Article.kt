package com.ahmadrenhoran.pregnantz.domain.model

import com.ahmadrenhoran.pregnantz.data.model.article.SourceResponse

data class Article(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: SourceResponse? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null
)
