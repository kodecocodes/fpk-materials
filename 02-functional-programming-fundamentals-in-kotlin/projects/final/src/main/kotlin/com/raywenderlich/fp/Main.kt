/*
 * Copyright (c) 2022 Razeware LLC
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
package com.raywenderlich.fp

/** Double the input */
fun twice(a: Int): Int = a * 2

/** Format a string as a result */
fun format(b: Int): String = "Result is $b"

/** Returns the length of the String */
fun length(s: String): Int = s.length

/** Half the input */
fun half(a: Int): Int = a / 2

fun one(u: Unit): Int = 1
fun two(u: Unit): Int = 2
fun minusThree(u: Unit): Int = -3

/**
 * Composing twice and format
 */
fun formatAfterTwice(x: Int) = format(twice(x))

fun main() {

  println(format(twice(37)))
  println(formatAfterTwice(37))

  val f: Fun<Int, Int> = ::twice
  val g: Fun<Int, String> = ::format
  val formatTwice = g after f
  println(formatTwice(37))

  val h: Fun<String, Int> = ::length
  val leftSide = (h after g) after f
  val rightSide = h after (g after f)
  println(leftSide(37) == rightSide(37))

  /*
  val nothing = absurd<Int>(TODO())
  println(nothing)
  */

  // twice 2
  val twiceTwo = ::twice after ::two
  println(twiceTwo(Unit))
}