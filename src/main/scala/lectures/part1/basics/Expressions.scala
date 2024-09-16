package com.rockthejvm
package lectures.part1.basics

object Expressions extends App {
  val x = 1 + 2 // Expression
  println(x)
  
  println(2 + 2 * 4)
  // + - * / & | ^ << >> >>> (right shift with zero extension)
  
  println(1 == x)
  // == != > >= < <=
  
  println(!(1 == x))
  // ! && ||
  
  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ... although these are side effects!
  println(aVariable)
  
  // Instructions (DO something) vs Expressions (evaluates into a value)
  
  // IF expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 // `if` expression
  println(aConditionedValue)
  println(if (aCondition) 5 else 3)
  println(1 + 3)
  
  /**
   * Never do the following!
   */
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
  
  // Everything in Scala is an Expression!
  
  val aWeirdValue = (aVariable = 3) // Unit === void (in other languages)
  println(aWeirdValue)
  
  // side effects: println(), while, variable reassigning
  
  // Code Blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    
    if (z > 2) "hello" else "goodbye"
  }
}
