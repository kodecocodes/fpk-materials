/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.fp.exercise2

/** First version of twice of type Fun<Int, Int> */
fun twice(x: Int) = 2 * x

/** Second version of twice of type Fun<Double, Double> */
fun twice(x: Double) = 2 * x

/** First version of half of type Fun<Int, Int> */
fun half(x: Int) = x / 2

/** Second version of half of type Fun<Double, Double> */
fun half(x: Double): Double = x / 2

fun main() {
  println("First version using Int")

  println("\nhalf after twice")
  println("x=0,  result=${half(twice(0))}")
  println("x=1,  result=${half(twice(1))}")
  println("x=2,  result=${half(twice(2))}")
  println("x=3,  result=${half(twice(3))}")

  println("\ntwice after half")
  println("x=0, result=${twice(half(0))}")
  println("x=1, result=${twice(half(1))}")
  println("x=2, result=${twice(half(2))}")
  println("x=3, result=${twice(half(3))}")

  println("\nSecond version using Double")

  println("\nhalf after twice")
  println("x=0.0, result=${half(twice(0.0))}")
  println("x=1.0, result=${half(twice(1.0))}")
  println("x=2.0, result=${half(twice(2.0))}")
  println("x=3.0, result=${half(twice(3.0))}")

  println("\ntwice after half")
  println("x=0.0, result=${twice(half(0.0))}")
  println("x=1.0, result=${twice(half(1.0))}")
  println("x=2.0, result=${twice(half(2.0))}")
  println("x=3.0, result=${twice(half(3.0))}")
}