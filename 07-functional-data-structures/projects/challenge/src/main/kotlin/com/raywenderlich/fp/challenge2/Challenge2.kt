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

package com.raywenderlich.fp.challenge2

import com.raywenderlich.fp.FList
import com.raywenderlich.fp.Predicate
import com.raywenderlich.fp.challenge1.first
import com.raywenderlich.fp.challenge1.last
import com.raywenderlich.fp.filter
import com.raywenderlich.fp.match

/** First item for the given condition */
fun <T> FList<T>.firstWhen(predicate: Predicate<T>): T? =
  filter(predicate).first()

/** Fast version of the firstWhen */
fun <T> FList<T>.fastFirstWhen(predicate: Predicate<T>): T? = match(
  whenNil = { null },
  whenCons = { head, tail ->
    if (predicate(head)) {
      head
    } else {
      tail.fastFirstWhen(predicate)
    }
  }
)

/** Last item for the given condition */
fun <T> FList<T>.lastWhen(predicate: Predicate<T>): T? =
  filter(predicate).last()

fun main() {
  val isEven: Predicate<Int> = { a: Int -> a % 2 == 0 }
  println(FList.of(1, 2, 3, 4, 5, 6).firstWhen(isEven))
  println(FList.of(1, 2, 3, 4, 5, 6).lastWhen(isEven))
  println(FList.of(1, 2, 3, 4, 5, 6).fastFirstWhen(isEven))
}