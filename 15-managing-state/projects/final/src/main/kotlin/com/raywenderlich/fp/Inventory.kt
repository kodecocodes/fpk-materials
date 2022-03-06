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

import com.raywenderlich.fp.lib.curry
import com.raywenderlich.fp.lib.pipe

/** A simple Product data class */
data class Product(val id: String, val name: String)

/** A Product with a Sku */
data class SkuProduct(val product: Product, val sku: String)

var count = 0
fun createSku(): String =
  "RAY-PROD-${String.format("%04d", count++)}"

/** Function that create a SkuProduct from Product handling state */
val assignSku: (Product, Int) -> Pair<SkuProduct, Int> =
  { product: Product, state ->
    val newSku = "RAY-PROD-${String.format("%04d", state)}"
    SkuProduct(product, newSku) to state + 1
  }

/** The curried version of assignSku*/
val curriedAssignSku: (Product) -> StateTransformer<Int, SkuProduct> = assignSku.curry()

fun main() {
  val prod1 = Product("1", "Cheese")
  val prod2 = Product("2", "Bread")
  val prod3 = Product("3", "Cake")

//  SkuProduct(prod1, createSku()) pipe ::println
//  SkuProduct(prod2, createSku()) pipe ::println
//  SkuProduct(prod3, createSku()) pipe ::println

//  val state0 = 0
//  val (sku1, state1) = skuStateTransformer(state0)
//  SkuProduct(prod1, sku1) pipe ::println
//  val (sku2, state2) = skuStateTransformer(state1)
//  SkuProduct(prod2, sku2) pipe ::println
//  val (sku3, state3) = skuStateTransformer(state2)
//  SkuProduct(prod3, sku3) pipe ::println

  val state0 = 0
  val (skuProd1, state1) = curriedAssignSku(prod1)(state0)
  skuProd1 pipe ::println
  val (skuProd2, state2) = curriedAssignSku(prod2)(state1)
  skuProd2 pipe ::println
  val (skuProd3, state3) = curriedAssignSku(prod3)(state2)
  skuProd3 pipe ::println
}