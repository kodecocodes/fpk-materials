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

package com.raywenderlich.android.raybius

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.raybius.databinding.MainBinding
import com.raywenderlich.android.raybius.mobius.TvShowEvent
import com.raywenderlich.android.raybius.mobius.TvShowMobiusController
import com.raywenderlich.android.raybius.mobius.TvShowModel
import com.raywenderlich.android.raybius.ui.MobiusHost
import com.raywenderlich.android.raybius.ui.search.TvSearchFragment
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Main Screen
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MobiusHost<TvShowModel, TvShowEvent> {

  @Inject
  lateinit var tvShowController: TvShowMobiusController

  //  lateinit var mainBinding: ActivityMainBinding
  lateinit var mainBinding: MainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    mainBinding = MainBinding.inflate(LayoutInflater.from(this))
    //mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
    setContentView(mainBinding.root)
    tvShowController.connect(::connectViews)
    supportFragmentManager
      .beginTransaction()
      .replace(R.id.anchor, TvSearchFragment())
      .commit()
  }


  override fun onDestroy() {
    super.onDestroy()
    tvShowController.disconnect()
  }

  override fun onResume() {
    super.onResume()
    tvShowController.start()
  }

  override fun onPause() {
    super.onPause()
    tvShowController.stop()
  }

  lateinit var eventConsumer: Consumer<TvShowEvent>

  private fun connectViews(eventConsumer: Consumer<TvShowEvent>): Connection<TvShowModel> {
    this.eventConsumer = eventConsumer
    return object : Connection<TvShowModel> {
      override fun accept(model: TvShowModel) {
        logic(eventConsumer, model)
      }

      override fun dispose() {
        //mainBinding.clear()
      }
    }
  }

  var logic: (Consumer<TvShowEvent>, TvShowModel) -> Unit = { _, _ -> }
  override fun injectLogic(logic: (Consumer<TvShowEvent>, TvShowModel) -> Unit) {
    this.logic = logic
  }
}
