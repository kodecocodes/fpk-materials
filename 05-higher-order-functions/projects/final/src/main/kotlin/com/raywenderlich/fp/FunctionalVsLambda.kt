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

/** predicate with a single param */
typealias SinglePredicate<T> = (T) -> Boolean

/** Functional Interface Version */
fun interface ISinglePredicate<T> {

  fun accept(value: T): Boolean

  fun other() {
    println("I can have other methods")
  }
}

/** Extension function on any function of type (T) -> Boolean */
//fun <T> SinglePredicate<T>.whoAmI() = println("I'm a typealias")
fun <T> ((T) -> Boolean).whoAmI() =
  println("I'm a typealias")

/** Extension function on any function of type ISinglePredicate<T> */
fun <T> ISinglePredicate<T>.whoAmI() =
  println("I'm a functional interface")

fun main() {
  val isEven = { number: Int -> number % 2 == 0 }
  isEven.whoAmI()

  val isEvenFI = ISinglePredicate<Int> { number -> number % 2 == 0 }
  isEvenFI.whoAmI()
  isEvenFI.other()
}