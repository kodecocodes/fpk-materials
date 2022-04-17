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

fun interface Logger {
  fun log(msg: String)
}

fun interface Calculator {
  fun multiply(a: Double, b: Double): Double
}

fun interface DB {
  fun save(result: Double)
}

fun interface CalculatorFactory {
  fun create(db: DB, logger: Logger): Calculator
}

val calculatorFactoryImpl =
  CalculatorFactory { db, logger ->
    object : Calculator {
      override fun multiply(a: Double, b: Double): Double {
        val result = a * b
        db.save(result)
        logger.log("$a * $b = $result")
        return result
      }
    }
  }

fun main() {
  val db = DB {
    println("Saving value: $it")
  }
  val simpleLogger = Logger {
    println("Logging: $it")
  }
  val fileLogger = Logger {
    println("Logging on File: $it")
  }
//  val calculator1 = calculatorFactoryImpl.create(db, simpleLogger)
//  val calculator2 = calculatorFactoryImpl.create(db, fileLogger)
//  println(calculator1.multiply(2.0, 3.0))
//  println(calculator2.multiply(2.0, 3.0))
  val partialFactory = calculatorFactoryImpl::create.curry()
  val partialFactoryWithDb = db pipe partialFactory
  val calculator1 = partialFactoryWithDb(simpleLogger)
  val calculator2 = partialFactoryWithDb(fileLogger)
  println(calculator1.multiply(2.0, 3.0))
  println(calculator2.multiply(2.0, 3.0))
}
