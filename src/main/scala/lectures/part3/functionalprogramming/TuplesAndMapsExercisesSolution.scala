package lectures.part3.functionalprogramming

import scala.annotation.tailrec

object TuplesAndMapsExercisesSolution extends App {
  /*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900?
  */
  // Answer: be careful with mapping keys. Since keys are unique, further key definitions that share the same
  // key name will cause the value for that key to be replaced with the last definition.
  
  /*
    2. Design an overly simplified social network based on maps.
        Person = String
          1.- add a person to the network
          2.- remove
          3.- fiend( mutual )
          4.- unfriend
          5.- number of friends of a person
          6.- person with most friends
          7.- how many people have NO friends
          8.- if there is a social connection between people ( direct or not )
   */
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())
  
  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }
  
  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }
  
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }
  
  // Testing
  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))
  
  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")
  
  println(testNet)
  
  
  // Mary, Peter, John, Jim, Bob, Stu
  val people2 =
    add(
      add(
        add(
          add(
            add(
              add(
                add(
                  add(empty, "Mary"),
                  "Peter"),
                "John"),
              "Jim"),
            "Bob"),
          "Stu"),
        "Gerard"),
      "Brandon")
  
  val maryFriends =
    friend(
      friend(
        friend(
          friend(people2,
            "Mary", "Bob"),
          "Mary", "Jim"),
        "Mary", "John"),
      "Mary", "Peter")
  
  val peterFriends =
    friend(
      friend(
        friend(maryFriends,
          "Peter", "John"),
        "Peter", "Mary"),
      "Peter", "Stu")
  
  val johnFriends =
    friend(
      friend(
        friend(peterFriends,
          "John", "Mary"),
        "John", "Jim"),
      "John", "Peter")
  
  val jimFriends =
    friend(
      friend(johnFriends,
        "Jim", "John"),
      "Jim", "Mary")
  
  val bobFriends =
    friend(
      friend(
        friend(jimFriends,
          "Bob", "Mary"),
        "Bob", "Stu"),
      "Bob", "Gerard")
  
  val gerardFriends =
    friend(bobFriends,
      "Gerard", "Bob")
  
  val bigSocialNetwork =
    friend(
      friend(gerardFriends,
        "Stu", "Bob"),
      "Stu", "Peter")
  
  
  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if(!network.contains(person)) 0
    else network(person).size
  
  println(nFriends(testNet, "Bob"))
  
  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1
  
  println(mostFriends(testNet))
  
  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.view.count(_._2.isEmpty)
  
  println(nPeopleWithNoFriends(testNet))
  
  /**
   * Checks whether <code>a</code> (or any of their friends) has a social connection with <code>b</code>
   *
   * @param network The social group to look into.
   * @param a       The person in question.
   * @param b       The person that may have social connection with <code>a</code>.
   * @return Whether or not <code>a</code> has a direct or indirect social connection with <code>b</code>.
   */
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if(person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    
    bfs(target = b, consideredPeople = Set(), discoveredPeople = network(a) + a)
  }
  
  println(s"*** Test network contents:\n$testNet")
  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
  
  people2.foreach(x =>
    people2.foreach(y =>
      println(s"Has ${x._1} a social connection to ${y._1}? ${socialConnection(bigSocialNetwork, s"${x._1}", s"${y._1}")}")
    )
  )
  
}
