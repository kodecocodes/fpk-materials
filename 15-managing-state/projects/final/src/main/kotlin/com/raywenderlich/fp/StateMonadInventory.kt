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

import com.raywenderlich.fp.lib.*


val products = FList.of(
  Product("1", "Eggs"),
  Product("2", "Flavor"),
  Product("3", "Cake"),
  Product("4", "Pizza"),
  Product("5", "Water")
)

var currentCount = 0
fun inventoryMap(products: FList<Product>): FList<SkuProduct> {
  return products.map {
    SkuProduct(it, "RAY-PROD-${String.format("%04d", currentCount++)}")
  }
}

fun inventoryMapWithCount(products: FList<Product>): FList<SkuProduct> {
  var internalCount = 0
  return products.map {
    SkuProduct(it, "RAY-PROD-${String.format("%04d", internalCount++)}")
  }
}

fun listInventory(products: FList<Product>): (Int) -> Pair<Int, FList<SkuProduct>> =
  when (products) {
    is Nil -> { count: Int -> count to Nil }
    is FCons<Product> -> { count: Int ->
      val (newState, tailInventory) = listInventory(products.tail)(count)
      val sku = "RAY-PROD-${String.format("%04d", newState)}"
      newState + 1 to FCons(SkuProduct(products.head, sku), tailInventory)
    }
  }


val addSku: (Product) -> State<Int, SkuProduct> = { prod: Product ->
  State<Int, SkuProduct> { state: Int ->
    val newSku = "RAY-PROD-${String.format("%04d", state)}"
    SkuProduct(prod, newSku) to state + 1
  }
}

fun inventory(list: FList<Product>): State<Int, FList<SkuProduct>> =
  when (list) {
    is Nil -> State.lift(Nil)
    is FCons<Product> -> {
      val head = State.lift<Int, Product>(list.head).flatMap(addSku)
      val tail = inventory(list.tail)
      head.zip(tail) { a: SkuProduct, b: FList<SkuProduct> ->
        FCons(a, b)
      }
    }
  }

fun main() {
  //inventoryMap(products).forEach(::println)
  //listInventory(products)(0).second.forEach(::println)
  inventory(products)(0).first.forEach(::println)
}