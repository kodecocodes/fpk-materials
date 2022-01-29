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

/** A simple pure functon */
fun pureFunction(x: Int): Int = x * x - 1

/** Simple function with effect */
fun functionWithEffect(x: Int): Int {
  val result = x * x - 1
  println("Result: $result")
  return result
}

val effectComposition = ::functionWithEffect compose ::functionWithEffect

/** The Writer data type */
typealias Writer<A, B> = (A) -> Pair<B, String>

/** Implementation that add the side effect as part of the output */
fun functionWithWriter(x: Int): Pair<Int, String> {
  val result = x * x - 1
  return result to "Result: $result"
}

/** Writer composition */
infix fun <A, B, C> Writer<A, B>.compose(
  g: Writer<B, C>
): Writer<A, C> = { a: A ->
  val (b, str) = this(a)
  val (c, str2) = g(b)
  c to "$str\n$str2\n"
}

fun main() {
  /*
  pureFunction(5) pipe ::println
  pureFunction(5) pipe ::println
  pureFunction(5) pipe ::println
  println("-------------------------")
  functionWithEffect(5) pipe ::println
  functionWithEffect(5) pipe ::println
  functionWithEffect(5) pipe ::println
  */
  /*
  listOf(1, 2, 3).map(::pureFunction) pipe ::println
  println("-------------------------")
  listOf(1, 2, 3).map(::pureFunction).map(::pureFunction) pipe ::println
  listOf(1, 2, 3).map(::pureFunction compose ::pureFunction) pipe ::println
  println("-------------------------")
  listOf(1, 2, 3).map(::functionWithEffect).map(::functionWithEffect) pipe ::println
  listOf(1, 2, 3).map(::functionWithEffect compose ::functionWithEffect) pipe ::println
  println("-------------------------")
   */
  val square = { a: Int -> a * a }
  val double = { a: Int -> a * a }
  val squareFunAndWrite = square compose ::functionWithWriter
  val doubleFunAndWrite = double compose ::functionWithWriter
  val compFunWithWriter = squareFunAndWrite compose doubleFunAndWrite
  compFunWithWriter(5).second pipe ::println
}