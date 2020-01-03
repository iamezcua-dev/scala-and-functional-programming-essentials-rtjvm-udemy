package lectures.part2.oop.inheritancecontinued

object AbstractDataTypes extends App {
	
	// Abstract
	abstract class Animal {
		val creatureType: String = "wild"
		def eat: Unit
	}
	
	class Dog extends Animal {
		override val creatureType: String = "Canine"
		override def eat: Unit = println( "Crunch, crunch!" )
	}
	
	// traits
	trait Carnivore {
		def eat( animal: Animal ): Unit
		val preferredMeal: String = "fresh meat"
	}
	
	trait ColdBlooded
	
	class Crocodile extends Animal with Carnivore with ColdBlooded {
		override val creatureType: String = "croc"
		def eat: Unit = "Nom, Nom!"
		def eat( animal: Animal ): Unit = println( s"I'm a croc and I'm eating ${animal.creatureType}" )
	}
	
	val dog = new Dog
	val croc = new Crocodile
	croc.eat( dog )
	
	// traits v.s. abstract classes
	//	1) Both can have both, abstract and non-abstract members.
	//	2) Traits do not have constructor parameters.
	//	3) A class cannot inherit or extend multiple classes nor abstract ones.
	//	4) A class can inherit multiple traits using the `with` keyword.
	//	5) We choose a trait over an abstract class if it describes a BEHAVIOR.
}
