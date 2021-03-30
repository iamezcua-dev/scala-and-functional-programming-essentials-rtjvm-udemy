package lectures.part3.functionalprogramming

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
  
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    ???
  }
  
}
