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

package com.raywenderlich.fp.lib

/** fold implementation for FList<T> */
tailrec fun <T, S> FList<T>.fold(start: S, combineFunc: (S, T) -> S): S = when (this) {
  is Nil -> start
  is FCons<T> -> tail.fold(combineFunc(start, head), combineFunc)
}

/** foldRight implementation for FList<T> */
fun <T, S> FList<T>.foldRight(start: S, combineFunc: (T, S) -> S): S = when (this) {
  is Nil -> start
  is FCons<T> -> combineFunc(head, tail.foldRight(start, combineFunc))
}

/** Map implementation for FList<T> */
fun <T, S> FList<T>.map(fn: Fun<T, S>): FList<S> = when (this) {
  is Nil -> FList.empty()
  is FCons<T> -> FCons(fn(head), tail.map(fn))
}

/** Creates a new FList<> appending the values in another */
fun <T> FList<T>.append(rhs: FList<T>): FList<T> =
  foldRight(rhs, { item, acc -> FCons(item, acc) })

/** Flatmap implementation for FList<T> */
fun <T, S> FList<T>.flatMap(fn: Fun<T, FList<S>>): FList<S> = foldRight(
  FList.empty()
) { item, acc ->
  fn(item).append(acc)
}

fun countUpToFList(value: Int) = FList.of(*Array(value) { it })

fun main() {
  //val numbers = FList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  //numbers.fold(0) { acc, item -> acc + item } pipe ::println
  //numbers.fold(1) { acc, item -> acc * item } pipe ::println
//  FList.of(*("supercalifragilisticexpialidocious".toCharArray().toTypedArray()))
//    .foldRight(StringBuilder()) { item, acc ->
//      acc.append(item)
//      acc
//    } pipe ::println
//  FList.of(1, 2, 3, 4, 5)
//    .map(::double)
//    .forEach(::println)
//  val first = FList.of(1, 2, 3)
//  val second = FList.of(4, 5, 6)
//  first
//    .append(second)
//    .forEach(::println)
  val intList = FList.of(1, 2, 3)
  intList.flatMap(::countUpToFList).forEach(::println)
}