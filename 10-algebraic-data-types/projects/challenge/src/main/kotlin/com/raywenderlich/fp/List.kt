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

sealed class NaturalNumber
object Zero : NaturalNumber()
data class Successor(val prec: NaturalNumber) : NaturalNumber()

val ZERO = Zero
val ONE = Successor(Zero)
val TWO = Successor(Successor(Zero)) // Successor(ONE)
val THREE = Successor(Successor(Successor(Zero))) // Successor(TWO)
// ...


sealed interface FList<out A>
object Nil : FList<Nothing>
data class FCons<A>(val head: A, val tail: FList<A> = Nil) : FList<A>


val countList = FCons(1, FCons(2, FCons(3, FCons(4, FCons(5, Nil)))))

/** Sum of elements in FList<Int> */
fun FList<Int>.sum(): Int = when (this) {
  is Nil -> 0
  is FCons<Int> -> head + tail.sum()
}

fun main() {
  println(countList.sum())
}


