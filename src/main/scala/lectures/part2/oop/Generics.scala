package lectures.part2.oop

object Generics extends App {
	
	class MyList[ A ] {
		// use the type A
	}
	
	class MyMap[ Key, Value ]
	
	val listOfIntegers = new MyList[ Int ]
	val listOfStrings = new MyList[ String ]
	
	object MyList {
		def empty[ A ]: MyList[ A ] = ???
	}
	
	val emptyListOfIntegers = MyList.empty[ Int ]
	
	// variance problem
	class Animal
	class Cat extends Animal
	class Dog extends Animal
	
	// If Cat extends Animal, does a List[Cat] also extends a List[Animal]?
	// Answers:
	
	// 1. Yes ( Covariance )
	// Covariance: a generic List of type A can accept either, elements of type A and their subclasses.
	class CovariantList[ +A ]
	val animal: Animal = new Cat
	val animalList: CovariantList[ Animal ] = new CovariantList[ Cat ]
	
	// 2. No ( Invariance )
	class InvariantList[ A ]
	val invariantAnimalList: InvariantList[ Animal ] = new InvariantList[ Animal ]
	
	// 3. Hell, no! ( Contravariance )
	class ContravariantList[ -A ]
	val contravariantList: ContravariantList[ Cat ] = new ContravariantList[ Animal ]
	class Trainer[ -A ]
	val trainer: Trainer[ Cat ] = new Trainer[ Animal ] // An `Animal` `Trainer is better, because he can train any `Animal` including `Cats`
	
	// Bounded types
	class Cage[ A <: Animal ]( animal: A ) // This class only accepts subclasses of the `Animal` class
	val cage = new Cage( new Dog )
	// Generic type needs proper bounded type
	class Car extends Animal
	val car = new Cage( new Car )
	
}
