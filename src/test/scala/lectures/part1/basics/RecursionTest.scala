package lectures.part1.basics

import org.scalatest.{ DiagrammedAssertions, FunSuite }

class RecursionTest extends FunSuite with DiagrammedAssertions {
	
	/**
	 *
	 */
	test( "Concatenator should concatenate Eren three times when 'Eréndira' and 3 are the parameters." ) {
		assert( Recursion.concatenateString( "Eréndira ", 3 )
				.equals( "Eréndira Eréndira Eréndira " ) )
	}
	
	/**
	 * Fibonacci function test
	 */
	test( "Fibonacci of 0 is 0." ) {
		assert( Recursion.fibonacci( 0 ) == 0 )
	}
	
	test( "Fibonacci of 1 is 1." ) {
		assert( Recursion.fibonacci( 1 ) == 1 )
	}
	
	test( "Fibonacci of 2 is 1." ) {
		assert( Recursion.fibonacci( 2 ) == 1 )
	}
	
	test( "Fibonacci of 3 is 2." ) {
		assert( Recursion.fibonacci( 3 ) == 2 )
	}
	
	test( "Fibonacci of 4 is 3." ) {
		assert( Recursion.fibonacci( 4 ) == 3 )
	}
	
	test( "Fibonacci of 5 is 5." ) {
		assert( Recursion.fibonacci( 5 ) == 5 )
	}
	
	test( "Fibonacci of 6 is 8." ) {
		assert( Recursion.fibonacci( 6 ) == 8 )
	}
	
	/**
	 * Is prime?
	 */
	
	test( "The number 0 not prime" ) {
		assert( !Recursion.isPrime( 0 ) )
	}
	
	test( "The number 1 not prime" ) {
		assert( !Recursion.isPrime( 1 ) )
	}
	
	test( "The number 2 is prime" ) {
		assert( Recursion.isPrime( 2 ) )
	}
	
	test( "The number 3 is prime" ) {
		assert( Recursion.isPrime( 3 ) )
	}
	
	test( "The number 4 is not prime" ) {
		assert( !Recursion.isPrime( 4 ) )
	}
	
	test( "The number 5 is prime" ) {
		assert( Recursion.isPrime( 5 ) )
	}
	
	test( "The number 6 is not prime" ) {
		assert( !Recursion.isPrime( 6 ) )
	}
	
	test( "The number 7 is prime" ) {
		assert( Recursion.isPrime( 7 ) )
	}
	
	test( "The number 8 is not prime" ) {
		assert( !Recursion.isPrime( 8 ) )
	}
	
	test( "The number 9 is not prime" ) {
		assert( !Recursion.isPrime( 9 ) )
	}
	
	test( "The number 10 is not prime" ) {
		assert( !Recursion.isPrime( 10 ) )
	}
	
	test( "The number 11 is prime" ) {
		assert( Recursion.isPrime( 11 ) )
	}
	
	test( "The number 191 is prime" ) {
		assert( Recursion.isPrime( 191 ) )
	}
	
	test( "The number 192 is not prime" ) {
		assert( !Recursion.isPrime( 192 ) )
	}
	
	test( "The number 193 is prime" ) {
		assert( Recursion.isPrime( 193 ) )
	}
	
	test( "The number 345 is not prime" ) {
		assert( !Recursion.isPrime( 345 ) )
	}
	
	test( "The number 756 is not prime" ) {
		assert( !Recursion.isPrime( 756 ) )
	}
	
	test( "The number 269 is prime" ) {
		assert( Recursion.isPrime( 269 ) )
	}
	
	test( "The number 147 is not prime" ) {
		assert( !Recursion.isPrime( 147 ) )
	}
	
}
