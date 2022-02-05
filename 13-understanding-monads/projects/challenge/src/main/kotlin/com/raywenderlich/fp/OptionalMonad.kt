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

import com.raywenderlich.fp.exercise1.None
import com.raywenderlich.fp.exercise1.Optional
import com.raywenderlich.fp.exercise1.optionalFish
import com.raywenderlich.fp.exercise2.flatMap
import kotlin.math.sqrt

/** From String to Int */
fun strToInt(str: String): Optional<Int> =
  try {
    Optional.lift(str.toInt())
  } catch (nfe: NumberFormatException) {
    None
  }

fun root(number: Int): Optional<Double> =
  if (number < 0) None else Optional.lift(sqrt(number.toDouble()))

fun main() {
  val strToRoot = ::strToInt optionalFish ::root
  strToRoot("onetwothree") pipe ::println
  strToRoot("123") pipe ::println

  strToInt("onetwothree").flatMap(::root) pipe ::println
  strToInt("123").flatMap(::root) pipe ::println
}