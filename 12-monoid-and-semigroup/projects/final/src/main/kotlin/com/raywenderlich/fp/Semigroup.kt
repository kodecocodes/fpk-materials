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

import pipe

/** Merge and combine the values in two lists */
//fun <T> mergeAndCombine(listA: List<T>, listB: List<T>, combine: (T, T) -> T): List<T> {
//  var i = 0
//  val result = mutableListOf<T>()
//  while (i < listA.size || i < listB.size) {
//    val first = if (i < listA.size) listA[i] else null
//    val second = if (i < listB.size) listB[i] else null
//    if (first != null && second != null) {
//      result.add(combine(first, second))
//    } else if (first != null) {
//      result.add(first)
//    } else if (second != null) {
//      result.add(second)
//    }
//    i++
//  }
//  return result
//}
fun <T> mergeAndCombine(listA: List<T>, listB: List<T>, semigroup: Semigroup<T>): List<T> {
  var i = 0
  val result = mutableListOf<T>()
  while (i < listA.size || i < listB.size) {
    val first = if (i < listA.size) listA[i] else null
    val second = if (i < listB.size) listB[i] else null
    if (first != null && second != null) {
      result.add(semigroup.combine(first, second))
    } else if (first != null) {
      result.add(first)
    } else if (second != null) {
      result.add(second)
    }
    i++
  }
  return result
}

/** Semigroup definition */
interface Semigroup<T> {
  val combine: T.(T) -> T
}

object SemigroupIntMult : Semigroup<Int> {
  override val combine: Int.(Int) -> Int
    get() = Int::times
}

fun main() {
  val listA = listOf(1, 2, 3, 4, 5, 6)
  val listB = listOf(3, 5, 6)
  mergeAndCombine(listA, listB, SemigroupIntMult) pipe ::println
}