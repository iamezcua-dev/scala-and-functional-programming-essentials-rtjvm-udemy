package lectures.part2.oop

import com.typesafe.scalalogging.LazyLogging
import exercises.{ EmptyList, MyList, MyPredicate, MyTransformer, Node }

object AnonymousClasses extends App with LazyLogging {
	abstract class Animal {
		def eat: Unit
	}
	
	// Anonymous class
	val funnyAnimal: Animal = new Animal {
		override def eat: Unit = println( "ahahahahahaha!" )
	}
	/*
		equivalent with
		
		class AnonymousClasses$$anon$1 extends Animal {
			override def eat: Unit = println( "ahahahahahaha!" )
		}
	 */
	
	println( funnyAnimal.getClass )
	
	class Person( name: String ) {
		def sayHi: Unit = println( s"Hi, my name is $name, how can I help?" )
	}
	
	val jim = new Person( "Jim" ) {
		
		override def sayHi: Unit = println( s"Hi, my name is Jim, how can I be of service?" )
	}
	
	jim.sayHi
	
	/*
		1. Generic trait MyPredicate[-T] with a little method test(T) => boolean
		2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
		3. Implement the following methods on MyList:
			- map( transformer ) => MyList
			- filter( predicate) => MyList
			- flatMap( transformer from A to MyList[B])
			
			class EvenPredicate extends MyPredicate[Int]
			class StringToIntTransformer extends MyTransformer[String, Int]
			
			[1,2,3].map(n * 2) = [2,4,6]
			[1,2,3,4].filter(n % 2) = [2,4]
			[1,2,3].flatMap( n => [n,n+1]) => [1,2,2,3,3,4]
	 */
	
	// Solutions to the exercises
	val list = EmptyList.add( 15 ).add( 12 ).add( 6 ).add( 5 ).add( 4 ).add( 3 ).add( 2 ).add( 1 )
	logger.info( s"Original list: ${list.toString}" )
	
	/*
		map function implementation
	 */
	logger.info( "Map function implementation" )
	val multiplierTransformer = new MyTransformer[ Int, Int ] {
		override def transform( thing: Int ): Int = thing * 2
	}
	val multipliedList = list.map( multiplierTransformer.transform )
	println( multipliedList )
	assert( multipliedList.toString equals "[ 2 (Integer), 4 (Integer), 6 (Integer), 8 (Integer), 10 (Integer), " +
			"12 (Integer), 24 (Integer), 30 (Integer) ]" )
	assert( EmptyList.map( multiplierTransformer.transform ).toString.equals( "[  ]" ) )
	
	/*
		filter function implementation
	 */
	logger.info( "Filter function implementation" )
	val evenPredicate = new MyPredicate[ Int ] {
		override def test( element: Int ): Boolean = element % 2 == 0
	}
	val filteredList = list.filter( evenPredicate )
	println( filteredList )
	assert( filteredList.toString.equals( "[ 2 (Integer), 4 (Integer), 6 (Integer), 12 (Integer) ]" ) )
	assert( EmptyList.filter( evenPredicate.test ).toString.equals( "[  ]" ) )
	
	/*
		flatMap function implementation
	 */
	logger.info( "FlatMap function implementation" )
	
	val elementAndElementPlusOneTransformer = new MyTransformer[ Int, MyList[ Int ] ] {
		override def transform( element: Int ): MyList[ Int ] = new Node( element, new Node( element + 1, EmptyList ) )
	}
	val flatMappedList = list.flatMap( elementAndElementPlusOneTransformer )
	println( flatMappedList )
	assert( flatMappedList.toString equals "[ 1 (Integer), 2 (Integer), 2 (Integer), 3 (Integer), 3 (Integer)," +
			" 4 (Integer), 4 (Integer), 5 (Integer), 5 (Integer), 6 (Integer), 6 (Integer), 7 (Integer), 12 (Integer)," +
			" 13 (Integer), 15 (Integer), 16 (Integer) ]" )
	assert( EmptyList.flatMap( elementAndElementPlusOneTransformer.transform ).toString.equals( "[  ]" ) )
	
}
