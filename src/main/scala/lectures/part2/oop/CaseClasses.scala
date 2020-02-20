package lectures.part2.oop

object CaseClasses extends App {
	/*
		equals, hashCode, toString
	 */
	
	case class Person( name: String, age: Int )
	
	// 1. Class parameters are fields
	val jim = new Person( "Jim", 34 )
	println( jim.name )
	
	// 2. Sensible toString
	// println( instance ) == println( instance.toString ) // syntactic sugar
	println( jim )
	
	// 3. equals and hashCode implemented out of the box
	val jim2 = new Person( "Jim", 34 )
	println( jim == jim2 )
	
	// 4. case classes have handy copy method
	val jim3 = jim.copy( age = 45 )
	println( jim3 )
	
	// 5. case classes have companion objects
	val thePerson = Person
	val mary = Person( "Mary", 23 )
	
	// 6. case classes are serializable ( useful for Akka )
	
	// 7. case classes have extractor patterns = case classes can be used in Pattern Matching
	case object UnitedKingdom {
		def name: String = "The UK of GB and NI"
	}
	println( UnitedKingdom.name )
	
}