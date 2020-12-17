package lectures.part3.functionalprogramming

object TuplesAndMaps extends App {
  // Tuples = finite ordered "lists"
  val aTuple = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  
  println( aTuple._1 ) // 2
  println( aTuple.copy( _2 = "Goodbye Java" ) )
  println( aTuple.swap ) // ("Hello, Scala", 2)
  
  // Maps - keys -> values
  val aMap: Map[ String, Int ] = Map()
  
  val phoneBook = Map( ("Jim", 555), ("Daniel", 789) ).withDefaultValue( -1 )
  val phoneBook2 = Map( "Jim" -> 555, "Daniel" -> 789 ).withDefaultValue( -1 )
  
  println( phoneBook )
  println( phoneBook2 )
  
  // Map Operations
  println( phoneBook.contains( "Jim" ) )
  println( phoneBook( "Jim" ) )
  println( phoneBook( "Mary" ) )
  
  // Add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println( newPhoneBook )
  
  // Functionals on Mapp ( map, flatmap, filter )
  println( phoneBook.map( pair => pair._1.toLowerCase -> pair._2 ) )
  
  // filterKeys
  println( phoneBook.filterKeys( x => x.startsWith( "J" ) ) )
  
  // mapValues
  println( phoneBook.view.mapValues( number => number * 10 ).toMap )
  println( phoneBook.view.mapValues( number => s"0234-$number" ).toMap )
  
  // conversions to other collections
  println( phoneBook.toList )
  println( List( ("Daniel", 555) ).toMap )
  val names = List( "Bob", "James", "Angela", "Mary", "Daniel", "Jim" )
  println( names.groupBy( name => name.charAt( 0 ) ) )
  
}
