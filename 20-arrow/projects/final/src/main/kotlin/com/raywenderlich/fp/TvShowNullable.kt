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

import arrow.core.computations.nullable
import com.raywenderlich.fp.model.ScoredShow
import com.raywenderlich.fp.tools.fetchers.TvShowFetcher
import com.raywenderlich.fp.tools.parser.TvShowParser
import kotlinx.coroutines.runBlocking
import java.io.IOException
import com.raywenderlich.fp.lib.flatMap as nullableFlatMap

fun fetchNullable(query: String): String? = try {
  TvShowFetcher.fetch(query)
} catch (ioe: IOException) {
  null
}

fun parseNullable(json: String): List<ScoredShow>? = try {
  TvShowParser.parse(json)
} catch (e: Throwable) {
  null
}

fun fetchAndParseNullableFlatMap(query: String) =
  fetchNullable(query)
    .nullableFlatMap(::parseNullable)

suspend fun fetchAndParseNullable(query: String) = nullable {
  val json = fetchNullable("Big Bang").bind()
  val result = parseNullable(json).bind()
  result
}

fun mainWithFlatMap() {
  val searchResultOption = fetchAndParseNullableFlatMap("Big Bang")
  if (searchResultOption != null) {
    printScoresShow(searchResultOption)
  } else {
    println("Something wrong happened!")
  }
}

suspend fun mainWithComprehension() {
  val searchResultOption = fetchAndParseNullable("Big Bang")
  if (searchResultOption != null) {
    printScoresShow(searchResultOption)
  } else {
    println("Something wrong happened!")
  }
}

fun main() {
  mainWithFlatMap()
  runBlocking {
    mainWithComprehension()
  }
}