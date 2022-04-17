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
 */

package com.raywenderlich.fp.challenge2

import com.raywenderlich.fp.times

/** Sequence of Euler's number approximations */
fun e(): () -> Double {
  var currentSum = 1.0
  var n = 1

  tailrec fun factorial(n: Int, tmp: Int): Int =
    if (n == 1) tmp else factorial(n - 1, n * tmp)

  return {
    currentSum += 1.0 / factorial(n++, 1).toDouble()
    currentSum
  }
}

fun fastE(): () -> Double {
  var currentSum = 1.0
  val fact = factSeq()
  return {
    currentSum += 1.0 / fact().toDouble()
    currentSum
  }
}

fun factSeq(): () -> Int {
  var partial = 1
  var n = 1
  return {
    partial *= n++
    partial
  }
}

fun main() {
  //val e = e()
  val e = fastE()
  10.times {
    println(e())
  }
}