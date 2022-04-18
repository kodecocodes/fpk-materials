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
import org.junit.Test
import kotlin.random.Random

class PropertyTestTest {

//  @Test
//  fun `sum test using predefined values`() {
//    Truth.assertThat(sum(2, 3)).isEqualTo(5)
//    Truth.assertThat(sum(2, 5)).isEqualTo(7)
//    Truth.assertThat(sum(-2, 5)).isEqualTo(3)
//  }
//
//  @Test
//  fun `sum test using random values`() {
//    val firstValue = Random.nextInt()
//    val secondValue = Random.nextInt()
//    val expectedValue = firstValue + secondValue
//    Truth.assertThat(sum(firstValue, secondValue))
//      .isEqualTo(expectedValue)
//  }
//
//  @Test
//  fun `sum test using random values 100 times`() {
//    100.times {
//      val firstValue = Random.nextInt()
//      val secondValue = Random.nextInt()
//      val expectedValue = firstValue + secondValue
//      Truth.assertThat(sum(firstValue, secondValue))
//        .isEqualTo(expectedValue)
//    }
//  }
//
//  @Test
//  fun `test someting wring with +`() {
//    Truth.assertThat(sum(Int.MAX_VALUE, 1)).isEqualTo(Int.MIN_VALUE)
//  }

  @Test
  fun `test sum is commutative`() {
    100.times {
      val firstValue = Random.nextInt()
      val secondValue = Random.nextInt()
      val result1 = sum(firstValue, secondValue)
      val result2 = sum(secondValue, firstValue)
      Truth.assertThat(result1).isEqualTo(result2)
    }
  }

  @Test
  fun `test addition is not multiplication`() {
    100.times {
      val randomValue = Random.nextInt()
      val result1 = sum(sum(randomValue, 1), 1)
      val result2 = sum(randomValue, 2)
      Truth.assertThat(result1).isEqualTo(result2)
    }
  }

  @Test
  fun `test using unit value for addition`() {
    100.times {
      val randomValue = Random.nextInt()
      val result1 = sum(randomValue, 0)
      val expected = randomValue
      Truth.assertThat(result1).isEqualTo(expected)
    }
  }

  @Test
  fun `Property-based test for sum`() {
    100.times {
      val additionProp =
        CommutativeProperty<Int>() and
            AssociativeProperty() and
            IdentityProperty(0)
      val evaluation = additionProp(IntGenerator) {
        sum(it[0], it[1])
      }
      Truth.assertThat(evaluation).isTrue()
    }
  }
}