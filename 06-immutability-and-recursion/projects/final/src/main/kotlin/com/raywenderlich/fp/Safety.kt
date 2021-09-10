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

import kotlin.concurrent.thread

/** Counter mutable class */
data class MutableCounter(
  var count: Int = 0
)

val counter = MutableCounter()

val task = {
  randomDelay()
  counter.count++
  randomDelay()
  if (counter.count == 2) {
    println("Completed")
  }
}

data class Counter(
  val count: Int = 0
)

fun incAndCheck(counter: Counter): Counter {
  randomDelay()
  val newCounter = Counter(counter.count + 1)
  randomDelay()
  if (newCounter.count == 2) {
    println("Completed")
  }
  return newCounter
}

/*
fun main() {
  thread(block = task)
  thread(block = task)
}
 */

fun main() {
  var counter = Counter()
  lateinit var counter1: Counter
  val th1 = thread {
    counter1 = incAndCheck(counter)
  }
  th1.join()
  thread {
    incAndCheck(counter1)
  }
}