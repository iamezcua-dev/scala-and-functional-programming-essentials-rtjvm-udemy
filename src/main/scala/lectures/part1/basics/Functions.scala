package com.rockthejvm
package lectures.part1.basics

import com.typesafe.scalalogging.LazyLogging

import scala.annotation.tailrec

object Functions extends App with LazyLogging {
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
  
  /**
   * Exercises
   *
   * 1. A greeting function (name, age) => "Hi, my name is $name, and I am $age years old."
   *
   * 2. Factorial function 1 * 2 * 3 * ... * n
   *
   * 3. A Fibonacci function
   *  - f(1) = 1
   *  - f(2) = 2
   *  - f(n) = f(n-1) + f(n-2)
   *
   * 4.Tests if a number is prime.
   */
  // 1.
  def greeting(name: String, age: Int): String = s"Hi, my name is $name, and I am $age years old."
  
  // 2.
  
  /**
   * Stack-recursive implementation
   */
  def factorial(n: Long): Long = {
    logger.debug(s"* Computing factorial of $n")
    
    @tailrec
    def factorialHelper(current: Long, accum: Long = 1): Long = {
      if (current == 0) {
        logger.debug(s"- The factorial of $n is $accum")
        accum
      }
      else {
        logger.debug(s"- To get factorial($current), we first need factorial(${current - 1})")
        factorialHelper(current - 1, current * accum)
      }
    }
    
    if (n < 0) throw new IllegalArgumentException()
    else factorialHelper(n)
  }
  
  // 3.
  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciHelper(current: Int = 2, prev: Int = 0, accum: Int = 1): Int = {
      if (current > n) accum
      else fibonacciHelper(current + 1, accum, accum + prev)
    }
    
    if (n < 0) throw IllegalArgumentException()
    else if (n == 0) 0
    else if (n == 1) 1
    else fibonacciHelper()
  }
  
  // 4.
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(current: Int): Boolean = {
      if (current < 2) true
      else n % current != 0 && isPrimeUntil(current - 1)
    }
    
    if (n <= 1) false
    else isPrimeUntil(n / 2)
  }
}
