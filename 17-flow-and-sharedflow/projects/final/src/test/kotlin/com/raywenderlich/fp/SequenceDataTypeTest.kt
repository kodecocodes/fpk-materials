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

import com.google.common.truth.Truth
import com.raywenderlich.fp.lib.*
import org.junit.Test

class SequenceDataTypeTest {

  @Test
  fun `Identity Functor Law for Sequences`() {
    val intToStringFunGenerator = funGenerator<Int, String>(StringGenerator(5))
    val i = { s: String -> s }
    100.times {
      val f = intToStringFunGenerator.one()
      val seq = IntGenerator.generate(5).asSequence()
      val list1 = seq.map(f compose i).toList()
      val list2 = seq.map(f).toList()
      Truth.assertThat(list1).isEqualTo(list2)
    }
  }

  @Test
  fun `Composition Functor Law for Sequences`() {
    val intToStringFunGenerator = funGenerator<Int, String>(StringGenerator(5))
    val stringToLongFunGenerator = funGenerator<String, Long>(LongGenerator)
    100.times {
      val f = intToStringFunGenerator.one()
      val g = stringToLongFunGenerator.one()
      val seq = IntGenerator.generate(5).asSequence()
      val list1 = seq.map(f compose g).toList()
      val list2 = seq.map(f).map(g).toList()
      Truth.assertThat(list1).isEqualTo(list2)
    }
  }
}