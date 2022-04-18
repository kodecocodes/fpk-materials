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

data class MutableKey(
  var id: Int
)

data class Key(
  val id: Int
)

fun main() {
  //val key1 = MutableKey(1)
  //val key2 = MutableKey(2)
  val key1 = Key(1)
  val key2 = Key(2)
  val myMap = mutableMapOf(
    key1 to "First",
    key2 to "Second"
  )
  println("Value for $key1 is ${myMap[key1]}")
  //key1.id = 2
  println("Value for $key1 is ${myMap[key1]} after key1 update")
  println("Value for $key2 is ${myMap[key2]}")
  println("The Map is $myMap")
  myMap.remove(key1).also { println("Removed $key1 from myMap") }
  myMap.remove(key2).also { println("Removed $key2 from myMap") }
  println("The Map after remove is $myMap")
  println("Value for $key1 is ${myMap[key1]} after key1 remove")
  println("Value for $key2 is ${myMap[key2]} after key2 remove")
}