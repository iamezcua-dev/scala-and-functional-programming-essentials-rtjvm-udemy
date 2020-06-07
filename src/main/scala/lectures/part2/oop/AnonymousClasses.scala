package lectures.part2.oop

import com.typesafe.scalalogging.LazyLogging
import exercises.{ EmptyList, MyList, Node }

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

	// Solutions to the exercises are in the MyList class

}
