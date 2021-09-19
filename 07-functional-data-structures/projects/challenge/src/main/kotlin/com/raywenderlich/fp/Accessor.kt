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
// DOESN'T COMPILE IN ANOTHER MODULE
/*
fun <T> FList<T>.size(): Int = when (this) {
  is Nil -> 0
  is Cons<T> -> 1 + tail.size
}
 */

/** Size of the FList<T> */
fun <T> FList<T>.size(): Int = match(
  whenNil = { 0 },
  whenCons = { head, tail -> 1 + tail.size() }
)

/** Head of the FList<T> */
fun <T> FList<T>.head(): T? = match(
  whenNil = { null },
  whenCons = { head, _ -> head }
)

fun main() {
  // Size
  println(FList.empty<Int>().size())
  println(FList.of(1).size())
  println(FList.of(1, 2, 3).size())
  // Head
  println(FList.empty<Int>().head())
  println(FList.of(1).head())
  println(FList.of(1, 2, 3).head())
}
