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

package com.raywenderlich.fp.exercise4

/** Alias for function returning arrays */
typealias ToArray<A, B> = (A) -> Array<B>

inline infix fun <A, B, reified C> ToArray<A, B>.compose(
  crossinline g: ToArray<B, C>
): ToArray<A, C> = { a: A ->
  val bArray = this(a)
  val cArray = mutableListOf<C>()
  for (bValue in bArray) {
    cArray.addAll(g(bValue))
  }
  cArray.toTypedArray()
}

inline infix fun <A, B, reified C> ToArray<A, B>.composeWithFold(
  crossinline g: ToArray<B, C>
): ToArray<A, C> = { a: A ->
  this(a).fold(mutableListOf<C>()) { acc, item ->
    for (bValue in g(item)) {
      acc.add(bValue)
    }
    acc
  }.toTypedArray()
}

val fibo = { n: Int ->
  tailrec fun fiboHelper(a: Int, b: Int, fiboN: Int): Int =
    when (fiboN) {
      0 -> a
      1 -> b
      else -> fiboHelper(b, a + b, fiboN - 1)
    }
  fiboHelper(1, 1, n)
}

fun main() {
  val counter = { a: Int -> Array(a) { it } }
  val fiboLength = { n: Int -> Array(n) { fibo(it) } }
  val counterFibo = counter compose fiboLength
  counterFibo(5).forEach { print("$it ") }
  println()
  val counterFiboWithFold = counter composeWithFold fiboLength
  counterFiboWithFold(5).forEach { print("$it ") }
}