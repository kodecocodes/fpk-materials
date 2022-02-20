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

import com.raywenderlich.fp.model.ScoredShow
import com.raywenderlich.fp.tools.fetchers.TvShowFetcher
import com.raywenderlich.fp.tools.parser.TvShowParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.CoroutineContext

/** Invokes the fetcher returning an Result<T> */
suspend fun fetchTvShowResult(
  ctx: CoroutineContext,
  query: String
): Result<String> =
  withContext(ctx) {
    try {
      Result.success(TvShowFetcher.fetch(query))
    } catch (ioe: IOException) {
      Result.failure(ioe)
    }
  }

/** Invokes the parser returning an Result<T> */
suspend fun parseTvShowResult(
  ctx: CoroutineContext,
  json: String
): Result<List<ScoredShow>> =
  withContext(ctx) {
    try {
      Result.success(TvShowParser.parse(json /*+ "sabotage"*/))
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

val fetchSuspend: (String) -> SuspendableState<CoroutineContext, Result<String>> =
  { query: String ->
    SuspendableState { ctx: CoroutineContext ->
      ctx to fetchTvShowResult(ctx, query)
    }
  }

val parseSuspend: (String) -> SuspendableState<CoroutineContext, Result<List<ScoredShow>>> =
  { json: String ->
    SuspendableState { ctx: CoroutineContext ->
      ctx to parseTvShowResult(ctx, json)
    }
  }

val fetchSuspendResult: (String) -> SuspendableStateResult<CoroutineContext, String> =
  { query: String ->
    SuspendableStateResult { ctx: CoroutineContext ->
      ctx to fetchTvShowResult(ctx, query)
    }
  }

val parseSuspendResult: (String) -> SuspendableStateResult<CoroutineContext, List<ScoredShow>> =
  { json: String ->
    SuspendableStateResult { ctx: CoroutineContext ->
      ctx to parseTvShowResult(ctx, json)
    }
  }

@OptIn(FlowPreview::class)
suspend fun searchTvShow(ctx: CoroutineContext) = withContext(ctx) {
  inputStringFlow("Search Your Show: ").flatMapConcat { query ->
    fetchSuspendResult(query)
      .flatMap(parseSuspendResult).sst(ctx)
      .second.fold(
        onSuccess = { it.asFlow() },
        onFailure = { emptyFlow() })
  }
}

@OptIn(FlowPreview::class)
fun main() {
  runBlocking {
    searchTvShow(Dispatchers.IO)
      .collect {
        println("Score: ${it.score}  Name: ${it.show.name} Genres: ${it.show.genres}")
        println(it.show.summary)
        println("--------------------------")
      }
  }
}
