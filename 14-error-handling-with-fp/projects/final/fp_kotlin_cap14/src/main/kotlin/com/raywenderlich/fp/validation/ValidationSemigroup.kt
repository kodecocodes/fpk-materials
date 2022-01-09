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

package com.raywenderlich.fp.validation

import com.raywenderlich.fp.applicative.*
import com.raywenderlich.fp.lib.curry

interface Semigroup<T> {
  operator fun plus(rh: T): T
}

/** Composite of ValidationException */
data class ValidationExceptionComposite(
  private val errors: List<ValidationException>
) : Exception(), Semigroup<ValidationExceptionComposite> {
  override fun plus(
    rh: ValidationExceptionComposite
  ): ValidationExceptionComposite = ValidationExceptionComposite(this.errors + rh.errors)

  override fun getLocalizedMessage(): String {
    return errors.joinToString { it.localizedMessage }
  }
}

fun <E, T, R> ResultAp<E, T>.apsg(fn: ResultAp<E, (T) -> R>): ResultAp<E, R>
    where E : Throwable, E : Semigroup<E> =
  when (fn) {
    is Success<(T) -> R> -> successMap(fn.value)
    is Error<E> -> when (this) {
      is Success<T> -> Error(fn.error)
      is Error<E> -> Error(this.error + fn.error)
    }
  }

/** apsg as infix operator */
infix fun <E, T, R> ResultAp<E, (T) -> R>.applsg(a: ResultAp<E, T>)
    where E : Throwable, E : Semigroup<E> =
  a.apsg(this)

/** Name validation*/
fun validateNameSg(name: String): ResultAp<ValidationExceptionComposite, String> =
  if (name.length > 4)
    Success(name)
  else
    Error(ValidationExceptionComposite(listOf(ValidationException("Invalid Name"))))

/** Email validation */
fun validateEmailSg(email: String): ResultAp<ValidationExceptionComposite, String> =
  if (email.contains("@"))
    Success(email)
  else
    Error(ValidationExceptionComposite(listOf(ValidationException("Invalid email"))))


fun main() {
  val userBuilder = ::User.curry()
  val userApplicative = ResultAp.success(userBuilder)
  val idAp = ResultAp.success(1)
  /*
  (userApplicative appl
      idAp appl
      validateName("") appl
      validateEmail(""))
    .errorMap {
      println("Error: $it"); it
    }
    .successMap {
      println("Success $it")
    }
   */
  (userApplicative applsg
      idAp applsg
      validateNameSg("Massimo") applsg
      validateEmailSg("max@maxcarli.it"))
    .errorMap {
      println(it.localizedMessage); it
    }.successMap {
      println("Success $it")
    }
}