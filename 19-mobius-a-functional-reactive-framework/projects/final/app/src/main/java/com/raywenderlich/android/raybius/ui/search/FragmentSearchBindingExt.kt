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

package com.raywenderlich.android.raybius.ui.search

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.raybius.databinding.FragmentSearchBinding
import com.raywenderlich.android.raybius.mobius.InputTextChanged
import com.raywenderlich.android.raybius.mobius.SearchButtonClicked
import com.raywenderlich.android.raybius.mobius.TvShowEvent
import com.raywenderlich.android.raybius.mobius.TvShowModel
import com.raywenderlich.android.raybius.model.ScoredShow
import com.spotify.mobius.functions.Consumer

/**
 * Display the initial state of the UI
 */
fun FragmentSearchBinding.initUI(
  eventConsumer: Consumer<TvShowEvent>,
  onItemSelected: (Int) -> Unit
) {
  with(resultRecyclerView) {
    adapter = ScoredShowAdapter(onItemSelected)
    layoutManager = LinearLayoutManager(context)
    visibility = View.GONE
  }
  helpMessage.visibility = View.VISIBLE
  progressBar.visibility = View.GONE
  textUpdate { text ->
    eventConsumer.accept(InputTextChanged(text))
  }
  search {
    eventConsumer.accept(SearchButtonClicked)
  }
}

/** UI logic for the Main Activity */
fun FragmentSearchBinding.logic(model: TvShowModel) {
  if (model.loading) {
    showLoading()
  } else {
    hideLoading()
  }
  displayResults(model.searchResults)
  if (model.error) {
    errorMode()
  }
  searchButton.isEnabled = model.searchEnabled
}

fun FragmentSearchBinding.emptyMain() {
  progressBar.visibility = View.GONE
  helpMessage.visibility = View.VISIBLE
  resultRecyclerView.visibility = View.GONE
}

fun FragmentSearchBinding.showLoading() {
  progressBar.visibility = View.VISIBLE
  helpMessage.visibility = View.GONE
}

fun FragmentSearchBinding.hideLoading() {
  progressBar.visibility = View.GONE
  helpMessage.visibility = View.VISIBLE
}

fun FragmentSearchBinding.search(callback: () -> Unit) {
  searchButton.setOnClickListener {
    callback()
  }
}

fun FragmentSearchBinding.textUpdate(callback: (String) -> Unit) {
  inputText.addTextChangedListener(object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable) {
      callback(s.toString())
    }
  })
}

/** Displays the result in the RecyclerView */
fun FragmentSearchBinding.displayResults(results: List<ScoredShow>) {
  (resultRecyclerView.adapter as ScoredShowAdapter).submitList(results)
  progressBar.visibility = View.GONE
  if (results.isEmpty()) {
    resultRecyclerView.visibility = View.GONE
    helpMessage.visibility = View.VISIBLE
  } else {
    resultRecyclerView.visibility = View.VISIBLE
    helpMessage.visibility = View.GONE
  }
}

/** Hide information to allow the display of error message */
fun FragmentSearchBinding.errorMode() {
  resultRecyclerView.visibility = View.GONE
  progressBar.visibility = View.GONE
}

/**
 * Release resources
 */
fun FragmentSearchBinding.clear() {
  helpMessage.visibility = View.VISIBLE
  resultRecyclerView.visibility = View.GONE
}