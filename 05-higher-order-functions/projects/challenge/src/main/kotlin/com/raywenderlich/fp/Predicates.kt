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
 */

package com.raywenderlich.fp

/** Simple predicate functional interface */
fun interface Predicate1<T> {
  fun accept(value: T): Boolean
}

fun <T> T.isEqualsPredicate1(): Predicate1<T> =
  Predicate1<T> { value -> this == value }

/** Logical and */
infix fun <T> Predicate1<T>.and(other: Predicate1<T>): Predicate1<T> =
  Predicate1<T> { value ->
    this.accept(value) && other.accept(value)
  }

/** Logical or */
infix fun <T> Predicate1<T>.or(other: Predicate1<T>): Predicate1<T> =
  Predicate1<T> { value ->
    this.accept(value) || other.accept(value)
  }

/** Overload of filter for using Predicate1<T> */
fun <T> Iterable<T>.filterWithPredicate(predicate: Predicate1<T>) =
  filter(predicate::accept)

fun main() {
  val predicate = 4.isEqualsPredicate1() or 5.isEqualsPredicate1()
  listOf(1, 2, 3, 4, 4, 5, 6, 7, 8, 8)
    .filterWithPredicate(predicate)
    .forEach(::println)
}