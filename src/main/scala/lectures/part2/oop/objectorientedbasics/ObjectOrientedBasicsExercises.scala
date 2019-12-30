package lectures.part2.oop.objectorientedbasics

object ObjectOrientedBasicsExercises extends App {
	/*
	 * Novel and Writer
	 */
	val author = new Writer( "José", "Saramago", 1922 )
	val imposterAuthor = new Writer( "José", "Saramago", 1922 )
	val novel = new Novel( "Ensayo sobre la ceguera", 1988, author )
	
	println( novel.authorAge )
	println( novel.isWrittenBy( author ) )
	
	/*
	 * Counter
	 *
	 *  - receives an integer value.
	 *  - method current count.
	 *  - method to increment/decrement.
	 *  - overload increment/decrement to receive and amount.
	 */
	val counter = new Counter
	counter.increment.printCurrentCount
	counter.increment.increment.increment.printCurrentCount
	counter.increment( 10 ).printCurrentCount
	counter.increment( 156550 ).printCurrentCount
}
