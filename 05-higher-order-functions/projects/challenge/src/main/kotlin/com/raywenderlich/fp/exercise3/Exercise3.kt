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

package com.raywenderlich.fp.exercise3

/** Example of Reader as Functional Interface */
fun interface Reader {
  fun readChar(): Char?
  fun readString(): String {
    val result = StringBuilder()
    do {
      val nextChar = readChar()
      if (nextChar != null) {
        result.append(nextChar)
      }
    } while (nextChar != null)
    return result.toString()
  }
}

class MyReader(val str: String) : Reader {
  var pos = 0
  override fun readChar(): Char? =
    if (pos < str.length) str[pos++] else null
}

/** Functional Interface as input parameter */
fun consumeReader(reader: Reader) {
  println(reader.readString())
}

fun main() {
  // Example
  //val input = MyReader("This is a String!")
  //println(input.readString())

  // Multiple usage
  //  val inputString = "This is a String!"
  //  var pos = 0
  //  var pos2 = 0
  //  val input = Reader { if (pos < inputString.length) inputString[pos++] else null }
  //  val input2 = Reader { if (pos2 < inputString.length) inputString[pos2++] else null }
  //  println(input.readString())
  //  println(input2.readString())

  // Passing the functional interaface as input parameter
  var pos = 0
  val inputString = "This is a String!"
  consumeReader({ if (pos < inputString.length) inputString[pos++] else null })

}