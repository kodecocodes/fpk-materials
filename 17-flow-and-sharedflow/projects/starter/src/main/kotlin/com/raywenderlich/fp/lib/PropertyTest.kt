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
@file:JvmName("PropertyTest")

package com.raywenderlich.fp.lib

import kotlin.random.Random

/** Abstraction for the generation of random values */
fun interface Generator<T> {
  fun generate(n: Int): List<T>
}

/** Generator implementation for Int */
object IntGenerator : Generator<Int> {
  override fun generate(n: Int): List<Int> = List(n) { Random.nextInt() }
}

/** Generator implementation for Long */
object LongGenerator : Generator<Long> {
  override fun generate(n: Int): List<Long> = List(n) { Random.nextLong() }
}

/** Utility that returns a simple value */
fun <T> Generator<T>.one(): T = generate(1)[0]

/** Generator implementation for String */
class StringGenerator(
  private val minLength: Int = 0, private val maxLength: Int = 10
) : Generator<String> {
  val chars =
    "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890!±§!@£$%^&*()_+-="

  override fun generate(n: Int): List<String> = List(n) {
    val length = Random.nextInt(minLength, maxLength)
    val currentString = StringBuilder()
    (1..length).forEach {
      currentString.append(chars[Random.nextInt(0, chars.length)])
    }
    currentString.toString()
  }
}

/** Abstraction of a generic property */
interface Property<T> {
  operator fun invoke(
    gen: Generator<T>, fn: (List<T>) -> T
  ): Boolean
}

/** Both must be true */
infix fun <T> Property<T>.and(rightProp: Property<T>): Property<T> = object : Property<T> {
  override fun invoke(gen: Generator<T>, fn: (List<T>) -> T): Boolean =
    this@and(gen, fn) && rightProp(gen, fn)
}
