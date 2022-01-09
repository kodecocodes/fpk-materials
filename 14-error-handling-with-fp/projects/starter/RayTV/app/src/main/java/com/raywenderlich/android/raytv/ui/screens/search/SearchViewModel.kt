package com.raywenderlich.android.raytv.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raywenderlich.android.raytv.api.fetchAndParseTvShowResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

  var searchState = mutableStateOf<SearchState>(NoSearchDone)
    private set

  private var currentJob: Job? = null

  fun findShow(showName: String) {
    currentJob?.cancel()
    currentJob = viewModelScope.launch(Dispatchers.IO) {
      searchState.value = SearchRunning
      fetchAndParseTvShowResult(showName)
        .fold(onFailure = {
          searchState.value = FailureSearchResult(it)
        }, onSuccess = {
          if (!it.isEmpty()) {
            searchState.value = SuccessSearchResult(it)
          } else {
            searchState.value = NoSearchResult
          }
        })
    }
  }
}