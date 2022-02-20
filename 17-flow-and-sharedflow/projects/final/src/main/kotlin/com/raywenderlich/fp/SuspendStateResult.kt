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


/** State data type definition */
typealias SuspendStateResultTransformer<S, T> = suspend (S) -> Pair<S, Result<T>>

data class SuspendableStateResult<S, T>(
  val sst: SuspendStateResultTransformer<S, T>
) {

  companion object {
    @JvmStatic
    fun <S, T> lift(
      value: T
    ): SuspendableStateResult<S, T> =
      SuspendableStateResult { state -> state to Result.success(value) }
  }
}

fun <S, A, B> SuspendableStateResult<S, A>.map(
  fn: SuspendFun<A, B>
): SuspendableStateResult<S, B> =
  SuspendableStateResult { s0: S ->
    val (s1, a) = this.sst(s0)
    s1 to a.fold(
      onSuccess = { Result.success(fn(it)) },
      onFailure = { Result.failure(it) }
    )
  }

fun <S, A, B> SuspendableStateResult<S, A>.flatMap(
  fn: suspend (A) -> SuspendableStateResult<S, B>
): SuspendableStateResult<S, B> = SuspendableStateResult { s0 ->
  val (s1, res) = sst(s0)
  res.fold(onSuccess = { a: A ->
    fn(a).sst(s1)
  }, onFailure = { thowable ->
    s1 to Result.failure(thowable)
  })
}