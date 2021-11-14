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

package com.raywenderlich.fp.exercise3

import com.raywenderlich.fp.Fun
import com.raywenderlich.fp.curry

typealias Fun3<I1, I2, I3, O> = (I1, I2, I3) -> O
typealias Fun4<I1, I2, I3, I4, O> = (I1, I2, I3, I4) -> O
typealias Fun5<I1, I2, I3, I4, I5, O> = (I1, I2, I3, I4, I5) -> O

typealias Chain3<I1, I2, I3, O> = (I1) -> (I2) -> (I3) -> O
typealias Chain4<I1, I2, I3, I4, O> = (I1) -> (I2) -> (I3) -> (I4) -> O
typealias Chain5<I1, I2, I3, I4, I5, O> = (I1) -> (I2) -> (I3) -> (I4) -> (I5) -> O

fun <I1, I2, I3, O> Fun3<I1, I2, I3, O>.curry():
    Chain3<I1, I2, I3, O> = { i1: I1, i2: I2 ->
  { i3: I3 ->
    this(i1, i2, i3)
  }
}.curry()

fun <I1, I2, I3, I4, O> Fun4<I1, I2, I3, I4, O>.curry():
    Chain4<I1, I2, I3, I4, O> = { i1: I1, i2: I2, i3: I3 ->
  { i4: I4 ->
    this(i1, i2, i3, i4)
  }
}.curry()

fun <I1, I2, I3, I4, I5, O>
    Fun5<I1, I2, I3, I4, I5, O>.curry():
    Chain5<I1, I2, I3, I4, I5, O> =
  { i1: I1, i2: I2, i3: I3, i4: I4 ->
    { i5: I5 ->
      this(i1, i2, i3, i4, i5)
    }
  }.curry()

/** A simple function to push a value into a function */
infix fun <A, B> Fun<A, B>.epip(a: A): B = this(a)

fun main() {
  val sum = { a: Int, b: Int, c: Int, d: Int, e: Int ->
    a + b + c + d + e
  }
  val curriedSum = sum.curry()
  //println(curriedSum(1)(2)(3)(4)(5))
  //val result = 5 pipe 4 pipe 3 pipe 2 pipe 1 pipe curriedSum
  //val result = 5 pipe (4 pipe (3 pipe (2 pipe (1 pipe curriedSum))))
  val result = curriedSum epip 1 epip 2 epip 3 epip 4 epip 5
  println(result)
}