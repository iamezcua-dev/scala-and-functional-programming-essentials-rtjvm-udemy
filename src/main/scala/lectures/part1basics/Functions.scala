package com.rockthejvm
package lectures.part1basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())
  println(aParameterlessFunction())

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + "-" + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 5))

  // When you need loops, use recursion.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  println(aBigFunction(5))

  /*
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
    2. Factorial function 1 * 2 * 3 * ... * n
    3. A Fibonacci function:
        f(1) = 1
        f(2) = 1
        f(n) = f(n-1) + f(n-2)
    4. Tests if a number ir prime.
  */
  def greeting(name: String, age: Int): Unit = {
    println(s"Hi, my name is $name and I am $age years old.")
  }

  greeting("Isaac", 34)

  private def factorial(n: Int): BigInt = {
    def factorialHelper(n: Int, accumulator: BigInt): BigInt = {
      if (n <= 1) accumulator
      else factorialHelper(n - 1, accumulator * n)
    }

    factorialHelper(n, 1)
  }

  private val number = 8
  private val result: BigInt = factorial(number)
  println(s"Factorial of $number is $result")
  assert(result == BigInt(40320))

}