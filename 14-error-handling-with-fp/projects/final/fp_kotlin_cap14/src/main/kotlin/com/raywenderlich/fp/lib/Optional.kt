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

/** Optional type */
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

/** The map function for Optional<T> */
fun <A, B> Optional<A>.map(fn: Fun<A, B>): Optional<B> = when (this) {
  is None -> Optional.empty()
  is Some<A> -> Optional.lift(fn(value))
}

/** The flatMap function for Optional<T> */
fun <A, B> Optional<A>.flatMap(fn: Fun<A, Optional<B>>): Optional<B> = when (this) {
  is None -> Optional.empty()
  is Some<A> -> {
    val res = fn(value)
    when (res) {
      is None -> Optional.empty()
      is Some<B> -> Optional.lift(res.value)
    }
  }
}

/** Extract the value in the Optional if any */
fun <A> Optional<A>.getOrDefault(defaultValue: A): A = when (this) {
  is None -> defaultValue
  is Some<A> -> value
}
