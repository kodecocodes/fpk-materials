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

import arrow.core.Option
import arrow.core.getOrElse
import arrow.core.none
import arrow.core.some
import com.raywenderlich.fp.model.ScoredShow
import com.raywenderlich.fp.tools.fetchers.TvShowFetcher
import com.raywenderlich.fp.tools.parser.TvShowParser
import java.io.IOException

fun fetchOption(query: String): Option<String> = try {
  TvShowFetcher.fetch(query).some()
} catch (ioe: IOException) {
  none()
}

fun parseOption(json: String): Option<List<ScoredShow>> = try {
  TvShowParser.parse(json).some()
} catch (e: Throwable) {
  none()
}

fun fetchAndParseOption(query: String): Option<List<ScoredShow>> =
  fetchOption(query).flatMap(::parseOption)

fun main() {
  val searchResultOption = fetchAndParseOption("Big Bang")
  if (searchResultOption.isDefined()) {
    val searchResult = searchResultOption.getOrElse { emptyList() }
    if (!searchResult.isEmpty()) {
      searchResult.forEach {
        with(it.show) {
          println("Name: ${name}  Genre: ${genres.joinToString()}")
        }
      }
    } else {
      println("No Results!")
    }
  } else {
    println("Something wrong happened!")
  }
}