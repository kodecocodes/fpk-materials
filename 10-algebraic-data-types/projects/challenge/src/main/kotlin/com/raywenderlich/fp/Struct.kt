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

import compose

/** A Pair of Boolean values*/
typealias BoolPair = Pair<Boolean, Boolean>

val bool1 = true to true
val bool2 = true to false
val bool3 = false to true
val bool4 = false to false

/** Example of a type with 3 values */
enum class Triage {
  RED, YELLOW, GREEN
}

/** A Pair of Boolean and a Triage*/
typealias BoolTriage = Pair<Boolean, Triage>

val triple1 = true to Triage.RED
val triple2 = true to Triage.YELLOW
val triple3 = true to Triage.GREEN
val triple4 = false to Triage.RED
val triple5 = false to Triage.YELLOW
val triple6 = false to Triage.GREEN

/** Example of a pair of Unit and a type */
typealias UnitTriage = Pair<Unit, Triage>

val unit11 = Unit to Triage.RED
val unit21 = Unit to Triage.YELLOW
val unit31 = Unit to Triage.GREEN

/** Example of a pair of a type with Unit */
typealias TriageUnit = Pair<Triage, Unit>

val unit12 = Triage.RED to Unit
val unit22 = Triage.YELLOW to Unit
val unit32 = Triage.GREEN to Unit

/** Example of a simple function */
fun isEven(a: Int): Boolean = a % 2 == 0

/** Example of a function that translates a Boolean into an Int */
fun booleanToInt(even: Boolean): Int = if (even) 1 else 0

val isEvenInt = ::isEven compose ::booleanToInt

typealias NothingTriage = Pair<Nothing, Triage>

//val nothing1 : NothingTriage = Pair(???, Triage.RED)

/** Example of a Struct */
data class Struct(val enabled: Boolean, val triage: Triage, val value: Byte)

/** Antoher example of a Struct */
data class AnotherStruct(val enabled: Boolean, val triage: Triage, val name: String)