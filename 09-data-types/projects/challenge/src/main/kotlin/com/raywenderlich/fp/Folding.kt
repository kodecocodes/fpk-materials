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
 *
 */

package com.raywenderlich.fp

/** Imperative sum */
fun List<Int>.imperativeSum(): Int {
  var sum = 0
  for (i in 0 until size) {
    sum += this[i]
  }
  return sum
}

fun List<Int>.declarativeSum(): Int {
  tailrec fun helper(pos: Int, acc: Int): Int {
    if (pos == size) {
      return acc
    }
    return helper(pos + 1, this[pos] + acc)
  }
  return helper(0, 0)
}

fun List<Int>.declarativeProduct(): Int {
  tailrec fun helper(pos: Int, acc: Int): Int {
    if (pos == size) {
      return acc
    }
    return helper(pos + 1, this[pos] * acc)
  }
  return helper(0, 1)
}

fun <T, S> List<T>.declarativeFold(start: S, combineFunc: (S, T) -> S): S {

  tailrec fun helper(pos: Int, acc: S): S {
    if (pos == size) {
      return acc
    }
    return helper(pos + 1, combineFunc(acc, this[pos]))
  }
  return helper(0, start)
}

/** A foldRight implementation */
fun <T, S> List<T>.declarativeFoldRight(start: S, combineFunc: (T, S) -> S): S {
  fun helper(pos: Int): S {
    if (pos == size) {
      return start
    }
    return combineFunc(this[pos], helper(pos + 1))
  }
  return helper(0)
}

/** A foldRight implementation */
/*
fun <T, S> List<T>.declarativeFoldRight(start: S, combineFunc: (T, S) -> S): S {
  tailrec fun helper(pos: Int, acc: S): S {
    if (pos == size) {
      return acc
    }
    return helper(pos + 1, combineFunc(this[pos], acc))
  }
  return helper(0, start)
}
 */

fun main() {
  val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  //list.imperativeSum() pipe ::println
  //List<Int>::imperativeSum compose ::println epip list
  //list.declarativeSum() pipe ::println
  //list.declarativeProduct() pipe ::println
  //list.declarativeFold(0) { acc, item -> acc + item } pipe ::println
  //list.declarativeFold(1) { acc, item -> acc * item } pipe ::println
  //list.fold(0) { acc, item -> acc + item } pipe ::println
  //list.fold(1) { acc, item -> acc * item } pipe ::println
  //list.declarativeFold(0) { acc, item -> acc + item } pipe ::println

  //val list = listOf(1, 2, 3, 4, 5)
  //list.declarativeFold(0) { acc, item -> acc + item } pipe ::println


  //list.imperativeSum() pipe ::println
  //list.declarativeSum() pipe ::println
  //list.declarativeProduct() pipe ::println
  /*
  // List<Int>::imperativeSum compose ::println epip list
  // List<Int>::declarativeSum compose ::println epip list
  list.declarativeFold(0) { item, acc -> acc + item } pipe ::println
  list.declarativeFold(1) { item, acc -> acc * item } pipe ::println
  list.fold(0) { item, acc -> acc + item } pipe ::println
  list.fold(1) { item, acc -> acc * item } pipe ::println
  list.declarativeFoldRight(0) { item, acc -> acc + item } pipe ::println
  list.declarativeFoldRight(1) { item, acc -> acc * item } pipe ::println
  list.foldRight(0) { item, acc -> acc + item } pipe ::println
  list.foldRight(1) { item, acc -> acc * item } pipe ::println
  println("fold")
  list.map(Int::toString).declarativeFold("") { acc, item -> acc + item } pipe ::println
  list.map(Int::toString).fold("") { acc, item -> acc + item } pipe ::println
   */
  //println("foldRight")
  //list.map(Int::toString).declarativeFoldRight("") { item, acc -> acc + item } pipe ::println
  //list.map(Int::toString).foldRight("") { item, acc -> acc + item } pipe ::println
  list.map(Int::toString).declarativeFold("") { acc, item -> acc + item } pipe ::println
  list.map(Int::toString).fold("") { acc, item -> acc + item } pipe ::println
  list.map(Int::toString).declarativeFoldRight("") { item, acc -> acc + item } pipe ::println
  list.map(Int::toString).foldRight("") { item, acc -> acc + item } pipe ::println
}