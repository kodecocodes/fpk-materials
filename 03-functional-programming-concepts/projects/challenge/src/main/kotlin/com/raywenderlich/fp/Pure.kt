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

package com.raywenderlich.fp

import kotlin.random.Random

fun twice(x: Int): Int = x * 2

fun twiceAndLog(x: Int): Int {
  val result = x * 2
  println("2 * $x = $result")
  return result
}

fun randomInc(a: Int): Int = a + Random.nextInt()

///fun abs(x: Int) = if (x < 0) -x else x

fun negate(x: Int) = -x
fun identity(x: Int) = x
fun abs(x: Int) = if (x < 0) negate(x) else identity(x)


var sharedCount = 1
fun comp1(x: Int): String = "Hello $(x + sharedCount)"

fun comp2(x: String): Int = x.length - sharedCount

val comp2AfterComp1 = ::comp2 after ::comp1

fun main() {
  var f: Fun<Int, Int> = ::twice
  println("Executing twice(10)")
  f(10)
  f = ::twiceAndLog
  println("Executing twiceAndLog(10)")
  f(10)

  println(twice(10))
  println(twice(10))
  println(twice(10))
  println(twice(10))
  println(twice(10))

  println(randomInc(10))
  println(randomInc(10))
  println(randomInc(10))
  println(randomInc(10))
}