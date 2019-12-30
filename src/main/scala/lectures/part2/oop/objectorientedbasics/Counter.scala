package lectures.part2.oop.objectorientedbasics

import scala.annotation.tailrec

class Counter( val count: Int = 0 ) {
  def printCurrentCount = println( count )
  
  def increment: Counter = {
    println( "Incrementing ..." )
    new Counter( count + 1 )
  }
  
  def decrement: Counter = {
    println( "Decrementing ..." )
    new Counter( count - 1 )
  }
  
  @tailrec
  final def increment( times: Int ): Counter = {
    if( times <= 0 ) this
    else increment.increment( times - 1 )
  }
  
  @tailrec
  final def decrement( times: Int ): Counter = {
    if( times <= 0 ) this
    else decrement.decrement( times - 1 )
  }
}
