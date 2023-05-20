package com.rockthejvm
package lectures.part2oop.exercises

import org.scalatest.wordspec.AnyWordSpec

import scala.math.pow

class MyListTest extends AnyWordSpec {
  
  "MyList" should {
    "map integers" when {
      "providing a valid MyTransformer as transformer" in {
        val myList: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))
        val myTransformer: MyTransformer[Int, Int] = (element: Int) => pow(element, 2).toInt
        val squaredPoweredList = myList.map(myTransformer)
        assert(new Cons(1, new Cons(4, new Cons(9, new Cons(16, new Cons(25, Empty))))).toString == squaredPoweredList.toString)
      }
    }
    
    "flatMap integers" when {
      "providing [n, n*2, n*3] as transformer" in {
        val myList: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
        val myTransformer: MyTransformer[Int, MyList[Int]] = (element: Int) => new Cons(element, new Cons(pow(element, 2).toInt, new Cons(pow(element, 3).toInt, Empty)))
        val squaredPoweredList = myList.flatMap(myTransformer)
        assert(
          new Cons(1,
            new Cons(1,
              new Cons(1,
                new Cons(2,
                  new Cons(4,
                    new Cons(8,
                      new Cons(3,
                        new Cons(9,
                          new Cons(27,
                            Empty))))))))).toString == squaredPoweredList.toString)
      }
    }
    
    "filter integers" when {
      "providing a valid MyPredicate as filter" in {
        val myList: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, new Cons(6, Empty))))))
        val evenNumbersFilter: MyPredicate[Int] = (element: Int) => element % 2 == 0
        val evenNumberOnlyList = myList.filter(evenNumbersFilter)
        assert(new Cons(2, new Cons(4, new Cons(6, Empty))).toString == evenNumberOnlyList.toString)
      }
    }
    
    
    "concatenate with another non-empty list" in {
      val myList: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
      val anotherList: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
      val concatenatedList = myList ++ anotherList
      assert(new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, new Cons(6, Empty)))))).toString == concatenatedList.toString)
    }
    
    "concatenate an empty list with another non-empty list" in {
      val myList: MyList[Int] = Empty
      val anotherList: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
      val concatenatedList = myList ++ anotherList
      assert(new Cons(4, new Cons(5, new Cons(6, Empty))).toString
          == concatenatedList.toString)
    }
    
    "concatenate an empty list with another empty list" in {
      val myList: MyList[Int] = Empty
      val anotherList: MyList[Int] = Empty
      val concatenatedList = myList ++ anotherList
      assert(Empty.toString == concatenatedList.toString)
    }
    
    
  }
  
  
}
