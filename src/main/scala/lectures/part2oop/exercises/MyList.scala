package com.rockthejvm
package lectures.part2oop.exercises

import scala.annotation.tailrec


abstract class MyList {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
  */
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String
  override def toString: String = s"[ $printElements ]"
}

object Empty extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element, this)
  override def printElements: String =
    if t.isEmpty then s"$h"
    else s"$h, ${t.printElements}"
}

object ListTest {
  @main
  def main(): Unit = {
    val myList: MyList = new Cons(1, new Cons(2, new Cons(3, Empty)))
    println(myList)
  }
}