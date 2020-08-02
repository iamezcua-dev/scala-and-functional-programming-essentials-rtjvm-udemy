package lectures.part3.functionalprogramming

import com.typesafe.scalalogging.LazyLogging
import exercises.{EmptyList, Node}

object HOFsCurries extends App with LazyLogging {
  val superFunction: (Int, (String, ( Int => Boolean )) => Int) => ( Int => Int ) = null
  // Functions that either, take functions as parameters or return functions as results are called:
  //  ===> High Order Functions
  
  // map, flatMap, filter in MyList
  
  // A function that applies a function n times over a value x
  // nTimes(f, x, n)
  // nTimes(f, x, 3) = f(f(f(x)))
  /**
   *
   * @param f The function to be applied
   * @param x The value that is subject to function application
   * @param n The number of times the function is applied
   * @return The value resulting after the application of the chained function calls
   */
  def nTimes( f: Int => Int, x: Int, n: Int ): Int =
    if( n <= 0 ) x
    else nTimes( f, f( x ), n - 1 )
  
  val plusOne = ( x: Int ) => x + 1
  println( nTimes( plusOne, 1, 10 ) )
  
  /**
   *
   * @param f The function to be applied
   * @param n The number of times the function is applied
   * @return A function
   */
  // nTimesBetter(f, n) = x => f(f(f...(x)))
  def nTimesBetter( f: Int => Int, n: Int ): ( Int => Int ) =
    if( n <= 0 ) ( x: Int ) => x
    else ( x: Int ) => nTimesBetter( f, n - 1 )( f( x ) )
  
  val plusTen = nTimesBetter( plusOne, 10 )
  println( plusTen( 1 ) )
  
  // Curried functions
  val superAdder: Int => ( Int => Int ) = ( x: Int ) => ( y: Int ) => x + y
  val add3 = superAdder( 3 ) // y => 3 + y
  println( add3( 10 ) )
  println( superAdder( 10 )( 3 ) )
  
  // Functions with multiple parameter lists
  def curriedFormatter( c: String )( x: Double ): String = c.format( x )
  
  val standardFormat: ( Double => String ) = curriedFormatter( "%4.2f" )
  val preciseFormat: ( Double => String ) = curriedFormatter( "%10.8f" )
  
  println( standardFormat( Math.PI ) )
  println( preciseFormat( Math.PI ) )
  
  /*
    1. Expand MyList
        - foreach method A => Unit
        [1,2,3].foreach(x => println(x))

        - sort function ((A, A) => Int) => MyList
        [1,2,3].sort((x, y) => y - x) => [3,2,1]

        - zipWith( list, (A, A) => B ) => MyList[B]
        [1,2,3].zipWith( [4,5,6], x * y ) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

        - fold(start)(function) => a value
        [1,2,3].fold(0)(x + y) = 6

    2.  toCurry(f: (Int, Int) => Int) => ( Int => Int => Int )
        fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

    3.  compose(f,g) => x => f(g(x))
        andThen(f,g) => x => g(f(x))
   */
  
  /*
    Isaac's attempts
   */
  val list = Node( 1, Node( 2, Node( 3, Node( 4, EmptyList ) ) ) )
  val list1 = Node( 8, EmptyList )
  
  //	1.1. foreach
  logger.info( s"Foreach - Applying foreach for an empty list (Nothing should be printed)." )
  EmptyList.foreach( println )
  logger.info( s"Foreach - Applying foreach for an single-element list $list1." )
  list1.foreach( println )
  logger.info( s"Foreach - Applying foreach for list $list :" )
  list.foreach( println )
  
  //	1.2. sort
  val descendingOrder = ( x: Int, y: Int ) => y - x
  logger.info( s"Sort - Sorting a empty list:" )
  val resultE = EmptyList.sort( descendingOrder )
  println( s"=> Results: ${resultE.toString}\n" )
  assert( resultE.toString == "[  ]" )
  
  logger.info( s"Sort - Sorting a single-element list $list1" )
  val result1 = list1.sort( descendingOrder )
  println( s"=> Results: ${result1.toString}\n" )
  assert( result1.toString == "[ 8 (Integer) ]" )
  
  logger.info( s"Sorting a list in descending order $list" )
  val result = list.sort( descendingOrder )
  println( s"=> Results: ${result.toString}\n" )
  assert( result.toString == "[ 4 (Integer), 3 (Integer), 2 (Integer), 1 (Integer) ]" )
  
  val list2 = Node( 7, Node( 5, Node( 9, Node( 6, Node( 3, Node( 8, EmptyList ) ) ) ) ) )
  logger.info( s"Sorting a list in descending order $list2" )
  val result2 = list2.sort( descendingOrder )
  println( s"=> Results: ${result2.toString}\n" )
  assert( result2.toString == "[ 9 (Integer), 8 (Integer), 7 (Integer), 6 (Integer), 5 (Integer), 3 (Integer) ]" )
  
  //	1.3 zipWith
  val anotherList = Node( 5, Node( 6, Node( 7, Node( 8, EmptyList ) ) ) )
  val multiply = ( x: Int, y: Int ) => x * y
  logger.info( s"Zipping the list $list with $anotherList using a multiply function:" )
  val zippedList = list.zipWith( anotherList, multiply )
  println( s"=> Results: ${zippedList.toString}\n" )
  assert( zippedList.toString == "[ 5 (Integer), 12 (Integer), 21 (Integer), 32 (Integer) ]" )
  
  logger.info( s"Zipping the list $list with an empty list using a multiply function:" )
  val zippedList1 = list.zipWith( EmptyList, multiply )
  println( s"=> Results: ${zippedList1.toString}\n" )
  assert( zippedList1.toString == "[  ]" )
  
  logger.info( s"Zipping an empty list with $list using a multiply function:" )
  val zippedList2 = EmptyList.zipWith( list, multiply )
  println( s"=> Results: ${zippedList2.toString}\n" )
  assert( zippedList2.toString == "[  ]" )
  
  //	1.4 fold
  logger.info( s"Folding a list $list" )
  val adder = ( a: Int, b: Int ) => a + b
  val foldedList = list.fold( 0 )( adder )
  println( s"=> Results: ${foldedList}\n" )
  assert( foldedList == 10 )
  
  //	2. toCurry and fromCurry
  logger.info( s"To curry ..." )
  
  def toCurry( f: (Int, Int) => Int ): ( Int => Int => Int ) = {
    x => y => f( x, y )
  }
  
  val resultTC = toCurry( adder )
  val addOneTC = resultTC( 1 )
  println( s"=> Result: ${addOneTC( 2 )}" )
  
  logger.info( s"From curry ..." )
  
  def fromCurry( f: ( Int => Int => Int ) ): (Int, Int) => Int = {
    ( x, y ) => f( x )( y )
  }
  
  val resultFC = fromCurry( superAdder )
  println( s"=> Result: ${resultFC( 20, 10 )}" )
  
  //	3. compose and andThen
  // FunctionX
  logger.info( s"Compose ..." )
  
  def compose[ A, B, T ]( f: A => B, g: T => A ): T => B =
    x => f( g( x ) )
  
  val addTwo = ( x: Int ) => x + 2
  val threeTimes = ( x: Int ) => x * 3
  val composed = compose( addTwo, threeTimes )
  println( s"=> Result: ${composed( 2 )}" )
  
  logger.info( s"AndThen ..." )
  
  def andThen[ A, B, C ]( f: A => B, g: B => C ): A => C =
    x => g( f( x ) )
  
  val ordered = andThen( addTwo, threeTimes )
  println( s"=> Result: ${ordered( 2 )}" )
  
}
