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

import pipe

/** Monoid<String> implementation for String concatenation */
object MonoidStringConcat : Monoid<String> {
  override val unit: String
    get() = ""
  override val combine: String.(String) -> String
    get() = String::plus
}

/** Sum of elements in a List<Int> */
//fun List<Int>.sumList() = fold(0) { a, b -> a + b }
fun List<Int>.sumList() = fold(MonoidIntAdd)

/** Reverse a String */
//fun String.reverseString() = foldRight("") { char, str -> str + char }
// fun String.reverseString() = fold(MonoidStringConcat)
fun String.reverseString() = fold(MonoidStringConcat.commutate())

/** Swap the receiver type */
fun <A, B, C> (A.(B) -> C).swap(): (B.(A) -> C) = { a: A ->
  a.this@swap(this)
}

/** Commute the order of the parameters in the given Monoid */
fun <T> Monoid<T>.commutate(): Monoid<T> = object : Monoid<T> {
  override val unit: T
    get() = this@commutate.unit
  override val combine: T.(T) -> T
    get() = this@commutate.combine.swap()
}

typealias Foldable<T> = Iterable<T>

/** Fold overload using a Monoid */
fun <T> Foldable<T>.fold(monoid: Monoid<T>): T =
  fold(monoid.unit, monoid.combine)

/** fold overload for CharSequence */
fun CharSequence.fold(monoid: Monoid<String>): CharSequence =
  this.fold(monoid.unit) { a, b ->
    monoid.combine(a, "$b")
  }


fun main() {
  listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).sumList() pipe ::println
  "supercalifragilisticexpialidocious".reverseString() pipe ::println
}