package com.raywenderlich.android.raytv.ui.screens

enum class Screens {
  Splash,
  Search,
  TvShowDetail;

  companion object {
    fun fromRoute(route: String?): Screens =
      when (route?.substringBefore("/")) {
        Splash.name -> Splash
        Search.name -> Search
        TvShowDetail.name -> TvShowDetail
        null -> Splash
        else -> throw IllegalArgumentException("Route $route is not recognized.")
      }
  }
}