package lectures.part1.basics

import com.typesafe.scalalogging.LazyLogging

object DefaultArgs extends LazyLogging with App {
	
	@scala.annotation.tailrec
	def trFactorial( currentNumber: Int, accumulator: BigInt = 1 ): BigInt = {
		if ( currentNumber <= 1 ) {
			logger.debug( s"Final accumulator value = $accumulator ..." )
			accumulator
		} else {
			logger.trace( s"Calculating $currentNumber * $accumulator ..." )
			trFactorial( currentNumber - 1, currentNumber * accumulator )
		}
	}
	
	val fact10 = trFactorial( 10 )
	println( fact10 )
	
	def savePicture( format: String = "jpg", width: Int = 1920, heigth: Int = 1080 ): Unit = println( "Saving picture ..." )
	
	savePicture( width = 800 )
	
	/*
		1. Pass every leading parameter
		2. Name the arguments
	 */
	savePicture( heigth = 600, width = 800, format = "bmp" )
}
