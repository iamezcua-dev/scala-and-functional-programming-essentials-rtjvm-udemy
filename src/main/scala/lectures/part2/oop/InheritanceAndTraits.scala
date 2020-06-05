package lectures.part2.oop

object InheritanceAndTraits extends App {
	
	// single class inheritance
	sealed class Animal {
		val creatureType = "wild"
		def eat: Unit = println( "Nom! nom!" )
	}
	
	class Cat extends Animal {
		def crunch = {
			eat
			println( "Crunch! crunch! meow!" )
		}
	}
	
	val cat = new Cat
	cat.crunch
	
	// constructor
	class Person( name: String, age: Int ) {
		def this( name: String ) = this( name, 0 )
	}
	
	class Adult( name: String, age: Int, idCard: String ) extends Person( name )
	
	// overriding
	// You can override a value in at least two ways:
	// 	a)	Overriding the value directly from the constructor.
	// 	b)	Overriding the value inside the class using the class parameter as the implementation.
	
	//	a)
	//	class Dog( override val creatureType: String ) extends Animal {
	//		//		override val creatureType: String = "domestic"
	//		override def eat: Unit = println( "Crunch! crunch! roof! roof!" )
	//	}
	
	//	b)
	class Dog( override val creatureType: String ) extends Animal {
		override def eat: Unit = {
			super.eat
			println( "Crunch! crunch! roof! roof!" )
		}
	}
	
	val dog = new Dog( "K9" )
	dog.eat
	println( dog.creatureType )
	
	// type substitution
	val unknownAnimal: Animal = new Dog( "K9" )
	unknownAnimal.eat
	
	// Overriding is not Overloading
	
	// Super
	
	// Preventing override
	// 	1)	Use `final` on member.
	//	2)	Use `final` on the entire class.
	//	3)	Use `sealed` to allow  a class to be extended in the sale file where it is declared.
}
