package com.example.newsapp.ui_layer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.network_layer.response.NewsModel
import com.example.newsapp.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val repository = Repository()
    val data = mutableStateOf<NewsModel?>(null)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsViewModel()
        }

    }

    suspend fun getNewsViewModel() {
        data.value = repository.getNewsRepo().body()
    }

}