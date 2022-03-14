/*
 * Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.raybius.ui.detail

import android.text.Html
import android.view.View
import coil.load
import com.raywenderlich.android.raybius.R
import com.raywenderlich.android.raybius.databinding.FragmentDetailBinding
import com.raywenderlich.android.raybius.mobius.TvShowModel
import com.raywenderlich.fp.model.ShowDetail

/** UI logic for the Detail Activity */
fun FragmentDetailBinding.logic(model: TvShowModel) {
  if (model.loading) {
    showLoading()
  } else {
    hideLoading()
  }
}

fun FragmentDetailBinding.displayResult(detail: ShowDetail) {
  val imageUrl = detail.image?.original ?: detail.image?.original
  posterView.load(imageUrl) {
    placeholder(R.mipmap.ic_launcher)
    error(R.mipmap.ic_launcher)
  }
  showName.text = detail.name
  showGenre.text = detail.genres.joinToString()
  showLanguage.text = detail.language
  showType.text = detail.type
  if (detail.summary != null) {
    showSummary.text = Html.fromHtml(detail.summary, Html.FROM_HTML_MODE_LEGACY)
  } else {
    showSummary.text = "N.A."
  }

}

fun FragmentDetailBinding.showLoading() {
  progressBar.visibility = View.VISIBLE
}

fun FragmentDetailBinding.hideLoading() {
  progressBar.visibility = View.GONE
}