/*
 *  Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * Not withstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the Software in
 * any work that is designed, intended, or marketed for pedagogical or instructional
 * purposes related to programming, coding, application development, or information
 * technology.  Permission for such use, copying, modification, merger, publication,
 * distribution, sublicensing, creation of derivative works, or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.raywenderlich.android.raytv.ui.screens.detail

import android.widget.TextView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.raywenderlich.android.raytv.R
import com.raywenderlich.fp.model.ShowDetail

@ExperimentalAnimationApi
@Composable
fun TvShowDetail(
  modifier: Modifier = Modifier,
  detailViewModel: DetailViewModel = hiltViewModel(),
  showId: Int
) {
  LaunchedEffect(true) {
    detailViewModel.findShowDetail(showId)
  }
  val currentState = detailViewModel.showDetailState.value
  val currentTitle: String
  when (currentState) {
    SearchDetailRunning -> {
      currentTitle = "Loading"
    }
    is SuccessDetailResult -> {
      currentTitle = currentState.data.name
    }
    is FailureDetailResult -> {
      currentTitle = "Error"
    }
  }
  Scaffold(
    topBar = { TopAppBar(title = { Text(currentTitle) }) },
    content = {
      val result = detailViewModel.showDetailState.value
      when (result) {
        is SuccessDetailResult -> {
          ShowDetail(showDetail = result.data)
        }
        else -> {

        }
      }
    },
  )
}

@ExperimentalAnimationApi
@Composable
fun ShowDetail(modifier: Modifier = Modifier, showDetail: ShowDetail) {
  Column(modifier.padding(16.dp)) {
    Row {
      Image(
        painter = rememberImagePainter(
          data = showDetail.image?.original ?: R.mipmap.ic_launcher,
          builder = {
            placeholder(R.mipmap.ic_launcher)
          }
        ),
        contentDescription = "TV Show Poster",
        modifier = Modifier
          .size(200.dp)
          .padding(2.dp),
      )
      Column {
        AnimatedVisibility(visible = !showDetail.type.isNullOrEmpty()) {
          Text("${stringResource(id = R.string.genre_label)}: ${showDetail.genres.joinToString()}")
        }
        AnimatedVisibility(visible = !showDetail.language.isNullOrEmpty()) {
          Text("${stringResource(id = R.string.language_label)}: ${showDetail.language}")
        }
        AnimatedVisibility(visible = !showDetail.premiered.isNullOrEmpty()) {
          Text("${stringResource(id = R.string.premiered_label)}: ${showDetail.premiered}")
        }
        AnimatedVisibility(visible = !showDetail.ended.isNullOrEmpty()) {
          Text("${stringResource(id = R.string.ended_label)}: ${showDetail.ended}")
        }
      }
    }
    AnimatedVisibility(visible = !showDetail.summary.isNullOrEmpty()) {
      Column(modifier = modifier) {
        Text(
          modifier = modifier,
          fontWeight = FontWeight.Bold,
          fontSize = 20.sp,
          text = stringResource(id = R.string.summary_label)
        )
        Html(showDetail.summary ?: "")
      }
    }
  }
}

@Composable
fun Html(text: String) {
  AndroidView(factory = { context ->
    TextView(context).apply {
      setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
    }
  })
}