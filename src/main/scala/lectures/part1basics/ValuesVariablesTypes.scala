package com.rockthejvm
package lectures.part1basics

object ValuesVariablesTypes extends App {
  private val x: Int = 42
  println(x)

  // VALs are immutable
  // Compiles can infer types

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 45678909876456789L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  private var aVariable: Int = 4
  aVariable = 5
}
