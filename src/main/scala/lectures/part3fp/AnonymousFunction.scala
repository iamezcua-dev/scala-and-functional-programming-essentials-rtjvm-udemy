package com.rockthejvm
package lectures.part3fp


object AnonymousFunction extends App {
  /*
    1. MyList: replace all FunctionX calls with lambdas.
    2. Rewrite the "special" adder as an anonymous function.
   */

  // 1. In "com.rockthejvm.part2oop.exercises.MyList"

  // 2. 
  private def specialAdder: Int => Int => Int = (a: Int) => (b: Int) => a * b

  println(specialAdder(8)(7))
}
