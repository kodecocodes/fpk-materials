package com.raywenderlich.android.raytv.ui.screens.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raywenderlich.android.raytv.api.fetchAndParseTvShowDetailResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

  var showDetailState = mutableStateOf<ShowDetailState>(SearchDetailRunning)
    private set

  private var currentJob: Job? = null

  fun findShowDetail(showId: Int) {
    currentJob?.cancel()
    currentJob = viewModelScope.launch(Dispatchers.IO) {
      showDetailState.value = SearchDetailRunning
      fetchAndParseTvShowDetailResult(showId)
        .fold(onFailure = {
          showDetailState.value = FailureDetailResult(it)
        }, onSuccess = {
          showDetailState.value = SuccessDetailResult(it)
        })
    }
  }
}