package lectures.part2.oop

import com.typesafe.scalalogging.LazyLogging

object Objects extends LazyLogging {
	
	def main( args: Array[ String ] ): Unit = {
		// Scala does not have class-level functionality i.e. `static` modifier
		object Person { // When we define the `Person` object, we define both, a new type and its only instance
			// Class-level functionality
			val N_EYES = 2
			
			def canFly: Boolean = false
			
			def apply( mother: Person, father: Person ): Person = new Person( "Bobbie" )
		}
		
		class Person( val name: String ) {
			// Instance-level functionality
		}
		
		// This pattern of writing classes and objects with the same name is called COMPANIONS
		
		logger.info( "How many eyes does a person have?" )
		println( Person.N_EYES )
		logger.info( "Could a person fly?" )
		println( Person.canFly )
		
		// Scala objects represents a SINGLETON INSTANCE
		val maryClassInstance = new Person( "Mary" )
		val johnClassInstance = new Person( "John" )
		logger.info( "Do mary and john variables point to the same instance?" )
		println( maryClassInstance != johnClassInstance )
		assert( maryClassInstance != johnClassInstance )
		
		val maryObject = Person
		val johnObject = Person
		logger.info( "Do Mary and John variables point to the same instance?" )
		println( maryObject == johnObject )
		assert( maryObject == johnObject )
		
		val bobbie = Person( maryClassInstance, johnClassInstance )
		logger.info( "The new child of Mary and Jane" )
		assert( bobbie.name equals "Bobbie" )
	}
	
}
