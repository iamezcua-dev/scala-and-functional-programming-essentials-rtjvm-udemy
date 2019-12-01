package lectures.part1.basics

import scala.annotation.tailrec

object Recursion extends App {
	// When you need loops, use Tail Recursion.
	
	/*
		* 1. Function that concatenate a string n times.
	*/
	def concatenateString( aString: String, times: Int ): String = {
		@tailrec
		def concatenator( remainingTimes: Integer, result: String ): String = {
			if ( remainingTimes <= 0 ) result
			else concatenator( remainingTimes - 1, result + aString )
		}
		
		concatenator( times, "" )
	}
	
	/*
		* 2. IsPrime function tail recursive.
	*/
	def isPrime( n: Int ): Boolean = {
		@tailrec
		def isPrimeUntil( currentNumber: Int ): Boolean = {
			if ( currentNumber <= 1 ) true
			else
				n % currentNumber != 0 && isPrimeUntil( currentNumber - 1 )
		}
		
		!( n <= 1 ) && isPrimeUntil( n / 2 )
	}
	
	/*
		* 3. Fibonacci function, tail recursive.
	 */
	def fibonacci( n: Int ): Int = {
		@tailrec
		def fibonacciHelper( n: Int, current: Int, next: Int ): Int = {
			if ( n <= 0 ) current
			else {
				fibonacciHelper( n - 1, next, current + next )
			}
		}
		
		fibonacciHelper( n, 0, 1 )
	}
	
}
