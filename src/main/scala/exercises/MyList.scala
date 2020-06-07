package exercises

import com.typesafe.scalalogging.LazyLogging
import lectures.part2.oop.AnonymousClasses.logger

// Covariant class
abstract class MyList[ +A ] {
	/*
		head = first element of  the list
		tail = remaider of the list
		isEmpty = is the list empty?
		add(int) => new list with thisx element added
		toString => a string representation of the list
	 */
	def head: A
	def tail: MyList[ A ]
	def isEmpty: Boolean
	def add[ B >: A ]( number: B ): MyList[ B ]
	def listContents: String
	override def toString: String = s"[ $listContents ]"
	def map[ B ]( transformer: A => B ): MyList[ B ]
	def filter( predicate: A => Boolean ): MyList[ A ]
	def flatMap[ B ]( transformer: A => MyList[ B ] ): MyList[ B ]
	def ++[ B >: A ]( list: MyList[ B ] ): MyList[ B ]
}

case object EmptyList extends MyList[ Nothing ] {
	def head: Nothing = throw new NoSuchElementException
	def tail: Nothing = throw new NoSuchElementException
	def isEmpty: Boolean = true
	def add[ B >: Nothing ]( element: B ): MyList[ B ] = Node( element, EmptyList )
	def listContents: String = ""
	override def map[ B ]( transformer: Nothing => B ): MyList[ B ] = EmptyList
	override def filter( predicate: Nothing => Boolean ): MyList[ Nothing ] = EmptyList
	override def flatMap[ B ]( transformer: Nothing => MyList[ B ] ): MyList[ B ] = EmptyList
	override def ++[ B >: Nothing ]( list: MyList[ B ] ): MyList[ B ] = list
}

case class Node[ +A ]( element: A, listTail: MyList[ A ] ) extends MyList[ A ] {
	def head: A = element
	def tail: MyList[ A ] = this.listTail
	def isEmpty: Boolean = false
	// B supertype of Nothing
	def add[ B >: A ]( element: B ): MyList[ B ] = Node( element, this )
	def listContents: String = {
		if ( listTail.isEmpty ) s"$element (${element.getClass.getSimpleName})"
		else s"$element (${element.getClass.getSimpleName}), ${listTail.listContents}"
	}

	override def map[ B ]( transformer: A => B ): MyList[ B ] =
		Node( transformer( element ), listTail.map( transformer ) )

	/*
		[1, 2, 3, 4].filter( n % 2 == 0 )
		= predicate( 1 % 2 == 0 ) is false. Then, continuing with: [2, 3, 4].filter( n % 2 == 0 )
		= predicate( 2 % 2 == 0 ) is true. Then, adding it to the list and continuing with: [2], [3, 4].filter( n % 2 == 0 )
		= predicate( 3 % 2 == 0 ) is false. Then, continuing with: [2], [4].filter( n % 2 == 0 )
		= predicate( 4 % 2 == 0 ) is true. Then, adding it to the list and continuing with: [2], [3].filter( n % 2 == 0 )

	 */
	override def filter( predicate: A => Boolean ): MyList[ A ] = {
		if ( predicate( element ) ) Node( element, tail.filter( predicate ) )
		else tail.filter( predicate )
	}

	/*
		[ 1, 2, 3].flatMap(  n => [ n, n+1 ] )
		= [1, 2] ++ [2, 3].flatMap( transformer )
		= [1, 2, 2, 3] ++ [3].flatMap( transformer)
		= [1, 2, 2, 3, 3, 4] ++ EmptyList.flatMap( transformer )
		= [1, 2, 2, 3, 3, 4]
	 */
	override def flatMap[ B ]( transformer: A => MyList[ B ] ): MyList[ B ] =
		transformer( element ) ++ listTail.flatMap( transformer )

	/*
			list ++ new Node( 27, new Node( 30, EmptyList ) ) )

			= [ 1, 2, 3, 4, 5, 6, 12, 15, 27, 30 ] ++ [ 27, 30 ]
			= [ 1] ,  [ 2, 3, 4, 5, 6, 12, 15, 27, 30 ] ++ [ 27, 30 ]
			= [ 1, 2] ,  [ 3, 4, 5, 6, 12, 15, 27, 30 ] ++ [ 27, 30 ]
			= [ 1, 2, 3] ,  [ 4, 5, 6, 12, 15, 27, 30 ] ++ [ 27, 30 ]
        				. . .
	 		= [ 1, 2, 3, 4, 5, 6, 12, 15, 27 ] ,  [ 30 ] ++ [ 27, 30 ]
	 		= [ 1, 2, 3, 4, 5, 6, 12, 15, 27, 30, 27, 30 ]
	 */
	override def ++[ B >: A ]( list: MyList[ B ] ): MyList[ B ] = Node( head, tail ++ list )
}

//trait MyPredicate[ -T ] {
//	def test( elem: T ): Boolean
//}
//
//trait MyTransformer[ -A, B ] {
//	def transform( elem: A ): B
//}

case object MyApp extends App with LazyLogging {
	val myList = Node( 1, Node( 2, Node( 3, Node( 5, EmptyList ) ) ) )
	logger.info( myList.toString )

	// Case 1: List of zero elements 	-> [  ]
	println( EmptyList )
	assert( EmptyList.toString equals "[  ]" )

	// Case 2: List of 1 element 			-> [ 1 (Integer) ]
	println( EmptyList.add( 1 ) )
	assert( EmptyList.add( 1 ).toString equals "[ 1 (Integer) ]" )

	// Case 3: List of 2 elements			-> [ 1 (Integer), 2 (Integer) ]
	println( EmptyList.add( 2 ).add( 1 ) )
	assert( EmptyList.add( 2 ).add( 1 ).toString equals "[ 1 (Integer), 2 (Integer) ]" )

	// Case 4: List of 4 elements			-> [ 1 (Integer), Isaac (String), 3 (Integer), Amezcua (String) ]
	println( EmptyList.add( "Amezcua" ).add( 3 ).add( "Isaac" ).add( 1 ) )
	assert( EmptyList.add( "Amezcua" ).add( 3 ).add( "Isaac" ).add( 1 ).toString equals "[ 1 (Integer), Isaac (String), 3 (Integer), Amezcua (String) ]" )

	/*
		My own implementation
	 */
	println( "\n\n*** My own implementation ***" )

	val list = EmptyList.add( 15 ).add( 12 ).add( 6 ).add( 5 ).add( 4 ).add( 3 ).add( 2 ).add( 1 )
	logger.info( s"Original list: ${list.toString}" )

	// map function implementation
	logger.info( "* Map function implementation" )
	val multiplierTransformer = new (Int => Int) {
		override def apply( thing: Int ): Int = thing * 2
	}
	val multipliedList = list.map( multiplierTransformer )
	assert( multipliedList.toString equals "[ 2 (Integer), 4 (Integer), 6 (Integer), 8 (Integer), 10 (Integer), " +
		"12 (Integer), 24 (Integer), 30 (Integer) ]" )
	assert( EmptyList.map( multiplierTransformer ).toString.equals( "[  ]" ) )
	logger.info( multipliedList.toString )

	// filter function implementation
	logger.info( "* Filter function implementation" )
	val evenPredicate = new (Int => Boolean) {
		override def apply( element: Int ): Boolean = element % 2 == 0
	}
	val filteredList = list.filter( evenPredicate )
	assert( filteredList.toString.equals( "[ 2 (Integer), 4 (Integer), 6 (Integer), 12 (Integer) ]" ) )
	assert( EmptyList.filter( evenPredicate ).toString.equals( "[  ]" ) )
	logger.info( filteredList.toString )

	// flatMap function implementation
	logger.info( "* FlatMap function implementation" )

	val elementAndElementPlusOneTransformer = new (Int => MyList[ Int ]) {
		override def apply( element: Int ): MyList[ Int ] = Node( element, Node( element + 1, EmptyList ) )
	}
	val flatMappedList = list.flatMap( elementAndElementPlusOneTransformer )
	assert( flatMappedList.toString equals "[ 1 (Integer), 2 (Integer), 2 (Integer), 3 (Integer), 3 (Integer)," +
		" 4 (Integer), 4 (Integer), 5 (Integer), 5 (Integer), 6 (Integer), 6 (Integer), 7 (Integer), 12 (Integer)," +
		" 13 (Integer), 15 (Integer), 16 (Integer) ]" )
	assert( EmptyList.flatMap( elementAndElementPlusOneTransformer ).toString.equals( "[  ]" ) )
	logger.info( flatMappedList.toString )


	/*
		Author's code
	 */
	println( "\n\n*** Author's code ***" )

	val listOfIntegers: MyList[Int] = Node( 1, Node(2, Node( 3, EmptyList ) ) )
	val anotherListOfIntegers: MyList[Int] = Node( 1, Node( 4, Node( 3, EmptyList ) ) )
	val listOfStrings: MyList[String] = Node( "Hello", Node( "Scala", EmptyList ) )

	println( listOfIntegers)
	println( listOfStrings )

	println( listOfIntegers.map( new Function1[Int, Int] {
		override def apply( elem: Int ): Int = elem * 2
	}).toString )

	println( listOfIntegers.filter( new Function1[Int, Boolean] {
		override def apply( elem: Int ): Boolean = elem % 2 == 0
	}).toString )

	println( listOfIntegers ++ anotherListOfIntegers )
	println( listOfIntegers.flatMap( new Function1[Int, MyList[Int]] {
		override def apply(v1: Int): MyList[Int] = Node( v1, Node( v1 + 1, EmptyList ) )
	}).toString )

}

