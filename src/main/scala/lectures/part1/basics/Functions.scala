package com.rockthejvm
package lectures.part1.basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = a + " " + b
  println(aFunction("Isaac", 35))
  
  def aParameterlessFunction(): Int = 32
  println(aParameterlessFunction())
  //  println(aParameterlessFunction) // this would cause a compile error.
  
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("hello", 3))
  
  // Rule of thumb: When you need loops, use recursion.
  
  def aFunctionWithSideEffects(aString: String): Unit = println(aString)
  
  def aBigFunction(n: Int) = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    
    aSmallerFunction(n, n - 1)
  }
  println(aBigFunction(7))
}
