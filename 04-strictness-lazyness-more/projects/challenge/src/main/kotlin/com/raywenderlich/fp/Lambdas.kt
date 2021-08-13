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

val empty = {}

// val lambda = { a, b -> a + b } // Error

// var operation = { a: Int, b: Int -> a + b }
var operation: (Int, Int) -> Int = { a, b -> a + b }

val testLambda = { a: Int ->
  val doubleA = a * 2
  if (doubleA > 10) "$doubleA is Greater that 10"
  else "$doubleA is Smaller or Equals to 10"
}

val multiplyBy2: Fun<Int, Int> = { x -> 2 * x }


class A : C()
open class C
class D : B()
open class B

typealias LambdaType = (A) -> B

//    C -> D
//    ^   v
//    A -> B

interface Combinable<A> {
  fun combine(rh: A): A
}

fun <A : Combinable<A>> combine(lh: A, rh: A): A = lh.combine(rh)

// val combineLambda = { lh: A, rh: A -> lh.append(rh) } // NOT WORKING

/*
// NOT VALID
typealias CombineLambdaType<A : Combinable<A>> = (A, A) -> A
 */
typealias CombineLambdaType<A> =
      (Combinable<A>, Combinable<A>) -> Combinable<A> // 1

//val combineLambda: CombineLambdaType<A> = { lh: A, rh: A -> lh.combine(rh) } // NOT WORKING


fun main() {
  // val result = operation(3, 4)
  val result = operation.invoke(3, 4)
  println(result)

  var lambda: LambdaType = { a -> B() }
  lambda = { c: C -> D() }
}