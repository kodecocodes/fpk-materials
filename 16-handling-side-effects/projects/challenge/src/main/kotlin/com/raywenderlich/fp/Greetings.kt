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

import com.raywenderlich.fp.lib.pipe
import java.util.*

/** readName as a transformation of the World */
val readName: (World) -> Pair<String, World> = { w: World ->
  Scanner(System.`in`).nextLine() to World
}

/** printString as a transformation of the World */
val printString: (String) -> SideEffect = { str: String ->
  { a: World ->
    print(str) to World
  }
}

/** Greetings app using pure functions */
fun askNameAndPrintGreetings(): (World) -> World =
  { w0: World ->
    val w1 = printString("What's your name? ")(w0)
    val (name, w2) = readName(w1)
    printString("Hello $name! \n")(w2)
  }

/** readName as a function of type WorldT<String> */
val readNameT: WorldT<String> = readName

/** printString as a function of type (String) -> WorldT<Unit> */
val printStringT: (String) -> WorldT<Unit> = { str: String ->
  { w: World ->
    Unit to printString(str)(w)
  }
}

/** Using myOp foraskNameAndPrintGreetings */
fun askNameAndPrintGreetingsT(): WorldT<Unit> =
  printStringT("What's your name? ") myOp { _ ->
    readNameT myOp { name ->
      printStringT("Hello $name! \n")
    }
  }

fun main() {
//  print("What's your name? ")
//  val name = Scanner(System.`in`).nextLine()
//  println("Hello $name\n")

//  readName(World) pipe ::println
//  printString("Hello Max \n")(World) pipe ::println

//  askNameAndPrintGreetings()(World) pipe ::println

  askNameAndPrintGreetingsT()(World) pipe ::println
}