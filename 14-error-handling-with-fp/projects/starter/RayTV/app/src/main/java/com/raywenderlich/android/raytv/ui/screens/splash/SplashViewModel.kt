package com.raywenderlich.android.raytv.ui.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

  private val SPLASH_DELAY = 1_500L

  var splashShowing = mutableStateOf(SplashState.NONE)
    private set

  enum class SplashState {
    NONE,
    DISPLAYED,
    DISMISSED
  }

  init {
    viewModelScope.launch {
      with(splashShowing) {
        value = SplashState.DISPLAYED
        delay(SPLASH_DELAY)
        value = SplashState.DISMISSED
      }
    }
  }
}