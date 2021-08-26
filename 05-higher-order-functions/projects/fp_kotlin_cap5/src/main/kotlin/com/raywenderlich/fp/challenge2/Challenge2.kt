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

import com.raywenderlich.fp.Predicate1

/** Retuns all the elements of an Array<T> given a Predicate1<T> */
inline fun <reified T> Array<T>.all(predicate: Predicate1<T>): Array<T> =
  filter { predicate.accept(it) }.toTypedArray()

val isPrime = Predicate1<Int> { value ->
  if (value <= 3) value > 1
  else if (value % 2 == 0 || value % 3 == 0) false
  else {
    var i = 5
    while (i * i <= value) {
      if (value % i == 0 || value % (i + 2) == 0)
        return@Predicate1 false
      i += 6
    }
    true
  }
}

fun main() {
  arrayOf(1, 45, 67, 78, 34, 56, 89, 121, 2, 11, 12, 13)
    .all(isPrime)
    .forEach(::println)
}
