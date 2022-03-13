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

package com.raywenderlich.fp.lib

sealed class ResultAp<out E : Throwable, out T> {

  companion object {
    @JvmStatic
    fun <E : Throwable> error(error: E): ResultAp<E, Nothing> = Error(error)

    @JvmStatic
    fun <T> success(value: T): ResultAp<Nothing, T> = Success(value)
  }
}

data class Error<E : Throwable>(val error: E) : ResultAp<E, Nothing>()
data class Success<T>(val value: T) : ResultAp<Nothing, T>()

/** Apply the function to the error or do nothing */
fun <E1 : Throwable, E2 : Throwable, T> ResultAp<E1, T>.errorMap(fl: (E1) -> E2): ResultAp<E2, T> =
  when (this) {
    is Error<E1> -> ResultAp.error(fl(error))
    is Success<T> -> this
  }

/** Apply the function to the success or do nothing */
fun <E : Throwable, T, R> ResultAp<E, T>.successMap(fr: (T) -> R): ResultAp<E, R> = when (this) {
  is Error<E> -> this
  is Success<T> -> ResultAp.success(fr(value))
}

/** Applicative Functor implementation */
fun <E : Throwable, T, R> ResultAp<E, T>.ap(
  fn: ResultAp<E, (T) -> R>
): ResultAp<E, R> = when (fn) {
  is Success<(T) -> R> -> successMap(fn.value)
  is Error<E> -> when (this) {
    is Success<T> -> Error(fn.error)
    is Error<E> -> Error(this.error)
  }
}

/** ap as infix operator */
infix fun <E : Throwable, A, B> ResultAp<E, (A) -> B>.appl(a: ResultAp<E, A>) = a.ap(this)