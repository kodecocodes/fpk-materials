/*
 * Copyright (c) 2021 Razeware LLC
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
package com.raywenderlich.fp.challenge2

import com.raywenderlich.fp.Predicate

/** The set of all the odd Ints */
val oddIntSet: Predicate<Int> = { a: Int -> a % 2 != 0 }

/** The set of all the multiple of 37 ints*/
val multipleOf37: Predicate<Int> = { a: Int -> a % 37 == 0 }

/** The union of the two sets */
fun <A> union(set1: Predicate<A>, set2: Predicate<A>): Predicate<A> = { a: A ->
  set1(a) || set2(a)
}

/** The intersection of the two sets */
fun <A> intersection(set1: Predicate<A>, set2: Predicate<A>): Predicate<A> = { a: A ->
  set1(a) && set2(a)
}

fun main() {
  val oddMultipleOf37Union =
    union(oddIntSet, multipleOf37)
  val oddMultipleOf37Intersection =
    intersection(oddIntSet, multipleOf37)

  println("1   is in union ${oddMultipleOf37Union(1)}")
  println("37  is in union ${oddMultipleOf37Union(37)}")
  println("74  is in union ${oddMultipleOf37Union(74)}")
  println("100 is in union ${oddMultipleOf37Union(100)}")

  println("1   is in intersection ${oddMultipleOf37Intersection(1)}")
  println("37  is in intersection ${oddMultipleOf37Intersection(37)}")
  println("74  is in intersection ${oddMultipleOf37Intersection(74)}")
  println("100 is in intersection ${oddMultipleOf37Intersection(100)}")
}