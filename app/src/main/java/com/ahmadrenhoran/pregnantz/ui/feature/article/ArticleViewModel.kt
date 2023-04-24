package com.ahmadrenhoran.pregnantz.ui.feature.article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadrenhoran.pregnantz.domain.model.Article
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.usecase.article.ArticleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val articleUseCases: ArticleUseCases): ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesUiState())
    val uiState: StateFlow<ArticlesUiState> = _uiState.asStateFlow()

    var getArticleResponse by mutableStateOf<Response<List<Article>>>(Response.Success(listOf()))
        private set

    fun getArticle() = viewModelScope.launch {
        getArticleResponse = Response.Loading
        getArticleResponse = articleUseCases.getArticles.invoke()
    }

    init {
        getArticle()
    }

    fun setArticles(articles: List<Article>) {
        _uiState.update {
            it.copy(
                articles = articles
            )
        }
    }
}