package lectures.part3.functionalprogramming

object MapFlatmapFilterFor extends App {
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
  
  println( chars.flatMap( aChar => numbers.map( aNumber => s"$aChar$aNumber" ) ) )
}
