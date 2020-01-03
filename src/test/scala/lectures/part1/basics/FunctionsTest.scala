package lectures.part1.basics

import org.scalatest.{ DiagrammedAssertions, FunSuite }

class FunctionsTest extends FunSuite with DiagrammedAssertions {
	/**
	 * Greeting function tests
	 */
	test( "Greeting function result value should be \"Hi, my name is Isaac and I am 31 years old.\", when " +
			"Isaac and 31 are the the input parameters." ) {
		assert( Functions.greeting( "Isaac", "31" ).equals( "Hi, my name is Isaac and I am 31 years old." ) )
	}
	
	test( "Greeting function result value should be \"Hi, my name is Eréndira and I am 29 years old.\", when " +
			"Eréndira and 29 are the the input parameters." ) {
		assert( Functions.greeting( "Eréndira", "29" ).equals( "Hi, my name is Eréndira and I am 29 years old." ) )
	}
	
	/**
	 * Factorial function tests
	 */
	test( "The factorial of 3 is 6." ) {
		assert( Functions.factorial( 3 ) == 6 )
	}
	
	test( "The factorial of 7 is 5040." ) {
		assert( Functions.factorial( 7 ) == 5040 )
	}
	
	test( "The factorial of 0 is 1." ) {
		assert( Functions.factorial( 0 ) == 1 )
	}
	
	test( "The factorial of -20 is 1." ) {
		assert( Functions.factorial( -20 ) == 1 )
	}
	
	/**
	 * Fibonacci function test
	 */
	test( "Fibonacci of 0 is 0." ) {
		assert( Functions.fibonacci( 0 ) == 0 )
	}
	
	test( "Fibonacci of 1 is 1." ) {
		assert( Functions.fibonacci( 1 ) == 1 )
	}
	
	test( "Fibonacci of 2 is 1." ) {
		assert( Functions.fibonacci( 2 ) == 1 )
	}
	
	test( "Fibonacci of 3 is 2." ) {
		assert( Functions.fibonacci( 3 ) == 2 )
	}
	
	test( "Fibonacci of 4 is 3." ) {
		assert( Functions.fibonacci( 4 ) == 3 )
	}
	
	test( "Fibonacci of 5 is 5." ) {
		assert( Functions.fibonacci( 5 ) == 5 )
	}
	
	test( "Fibonacci of 6 is 8." ) {
		assert( Functions.fibonacci( 6 ) == 8 )
	}
	
	/**
	 * Is prime?
	 */
	
	test( "The number 0 not prime" ) {
		assert( !Functions.isPrime( 0 ) )
	}
	
	test( "The number 1 not prime" ) {
		assert( !Functions.isPrime( 1 ) )
	}
	
	test( "The number 2 is prime" ) {
		assert( Functions.isPrime( 2 ) )
	}
	
	test( "The number 3 is prime" ) {
		assert( Functions.isPrime( 3 ) )
	}
	
	test( "The number 4 is not prime" ) {
		assert( !Functions.isPrime( 4 ) )
	}
	
	test( "The number 5 is prime" ) {
		assert( Functions.isPrime( 5 ) )
	}
	
	test( "The number 6 is not prime" ) {
		assert( !Functions.isPrime( 6 ) )
	}
	
	test( "The number 7 is prime" ) {
		assert( Functions.isPrime( 7 ) )
	}
	
	test( "The number 8 is not prime" ) {
		assert( !Functions.isPrime( 8 ) )
	}
	
	test( "The number 9 is not prime" ) {
		assert( !Functions.isPrime( 9 ) )
	}
	
	test( "The number 10 is not prime" ) {
		assert( !Functions.isPrime( 10 ) )
	}
	
	test( "The number 11 is prime" ) {
		assert( Functions.isPrime( 11 ) )
	}
	
	test( "The number 191 is prime" ) {
		assert( Functions.isPrime( 191 ) )
	}
	
	test( "The number 192 is not prime" ) {
		assert( !Functions.isPrime( 192 ) )
	}
	
	test( "The number 193 is prime" ) {
		assert( Functions.isPrime( 193 ) )
	}
	
	test( "The number 345 is not prime" ) {
		assert( !Functions.isPrime( 345 ) )
	}
	
	test( "The number 756 is not prime" ) {
		assert( !Functions.isPrime( 756 ) )
	}
	
	test( "The number 269 is prime" ) {
		assert( Functions.isPrime( 269 ) )
	}
	
	test( "The number 147 is not prime" ) {
		assert( !Functions.isPrime( 147 ) )
	}
	
}
