package lectures.part1.basics

import scala.annotation.tailrec

object Functions extends App {
	def aFunction( a: String, b: Int ): String = {
		a + " " + b
	}
	
	println( aFunction( "Hello", 3 ) )
	
	def aParameterlessFunction( ): Int = 42
	
	println( aParameterlessFunction() )
	println( aParameterlessFunction )
	
	def aRepeatedFunction( aString: String, n: Int ): String = {
		if ( n == 1 ) aString
		else aString + aRepeatedFunction( aString, n - 1 )
	}
	
	println( aRepeatedFunction( "hello", 3 ) )
	
	// When you need loops, use recursion
	def aFunctionWithSideEffects( aString: String ): Unit = println( aString )
	
	def aBigFunction( n: Int ): Int = {
		def aSmallerFunction( a: Int, b: Int ): Int = a + b
		
		aSmallerFunction( n, n - 1 )
	}
	
	/*
		* 1. A greeting function ( name, age ) => "Hi, my name is $name and I am $age years old."
	 */
	def greeting( name: String, age: String ) = s"Hi, my name is $name and I am $age years old."
	
	/*
		* 2. Factorial function: 1 * 2 * 3 * ... * n
	 */
	def factorial( n: Int ): Long = {
		def factorialHelper( n: Int, accumulator: Long ): Long = {
			if ( n <= 1 ) accumulator
			else {
				println( s"$accumulator * n" )
				factorialHelper( n - 1, accumulator * n )
			}
		}
		
		factorialHelper( n, 1 )
	}
	
	/*
		* 3. Fibonacci function
		* 	f(1) = 1
		* 	f(2) = 1
		* 	f(3) = f( n - 1 ) + f( n - 2 )
	 */
	def fibonacci( n: Int ): Long = {
		if ( n <= 0 ) 0
		else if ( n <= 2 ) 1
		else fibonacci( n - 1 ) + fibonacci( n - 2 )
	}
	
	/*
		* 4. Function that tests if a number is prime
	 */
	def isPrime( n: Int ): Boolean = {
		if ( n <= 1 ) false
		else {
			// A number is prime if and only if its unique divisors are 1 and itself.
			@tailrec
			def primeHelper( n: Int, tester: Int ): Boolean = {
				if ( tester > n / 2 ) true
				else ( n % tester != 0 ) && primeHelper( n, tester + 1 )
			}
			
			primeHelper( n, 2 )
		}
	}
	
}

