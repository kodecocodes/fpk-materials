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

package com.raywenderlich.fp.api.tools.fetchers

import java.net.HttpURLConnection
import java.net.URL

/** Fetches the TV Show for a given query */
object TvShowFetcher {
  fun fetch(query: String): String {
    val encodedUrl = java.net.URLEncoder.encode(query, "utf-8")
    val localUrl = URL("https://api.tvmaze.com/search/shows?q=$encodedUrl")
    with(localUrl.openConnection() as HttpURLConnection) {
      requestMethod = "GET"
      val reader = inputStream.bufferedReader()
      return reader.lines().toArray().asSequence()
        .fold(StringBuilder()) { builder, line ->
          builder.append(line)
        }.toString()
    }
  }

  fun fetchById(showId: Int): String {
    val localUrl = URL("https://api.tvmaze.com/shows/$showId")
    with(localUrl.openConnection() as HttpURLConnection) {
      requestMethod = "GET"
      val reader = inputStream.bufferedReader()
      return reader.lines().toArray().asSequence()
        .fold(StringBuilder()) { builder, line ->
          builder.append(line)
        }.toString()
    }
  }
}