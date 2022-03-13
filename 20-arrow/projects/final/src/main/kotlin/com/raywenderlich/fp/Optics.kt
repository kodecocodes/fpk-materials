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

package com.raywenderlich.fp

import arrow.optics.Optional
import com.raywenderlich.fp.lib.pipe
import com.raywenderlich.fp.model.*

val bigBangTheory =
  ScoredShow(
    score = 0.9096895,
    Show(
      id = 66,
      name = "The Big Bang Theory",
      genres = listOf("Comedy"),
      url = "https://www.tvmaze.com/shows/66/the-big-bang-theory",
      image = ShowImage(
        original = "",
        medium = "https://static.tvmaze.com/uploads/images/medium_portrait/173/433868.jpg"
      ),
      summary = "<p><b>The Big Bang Theory</b> is a comedy about brilliant physicists, Leonard and Sheldon...</p>",
      language = "English"
    )
  )

val updatedBigBangTheory = bigBangTheory.copy(
  show = bigBangTheory.show.copy(
    image = bigBangTheory.show.image?.copy(
      original = "https://static.tvmaze.com/uploads/images/medium_portrait/173/433868.jpg"
    )
  )
)

fun main() {
  // Update Original Image
  val updateOriginalImageLens: Optional<ScoredShow, String> = ScoredShow.show.image.original
  val updatedShow =
    updateOriginalImageLens.modify(bigBangTheory) { "https://static.tvmaze.com/uploads/images/medium_portrait/173/433868.jpg" }
  updatedShow pipe ::println

  // Update summary
  val updateName: Optional<ScoredShow, String> = ScoredShow.show.name
  val updatedName = updateName.modify(updatedShow, String::toUpperCase)
  updatedName pipe ::println

}