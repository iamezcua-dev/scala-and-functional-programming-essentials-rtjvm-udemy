package com.rockthejvm
package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // Expression
  println(x)

  println(2 + 3 * 4)
  // + - * / & ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  // ++ != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ...... side effects
  println(aVariable)

  // Instructions (DO) vs Expression (VALUE)

  // IF expression
  private val aCondition = true
  private val aConditionedValue = if (aCondition) 5 else 3
  println(aConditionedValue)
  println(if (aCondition) 5 else 3)
  println(1 + 3)

  // Loops (this only evaluate side effects over an over again)
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
  // NEVER WRITE THE ABOVE CODE AGAIN!

  // Everything in Scala is an Expression!

  val aWeirdValue: Unit = (aVariable = 3) // Unit === void (in java)
  println(aWeirdValue)

  /*
    Side effects examples:
      - printing something to the console
      - while loops
      - reassigning to variables
   */

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // 1. Difference between "hello world" vs println("hello world")?
  // Answer: The first one is a value that represents an String. The second one is a function call that returns
  //  Unit and prints a String + a new line as side effect.
  // 2.

  private val someValue = {
    2 < 3
  }
  println(someValue)
  // Answer: The value of someValue would be true, since it is a comparison and the last statement in the code
  //  block.

  private val someOtherValue = {
    if (someValue) 239 else 986
    42
  }
  println(someOtherValue)
  // Answer: The value of someOtherValue would be 42, since it is a comparison and the last statement in the code
  //  block.
}
