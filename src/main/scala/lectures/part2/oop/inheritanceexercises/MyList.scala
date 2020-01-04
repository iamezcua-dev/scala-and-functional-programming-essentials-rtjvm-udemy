package lectures.part2.oop.inheritanceexercises

import com.typesafe.scalalogging.LazyLogging

abstract class MyList extends LazyLogging {
	/*
		head = first element of  the list
		tail = remaider of the list
		isEmpty = is the list empty?
		add(int) => new list with thisx element added
		toString => a string representation of the list
	 */
	def head: Int
	def tail: MyList
	def isEmpty: Boolean
	def add( number: Int ): MyList
	def listContents: String
	override def toString: String = s"[ $listContents ]"
}

object EmptyList extends MyList {
	def head: Int = throw new NoSuchElementException
	def tail: MyList = throw new NoSuchElementException
	def isEmpty: Boolean = true
	def add( element: Int ): MyList = new Node( element, EmptyList )
	def listContents: String = ""
}

class Node( element: Int, listTail: MyList ) extends MyList {
	def head: Int = element
	def tail: MyList = this.listTail
	def isEmpty: Boolean = false
	def add( element: Int ): MyList = new Node( element, this )
	def listContents: String = {
		if ( listTail.isEmpty ) s"$element"
		else s"$element ${listTail.listContents}"
	}
}

object MyApp extends App {
	val list = new Node( 1, new Node( 2, new Node( 3, new Node( 5, EmptyList ) ) ) )
	logger.info( list )
}