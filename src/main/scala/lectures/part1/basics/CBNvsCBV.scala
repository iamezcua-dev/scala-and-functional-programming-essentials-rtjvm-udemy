package com.rockthejvm
package lectures.part1.basics

object CBNvsCBV extends App {
  private def calledByValue(x: Long): Unit = {
    println(s"by value: $x")
    println(s"by value: $x")
  }
  
  private def calledByName(x: => Long): Unit = {
    println(s"by name: $x")
    println(s"by name: $x")
  }
  
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
  
  private def infinite(): Int = 1 + infinite()
  private def printFirst(x: Int, y: => Int): Unit = println(x)
  
  //  printFirst(infinite(), 78)
  // - the function call above will cause a StackOverflowError
  
  printFirst(78, infinite())
  // - the function above will run properly since the `y` value is lazily-evaluated and not used.
}
