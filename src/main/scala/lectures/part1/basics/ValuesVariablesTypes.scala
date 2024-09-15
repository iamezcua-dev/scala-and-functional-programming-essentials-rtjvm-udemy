package com.rockthejvm
package lectures.part1.basics

object ValuesVariablesTypes extends App {
  val x: Int = 42
  println(x)
  
  // `val` defines an immutable reference
  // In Scala, compiler can infer types.
  
  val aString: String = "Hello"
  val anotherString: String = "Goodbye"
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aLong: Long = 345678987654345678L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.141592
  
  // variables
  var aVariable: Int = 4
  aVariable = 5 // this is a Side Effect. We should avoid or them.
}
