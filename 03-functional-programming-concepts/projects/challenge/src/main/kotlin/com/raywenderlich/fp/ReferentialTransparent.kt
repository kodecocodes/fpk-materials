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

import com.raywenderlich.fp.util.assertOrThrow


fun main() {
  // Example 1
  val expr1 = { 3 * 10 }
  val (a1, a2) = expr1() to expr1()
  val expr1Eval = expr1()
  val (a1Eval, a2Eval) = expr1Eval to expr1Eval
  assertOrThrow("expr1 is not RT") {
    a1 == a1Eval && a2 == a2Eval
  }
  /*
  // Uncomment when running Exercise 1
  // Example 1 with referential opaque
  var count = 1
  val expr2 = { 3 * 10 * ++count }
  val (b1, b2) = expr2() to expr2()
  val expr2Eval = expr2()
  val (b1Eval, b2Eval) = expr2Eval to expr2Eval
  assertOrThrow("expr2 is not RT") {
    b1 == b1Eval && b2 == b2Eval
  }
   */

  // Example 2
  val expr3 = { println("Hello World!") }
  //val (c1, c2) = expr3() to expr3()
  val expr3Eval = expr3()
  val (c1Eval, c2Eval) = expr3Eval to expr3Eval
  assertOrThrow("expr2 is not RT") {
    true//c1 == c1Eval && c2 == c2Eval
  }
}
