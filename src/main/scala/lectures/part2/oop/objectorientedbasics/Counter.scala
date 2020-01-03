package lectures.part2.oop.objectorientedbasics

import com.typesafe.scalalogging.LazyLogging

import scala.annotation.tailrec

class Counter( val count: Int = 0 ) extends LazyLogging {
	def printCurrentCount = println( count )
	
	def increment: Counter = {
		logger.trace( "Incrementing ..." )
		new Counter( count + 1 )
	}
	
	def decrement: Counter = {
		logger.trace( "Decrementing ..." )
		new Counter( count - 1 )
	}
	
	@tailrec
	final def increment( times: Int ): Counter = {
		if ( times <= 0 ) this
		else increment.increment( times - 1 )
	}
	
	@tailrec
	final def decrement( times: Int ): Counter = {
		if ( times <= 0 ) this
		else decrement.decrement( times - 1 )
	}
}
