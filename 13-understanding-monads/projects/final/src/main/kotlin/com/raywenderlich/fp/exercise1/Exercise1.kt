/*
 *  Copyright (c) 2021 Razeware LLC
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

package com.raywenderlich.fp.exercise1

import com.raywenderlich.fp.Fun

sealed class Optional<out T> {
  companion object {
    @JvmStatic
    fun <T> lift(value: T): Optional<T> = Some(value)

    @JvmStatic
    fun <T> empty(): Optional<T> = None
  }
}

object None : Optional<Nothing>()
data class Some<T>(val value: T) : Optional<T>()

fun <A, B> Optional<A>.map(fn: Fun<A, B>): Optional<B> =
  when (this) {
    is Some<A> -> Some(fn(this.value))
    is None -> None
  }

fun <T> Optional<Optional<T>>.optionalFlatten(): Optional<T> = when (this) {
  is Some<Optional<T>> -> when (this.value) {
    is Some<T> -> Optional.lift<T>(this.value.value)
    is None -> Optional.empty()
  }
  is None -> Optional.empty()
}

infix fun <B, C> Optional<B>.optionalBind(
  g: Fun<B, Optional<C>>
): Optional<C> =
  map(g).optionalFlatten()

infix fun <A, B, C> Fun<A, Optional<B>>.optionalFish(
  g: Fun<B, Optional<C>>
): Fun<A, Optional<C>> = { a: A ->
  this(a).optionalBind(g)
}

