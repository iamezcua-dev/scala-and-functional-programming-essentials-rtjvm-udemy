package lectures.part1.basics

object CBNvsCBV extends App {
	def calledByValue( x: Long ): Unit = {
		println( s"By value $x" )
		println( s"By value $x" )
	}
	
	def calledByName( x: => Long ): Unit = {
		println( s"By name $x" )
		println( s"By name $x" )
		println( s"By name $x" )
	}
	
	calledByValue( System.nanoTime() )
	calledByName( System.nanoTime() )
	
	def infinite: Int = 1 + infinite
	// By calling it by name, the "y" variable is evaluated only when it is used.
	def printFirst( x: Int, y: => Int ) = print( x )
	
	printFirst( 34, infinite )
}
