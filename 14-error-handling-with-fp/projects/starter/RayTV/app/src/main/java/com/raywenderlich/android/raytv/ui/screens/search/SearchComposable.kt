package com.raywenderlich.android.raytv.ui.screens.search

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.raywenderlich.android.raytv.R
import com.raywenderlich.android.raytv.ui.screens.ErrorAlert
import com.raywenderlich.android.raytv.ui.theme.RayTVTheme
import com.raywenderlich.fp.model.ScoredShow


@androidx.compose.animation.ExperimentalAnimationApi
@Composable
fun Search(
  modifier: Modifier = Modifier,
  onClick: (Int) -> Unit
) {
  val searchViewModel: SearchViewModel = hiltViewModel()
  val (inputText, updateInputText) = rememberSaveable { mutableStateOf("") }
  val focusRequester = remember { FocusRequester() }
  Scaffold(
    modifier = modifier,
    topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) },
    content = {
      val result = searchViewModel.searchState.value
      Column(
        modifier
          .padding(16.dp)
          .animateContentSize()
          .alpha(if (result == SearchRunning) 0.5F else 1.0F)
      ) {
        ErrorAlert(errorMessage = {
          stringResource(R.string.error_message)
        }) {
          result is FailureSearchResult
        }
        TextField(
          modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .padding(bottom = 16.dp),
          value = inputText,
          onValueChange = { newText ->
            updateInputText(newText)
            searchViewModel.findShow(newText)
          },
          shape = RoundedCornerShape(3.dp),
          singleLine = true,
          placeholder = { Text(stringResource(R.string.search_hint)) },
        )
        when (result) {
          NoSearchDone -> {
            FirstScreenComposable()
          }
          is SuccessSearchResult -> {
            ShowResultComposable(result.data, modifier, onClick)
          }
          NoSearchResult -> {
            NoResultComposable()
          }
          else -> {
          }
        }
      }
    },
  )
}

@Composable
fun FirstScreenComposable(modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    Text(
      text = stringResource(R.string.help_message),
      style = MaterialTheme.typography.h5,
      modifier = modifier.align(Alignment.Center)
    )
  }
}

@Composable
fun NoResultComposable(modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    Text(
      text = stringResource(R.string.no_result_message),
      style = MaterialTheme.typography.h5,
      modifier = modifier.align(Alignment.Center)
    )
  }
}

@Composable
fun ShowResultComposable(
  model: List<ScoredShow>,
  modifier: Modifier = Modifier,
  onClick: (Int) -> Unit
) {
  val scrollState = rememberLazyListState()
  LazyColumn(
    modifier = modifier,
    state = scrollState,
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    items(model.size) {
      TvShowItem(modifier, model[it], onClick)
    }
  }
}

@Composable
fun TvShowItem(
  modifier: Modifier = Modifier,
  item: ScoredShow,
  onClick: (Int) -> Unit
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .clickable { onClick(item.show.id) },
    shape = RoundedCornerShape(3.dp),
    border = BorderStroke(1.dp, Color.LightGray),
    elevation = 1.dp
  ) {
    Row(
      modifier = modifier.padding(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = rememberImagePainter(
          data = item.show.image?.original ?: R.mipmap.ic_launcher,
          builder = {
            placeholder(R.mipmap.ic_launcher)
          }
        ),
        contentDescription = "Android Logo",
        modifier = Modifier
          .size(150.dp)
      )
      Spacer(Modifier.width(8.dp))
      Column(modifier = modifier) {
        Text(
          text = item.show.name,
          style = MaterialTheme.typography.h6
        )
        Text(
          text = item.show.genres.joinToString(),
          style = MaterialTheme.typography.subtitle2
        )
      }
    }
  }
}


@Composable
@Preview
fun NoResultComposablePreview() {
  RayTVTheme {
    NoResultComposable()
  }
}