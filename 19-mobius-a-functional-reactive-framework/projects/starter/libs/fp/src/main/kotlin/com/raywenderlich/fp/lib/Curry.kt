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

package com.raywenderlich.fp.lib

/** Abstraction of a function with two input parameters */
typealias Fun2<T1, T2, R> = (T1, T2) -> R
typealias Fun3<T1, T2, T3, R> = (T1, T2, T3) -> R
typealias Fun4<T1, T2, T3, T4, R> = (T1, T2, T3, T4) -> R
typealias Fun5<T1, T2, T3, T4, T5, R> = (T1, T2, T3, T4, T5) -> R

fun <T1, T2, R> Fun2<T1, T2, R>.curry(): (T1) -> (T2) -> R = { t1: T1 ->
  { t2: T2 ->
    this(t1, t2)
  }
}

fun <T1, T2, T3, R> Fun3<T1, T2, T3, R>.curry(): (T1) -> (T2) -> (T3) -> R = { t1: T1 ->
  { t2: T2, t3: T3 ->
    this(t1, t2, t3)
  }.curry()
}

fun <T1, T2, T3, T4, R> Fun4<T1, T2, T3, T4, R>.curry(): (T1) -> (T2) -> (T3) -> (T4) -> R =
  { t1: T1 ->
    { t2: T2, t3: T3, t4: T4 ->
      this(t1, t2, t3, t4)
    }.curry()
  }

fun <T1, T2, T3, T4, T5, R> Fun5<T1, T2, T3, T4, T5, R>.curry(): (T1) -> (T2) -> (T3) -> (T4) -> (T5) -> R =
  { t1: T1 ->
    { t2: T2, t3: T3, t4: T4, t5: T5 ->
      this(t1, t2, t3, t4, t5)
    }.curry()
  }