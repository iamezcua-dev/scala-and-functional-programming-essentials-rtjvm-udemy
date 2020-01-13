package exercises

import com.typesafe.scalalogging.LazyLogging

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
}

object EmptyList extends MyList[ Nothing ] {
	def head: Nothing = throw new NoSuchElementException
	def tail: Nothing = throw new NoSuchElementException
	def isEmpty: Boolean = true
	def add[ B >: Nothing ]( element: B ): MyList[ B ] = new Node( element, EmptyList )
	def listContents: String = ""
}

class Node[ +A ]( element: A, listTail: MyList[ A ] ) extends MyList[ A ] {
	def head: A = element
	def tail: MyList[ A ] = this.listTail
	def isEmpty: Boolean = false
	// B supertype of Nothing
	def add[ B >: A ]( element: B ): MyList[ B ] = new Node( element, this )
	def listContents: String = {
		if ( listTail.isEmpty ) s"$element (${element.getClass.getSimpleName})"
		else s"$element (${element.getClass.getSimpleName}), ${listTail.listContents}"
	}
}

object MyApp extends App with LazyLogging {
	val list = new Node( 1, new Node( 2, new Node( 3, new Node( 5, EmptyList ) ) ) )
	logger.info( list.toString )
	
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
	
}

