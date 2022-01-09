package com.raywenderlich.android.raytv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raywenderlich.android.raytv.ui.screens.Screens
import com.raywenderlich.android.raytv.ui.screens.Splash
import com.raywenderlich.android.raytv.ui.screens.detail.TvShowDetail
import com.raywenderlich.android.raytv.ui.screens.search.Search
import com.raywenderlich.android.raytv.ui.screens.splash.SplashViewModel
import com.raywenderlich.android.raytv.ui.screens.splash.SplashViewModel.SplashState.DISMISSED
import com.raywenderlich.android.raytv.ui.theme.RayTVTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @ExperimentalAnimationApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      RayTvApp()
    }
  }
}


@ExperimentalAnimationApi
@Composable
fun RayTvApp() {
  RayTVTheme {
    val navController = rememberNavController()
    val splashViewModel: SplashViewModel = hiltViewModel()
    if (splashViewModel.splashShowing.value == DISMISSED) {
      NavHost(
        navController = navController,
        startDestination = Screens.Search.name,
        modifier = Modifier.padding(4.dp)
      ) {
        composable(Screens.Search.name) {
          Search { showId ->
            navController.navigate(
              "${Screens.TvShowDetail.name}/$showId"
            )
          }
        }
        composable("${Screens.TvShowDetail.name}/{showId}",
          arguments = listOf(
            navArgument("showId") {
              // Make argument type safe
              type = NavType.IntType
            }
          )) { entry ->
          val showId = entry.arguments?.getInt("showId") ?: -1
          // Pass account to SingleAccountBody
          TvShowDetail(showId = showId)
        }
      }
    } else {
      Splash()
    }
  }
}
