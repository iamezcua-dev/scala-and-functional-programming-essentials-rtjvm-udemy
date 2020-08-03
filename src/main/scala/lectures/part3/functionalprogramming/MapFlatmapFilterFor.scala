package lectures.part3.functionalprogramming

import com.typesafe.scalalogging.LazyLogging
import exercises.{ EmptyList, Just, Node }

object MapFlatmapFilterFor extends App with LazyLogging {
  val list = List( 1, 2, 3 )
  println( list.head )
  println( list.tail )
  
  // map
  println( list.map( _ + 1 ) )
  println( list.map( _ + " is a number" ) )
  
  // filter
  println( list.filter( _ % 2 == 0 ) )
  println( list.filter( _ > 1 ) )
  
  // flatMap
  val toPair = ( x: Int ) => List( x, x + 1 )
  println( list.flatMap( toPair ) )
  
  /*
    Exercise
    
    1.  Print all combinations between two or more lists.
   */
  val numbers = List( 1, 2, 3, 4 )
  val chars = List( 'a', 'b', 'c', 'd' )
  val colors = List( "Black", "White", "Magenta" )
  
  println( chars.flatMap( aChar => numbers.flatMap( aNumber => colors.map( color => s"$aChar$aNumber - $color" ) ) ) )
  
  // foreach
  list.foreach( println )
  
  // for-comprehensions
  val forCombinations = for {
    n <- numbers; c <- chars; color <- colors
  } yield s"$c$n - $color"
  
  println( forCombinations )
  
  for {
    n <- numbers
  } println( n )
  
  // syntax overload
  list.map { x =>
    x * 2
  }
  
  /*
    Exercises
    
    1.  MyList supports for comprehensions?
    2.  Implement a small collection of at most ONE element - Maybe[+T]
          - Implement map, flatMap, filter functions
   */
  logger.info( "1) MyList supports for comprehensions?" )
  val numberList = Node( 1, Node( 2, Node( 3, Node( 4, Node( 5, EmptyList ) ) ) ) )
  for {
    element <- numberList
  } yield println( element )
  
  println( "Answer: Yes, it does! I'm surprised!" )
  println( "The reason behind is that any collection that properly implements map, filter and flatMap can qualify " +
      "for being for-comprehension compatible" )
  
  logger.info( "2) Implementation of a small collection of maximum ONE element." )
  val collection = Just( 1 )
  println( collection )
  println( s"Map ( multiply by 5 ): ${collection.map( _ * 5 )}" )
  println( s"Filter ( greater that 0 ): ${collection.filter( _ > 0 )}" )
  println( s"FlatMap ( transform to the boolean 'is even?' ): ${collection.flatMap( x => Just( x % 2 == 0 ) )}" )
}
