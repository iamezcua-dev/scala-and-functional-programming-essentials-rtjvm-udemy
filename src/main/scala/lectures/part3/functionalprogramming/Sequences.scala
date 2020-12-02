package lectures.part3.functionalprogramming

import scala.util.Random

object Sequences extends App {
  // Seq
  // - very general interface for data structures that:
  //  - have a well defined order
  //  - can be indexed
  val aSequence = Seq( 1, 3, 2, 4 )
  println( aSequence )
  println( aSequence.reverse )
  println( aSequence( 2 ) )
  println( aSequence ++ Seq( 5, 6, 7 ) )
  println( aSequence.sorted )
  
  // Ranges
  val aRange: Seq[ Int ] = 1 until 10
  aRange.foreach( println )
  
  ( 1 to 10 ).foreach( _ => println( "Hello" ) )
  
  // Lists
  // - Extends LinearSeq
  // - immutable
  val aList = List( 1, 2, 3 )
  val prepended = 42 +: aList :+ 89
  println( prepended )
  
  val apples5 = List.fill( 5 )( "apple" )
  println( apples5 )
  println( aList.mkString( "-|-" ) )
  
  // Arrays
  // - Simple Java arrays
  // - mutable
  // - indexing is fast
  val numbers = Array( 1, 2, 3, 4 )
  val threeElements = Array.ofDim[ String ]( 3 )
  threeElements.foreach( println )
  
  // Arrays are mutable
  numbers( 2 ) = 0
  println( numbers.mkString( " " ) )
  
  // Arrays and Seq
  val numbersSeq: Seq[ Int ] = numbers
  println( numbersSeq )
  
  // Vectors
  // - Default implementation for immutable sequences
  // - Effectively constant indexed for r/w operations O(log32(n))
  // - Fast element addition ( append/prepend )
  // - Implemented as a fixed-branched trie ( branch factor 32 )
  // - Good performance for very-large sizes
  val vector: Vector[ Int ] = Vector( 1, 2, 3 )
  println( vector )
  
  // Vectors vs Lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  
  def getWriteTime( collection: Seq[ Int ] ): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated( r.nextInt( maxCapacity ), r.nextInt )
      System.nanoTime() - currentTime
    }
    
    ( times.sum * 1.0 ) / maxRuns
  }
  
  val numbersList = ( 1 to maxCapacity ).toList
  val numbersVector = ( 1 to maxCapacity ).toVector
  
  /*
    List implementation notes:
      - Advantage:    Keeps reference to tail [ O(1) ]
      - Disadvantage: Updating an element in the middle takes long time [ O(n) ]
   */
  println( s"Time took by List implementation: ${getWriteTime( 
  /*
    Vector implementation notes:
      - Advantage:    Depth of the three is small ( branch factor of 32 )
      - Disadvantage: Needs to replace an entire 32-element chunk [ O(log32(n)) ]
   */
  println( s"Time took by Vector implementation: ${getWriteTime( numbersVector )}" )
  
}
