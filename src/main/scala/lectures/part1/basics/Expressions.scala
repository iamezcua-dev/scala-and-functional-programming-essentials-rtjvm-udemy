package com.rockthejvm
package lectures.part1.basics

import com.typesafe.scalalogging.LazyLogging

object Expressions extends App with LazyLogging {
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
  
  /**
   * Exercises
   */
  // 1. Difference between "hello world" vs println("hello world")
  logger.info("Answer: The \"hello world\" evaluates to String, while println(\"hello world\") evaluates to Unit and " +
    "also prints such string as Side Effect")
  
  // 2. What are the values of the following expressions:
  val someValue = {
    2 < 3
  }
  logger.info("The value of the first expression is a Boolean (true).")
  println(someValue)
  
  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }
  logger.info("The value of the second expression is an Int (42).")
  println(someOtherValue)
}
