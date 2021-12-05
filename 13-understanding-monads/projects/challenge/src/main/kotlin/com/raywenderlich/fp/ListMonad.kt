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

package com.raywenderlich.fp

fun <T> List<List<T>>.listFlatten(): List<T> =
  this.fold(mutableListOf()) { acc, item ->
    acc.apply {
      addAll(item)
    }
  }

infix fun <B, C> List<B>.listBind(
  g: Fun<B, List<C>>
): List<C> =
  map(g).listFlatten()

infix fun <A, B, C> Fun<A, List<B>>.listFish(
  g: Fun<B, List<C>>
): Fun<A, List<C>> = { a: A ->
  this(a).listBind(g)
}

/** Function of type Fun<Int, List<Int>> returning the value from 0 to n*/
val countList: (Int) -> List<Int> =
  { n: Int -> List(n) { it + 1 } }

/** Function of type Fun<Int, List<String>> */
val intToChars =
  { n: Int -> List(n) { 'a' + n } }

fun main() {
  val fished = countList listFish intToChars
  fished(3) pipe ::println

  countList(3).flatMap(intToChars) pipe ::println
}