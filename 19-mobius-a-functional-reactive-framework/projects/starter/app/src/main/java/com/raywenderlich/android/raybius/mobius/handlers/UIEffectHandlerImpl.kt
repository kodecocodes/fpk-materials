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

package com.raywenderlich.android.raybius.mobius.handlers

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.raywenderlich.android.raybius.R
import com.raywenderlich.android.raybius.mobius.DisplayErrorMessage
import com.raywenderlich.android.raybius.mobius.HideKeyboard
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

internal const val SHOW_ID_EXTRA = "showId"

class UIEffectHandlerImpl @Inject constructor(
  @ActivityContext val activityContext: Context
) : UIEffectHandler {

  override fun handleErrorMessage(effect: DisplayErrorMessage) {
    val errorMessage =
      effect.error.localizedMessage ?: activityContext.getString(R.string.generic_error_message)
    Toast.makeText(activityContext, errorMessage, Toast.LENGTH_SHORT).show()
  }

  override fun handleHideKeyboardMessage(effect: HideKeyboard) {
    val asActivity = activityContext as Activity
    val imm: InputMethodManager =
      asActivity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view: View = activityContext.getCurrentFocus() ?: View(asActivity)
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
  }
}