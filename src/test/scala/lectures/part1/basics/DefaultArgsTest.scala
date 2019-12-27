package lectures.part1.basics

import org.scalatest.FunSuite

class DefaultArgsTest extends FunSuite {
	
	/**
	 * Factorial function tests
	 */
	test( "The factorial of 3 is 6." ) {
		assert( DefaultArgs.trFactorial( 3 ) == 6 )
	}
	
	test( "The factorial of 7 is 5040." ) {
		assert( DefaultArgs.trFactorial( 7 ) == 5040 )
	}
	
	test( "The factorial of 0 is 1." ) {
		assert( DefaultArgs.trFactorial( 0 ) == 1 )
	}
	
	test( "The factorial of -20 is 1." ) {
		assert( DefaultArgs.trFactorial( -20 ) == 1 )
	}
	
}
