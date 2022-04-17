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

/** A second version of shiftLeft */
fun shiftLeftWithEffect(x: Int): Pair<Int, String> {
  return x shl 1 to "Shift Left of $x"
}

/** A second version of not */
fun notWithEffect(x: Int): Pair<Int, String> {
  return x.inv() to "Negate $x"
}

/** The Writer data type */
typealias Writer<A, B> = (A) -> Pair<B, String>

/** Writer<A, B> composition */
infix fun <A, B, C> Writer<B, C>.after(w: Writer<A, B>): Writer<A, C> = { a: A ->
  val (b, str) = w(a)
  val (c, str2) = this(b)
  c to "$str\n$str2\n"
}

infix fun <A, B, C> Writer<A, B>.compose(w: Writer<B, C>): Writer<A, C> =
  w after this

fun main() {
  val f: Writer<Int, Int> = ::shiftLeftWithEffect // 1
  val g: Writer<Int, Int> = ::notWithEffect // 2
  val shiftLeftAndNotWithEffects = g after f // 3
  println(shiftLeftAndNotWithEffects(10).second) // 4
}
