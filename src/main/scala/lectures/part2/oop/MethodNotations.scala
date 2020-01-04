package lectures.part2.oop

import com.typesafe.scalalogging.LazyLogging
import scala.language.postfixOps

object MethodNotations extends App with LazyLogging {
	
	class Person( val name: String, val favoriteMovie: String, val age: Int = 0 ) {
		def likes( movie: String ): Boolean = movie == favoriteMovie
		
		def learnsScala: String = learns( "Scala" )
		
		def learns( language: String ) = s"$name learns $language"
		
		def +( person: Person ): String = s"${this.name} is hanging out with ${person.name}"
		
		def +( aka: String ): Person = new Person( s"$name ($aka)", favoriteMovie )
		
		def unary_! : String = s"$name, what the heck?!"
		
		def unary_+ : Person = new Person( name, favoriteMovie, age + 1 )
		
		def isAlive: Boolean = true
		
		def apply( ): String = s"Hi, my name is $name and I like $favoriteMovie"
		
		def apply( times: Int ): String = s"$name watched $favoriteMovie $times times"
	}
	
	val mary = new Person( "Mary", "Inception" )
	println( mary.likes( "Inception" ) )
	println( mary likes "Inception" )
	// Infix notation = operator notation (syntactic sugar)
	
	// "operators" in Scala
	val tom = new Person( "Tom", "Fight Club" )
	println( mary + tom )
	println( mary.+( tom ) )
	
	println( 1 + 2 )
	println( 1.+( 2 ) )
	
	// All OPERATORS ARE METHODS
	// Akka actors have operators like: ! ?
	
	// Prefix notation (another syntactic sugar)
	val x = -1 // equivalent with 1.unary_-
	val y = 1.unary_-
	// unary_ prefix only works with - + ~ !
	println( !mary )
	println( mary.unary_! )
	
	// Postfix notation ( yet another syntactic sugar)
	println( mary.isAlive )
	println( mary isAlive )
	
	// `apply` method
	println( mary.apply() )
	println( mary() ) // equivalent
	
	/*
	 * Exercises
	*/
	logger.info( "Exercises related to the chapter" )
	
	/*
		* 1.  Overload the + operator
		*     mary + "the rockstar" => new Person "Mary (the rockstar)"
		*/
	val maryland = new Person( "Mary", "Vanilla Sky" )
	val anotherMary = maryland + "the rockstar"
	println( s"Hello, this is ${anotherMary.name}" )
	assert( anotherMary.name equals "Mary (the rockstar)" )
	
	/*
		* 2.  Add an age to the Person class
		*     Add an unary + operator => new Person with the age + 1
		*     +mary => mary with the age incremented
		*/
	val yetAnotherMary = new Person( "Mary", "Eternal Sunshine of a Spotless Mind", 45 )
	val olderMary = +yetAnotherMary
	println( s"Mary will turn ${olderMary.age} years" )
	assert( yetAnotherMary.name equals "Mary" )
	assert( yetAnotherMary.favoriteMovie equals "Eternal Sunshine of a Spotless Mind" )
	assert( yetAnotherMary.age equals 45 )
	assert( olderMary.age equals 46 )
	
	/*
		* 3.  Add a "learns" method in the Person class => "Mary learns Scala"
		*     Add a learnsScala method, call learns method with "Scala".
		*     Use it in a postfix notation.
		*/
	println( mary learns "Scala" )
	println( mary learnsScala )
	assert( mary learns "Scala" equals "Mary learns Scala" )
	assert( mary.learnsScala equals "Mary learns Scala" )
	
	/*
		* 4.  Overload the apply method
		* 		mary.apply(2) => "Mary watched Inception 2 times"
	 */
	println( mary( 2 ) )
	assert( mary( 2 ) equals "Mary watched Inception 2 times" )
	
}
