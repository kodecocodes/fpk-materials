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

import com.raywenderlich.fp.lib.Fun

data class IO<T>(val wt: WorldT<T>) {

  companion object {
    @JvmStatic
    fun <S, T> lift(
      value: T
    ): IO<T> =
      IO { w -> value to w }
  }
}

operator fun <T> IO<T>.invoke(w: World) = wt(w)

/** IO<T> as a Functor */
fun <A, B> IO<A>.map(
  fn: Fun<A, B>
): IO<B> =
  IO { w0 ->
    val (a, w1) = this(w0) // Or wt(w0)
    fn(a) to w1
  }

/** IO<T> as Applicative Functor */
fun <T, R> IO<T>.ap(
  fn: IO<(T) -> R>
): IO<R> =
  IO { w0: World ->
    val (t, w1) = this(w0)
    val (fnValue, w2) = fn(w1)
    fnValue(t) to w2
  }

infix fun <A, B> IO<(A) -> B>.appl(a: IO<A>) = a.ap(this)

/** IO<T> as Monad */
fun <A, B> IO<A>.flatMap(
  fn: (A) -> IO<B>
): IO<B> =
  IO { w0: World ->
    val (a, w1) = this(w0)
    fn(a)(w1)
  }