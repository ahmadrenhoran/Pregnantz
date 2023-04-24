package com.ahmadrenhoran.pregnantz.data.model.article

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

    @field:SerializedName("totalResults")
	val totalResults: Int? = null,

    @field:SerializedName("articles")
	val articles: List<ArticleResponse?>? = null,

    @field:SerializedName("status")
	val status: String? = null
)


