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

package com.raywenderlich.fp.exercise1

/** Distance passing all the parameters */
val distanceLambda = { x1: Double, y1: Double, x2: Double, y2: Double ->
  val sqr1 = (x2 - x1) * (x2 - x1)
  val sqr2 = (y2 - y1) * (y2 - y1)
  Math.sqrt(sqr1 + sqr2)
}

typealias Point = Pair<Double, Double>

/** Distance using Points */
val distanceLambdaWithPairs = { p1: Point, p2: Point ->
  val sqr1 = Math.pow(p1.first - p2.first, 2.0)
  val sqr2 = Math.pow(p1.second - p2.second, 2.0)
  Math.sqrt(sqr1 + sqr2)
}

fun main() {
  println(distanceLambda(0.0, 0.0, 1.0, 1.0))
  println(distanceLambdaWithPairs(0.0 to 0.0, 1.0 to 1.0))
}