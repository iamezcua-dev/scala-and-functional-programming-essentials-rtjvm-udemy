package lectures.part3.functionalprogramming

import com.typesafe.scalalogging.LazyLogging

object TuplesAndMaps extends App with LazyLogging {
  // Tuples = finite ordered "lists"
  val aTuple = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  
  println( aTuple._1 ) // 2
  println( aTuple.copy( _2 = "Goodbye Java" ) )
  println( aTuple.swap ) // ("Hello, Scala", 2)
  
  // Maps - keys -> values
  val aMap: Map[ String, Int ] = Map()
  
  val phoneBook = Map( ("Jim", 555), ("Daniel", 789) ).withDefaultValue( -1 )
  val phoneBook2 = Map( "Jim" -> 555, "Daniel" -> 789 ).withDefaultValue( -1 )
  
  println( phoneBook )
  println( phoneBook2 )
  
  // Map Operations
  println( phoneBook.contains( "Jim" ) )
  println( phoneBook( "Jim" ) )
  println( phoneBook( "Mary" ) )
  
  // Add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println( newPhoneBook )
  
  // Functionals on Mapp ( map, flatmap, filter )
  println( phoneBook.map( pair => pair._1.toLowerCase -> pair._2 ) )
  
  // filterKeys
  println( phoneBook.view.filterKeys( x => x.startsWith( "J" ) ).toMap )
  
  // mapValues
  println( phoneBook.view.mapValues( number => number * 10 ).toMap )
  println( phoneBook.view.mapValues( number => s"0234-$number" ).toMap )
  
  // conversions to other collections
  println( phoneBook.toList )
  println( List( ("Daniel", 555) ).toMap )
  val names = List( "Bob", "James", "Angela", "Mary", "Daniel", "Jim" )
  println( names.groupBy( name => name.charAt( 0 ) ) )
  
  /*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900?
  */
  val peopleMap = Map( "Jim" -> 555, "JIM" -> 900 )
  assert( peopleMap.map( person => person._1.toLowerCase -> person._2 ).mkString == Map( "jim" -> 900 ).mkString )
  
  // Answer:  If we insert a (key,value) pair in a Map where such `key` already exist, such key gets updated with the new value,
  //          and the old value is discarded.
  
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
  case class Person( name: String )
  
  class SocialNetwork( val socialNetworkContents: Map[ Person, List[ Person ] ] = Map() ) {
    
    override def toString: String = {
      if ( socialNetworkContents.isEmpty ) "[ Empty ]"
      else socialNetworkContents.toString
    }
    
    def add( person: Person ): SocialNetwork = {
      // Appends a new association (Person, List[Person]) to then social network
      // When the person first join the social network, he/she has no friends, so his/her friend list is empty.
      SocialNetwork( socialNetworkContents ++ Map( person -> List() ) )
    }
    
    def remove( personName: String ): SocialNetwork = {
      SocialNetwork(
        socialNetworkContents
            // Removes the person from the social network
            .filter( association => association._1.name != personName )
            // After removing the person, traverse each and every association (Person, List[Person]) and remove
            // such person as a friend since he/she no longer belongs to the social network
            .map( association => association._1 -> association._2.filter( _.name != personName ) )
      )
    }
    
    def unfriend( personName: String, unfriendedName: String ): SocialNetwork = {
      SocialNetwork(
        // Removes a person from someone else's list by appending an association (Person, List(Person)) where the `Person`
        // is the unfriender Person and the `List[Person]` is his/her friend list with the unfriended Person filtered out.
        socialNetworkContents ++
            Map( Person( personName ) -> socialNetworkContents( Person( personName ) ).filter( _.name != unfriendedName ) ) ++
            Map( Person( unfriendedName ) -> socialNetworkContents( Person( unfriendedName ) ).filter( _.name != personName )
            )
      )
    }
    
    def friend( personA: String, personB: String ): SocialNetwork = {
      if ( socialNetworkContents.isEmpty ) {
        SocialNetwork(
          socialNetworkContents ++
              Map( Person( personA ) -> List( Person( personB ) ), Person( personB ) -> List( Person( personA ) ) )
        )
      } else {
        if ( !existInSocialNetwork( personA ) ) {
          logger.debug( s"Creating $personA" )
          add( Person( personA ) ).friend( personA, personB )
        } else if ( !existInSocialNetwork( personB ) ) {
          logger.debug( s"Creating $personB" )
          add( Person( personB ) ).friend( personA, personB )
        } else {
          SocialNetwork(
            socialNetworkContents ++
                Map( Person( personA ) -> ( socialNetworkContents( Person( personA ) ) :+ Person( personB ) ) ) ++
                Map( Person( personB ) -> ( socialNetworkContents( Person( personB ) ) :+ Person( personA ) ) )
          )
        }
      }
    }
    
    def showNumberOfFriends( name: String ): Int = {
      if ( socialNetworkContents.isEmpty ) -1
      else if ( !existInSocialNetwork( name ) ) -2
      else socialNetworkContents( Person( name ) ).size
    }
    
    def existInSocialNetwork( personName: String ): Boolean = {
      if ( socialNetworkContents.isEmpty ) false
      // Checks whether a Person exists in the social network by comparing each and every lower-cased Person name and
      // see if any of them matches the lower-cased personName.
      else socialNetworkContents.keySet.map( _.name.toLowerCase ).contains( personName.toLowerCase )
    }
    
    def showPeopleWithoutFriends( ): Unit = {
      ???
    }
    
    def personWithMostFriends( ): Unit = {
      ???
    }
    
    def isThereASocialConnectionBetween( personA: Person, personB: Person ): Boolean = {
      ???
    }
  }
  
  object SocialNetwork {
    def apply( socialNetworkContents: Map[ Person, List[ Person ] ] = Map() ): SocialNetwork = {
      new SocialNetwork( socialNetworkContents )
    }
  }
  
  // 1) Add a person to the network
  val socialNetwork = SocialNetwork()
  val updatedSocialNetwork = socialNetwork.add( Person( "Isaac Amezcua" ) ).add( Person( "Eréndira Ávila" ) )
  println( s"1) Current Social Network:" )
  println( s"\t${socialNetwork.add( Person( "Isaac Amezcua" ) )}" )
  
  // 2) removing a person from the Social Network
  println( "2) Removing a person from the Social Network" )
  println( s"\tCurrent Social Network: $updatedSocialNetwork" )
  println( s"\tRemoved Isaac Amezcua from the Social Network: ${updatedSocialNetwork.remove( "Isaac Amezcua" )}" )
  
  // 3) Adding someone as a friend
  println( "3) Adding Benjamín Vargas as Isaac Amezcua's friend" )
  println( updatedSocialNetwork.friend( "Benjamín Vargas", "Isaac Amezcua" ) )
  println( "* Adding Isaac Amezcua as Eréndira Avila's and Benjamín Vargas' friend" )
  println( updatedSocialNetwork
      .friend( "Benjamín Vargas", "Isaac Amezcua" )
      .friend( "Eréndira Ávila", "Isaac Amezcua" )
  )
  
  // A specific person exists in the Social Network?
  println( "* A specific person exists in the Social Network?" )
  println( s"\tDoes Isaac Amezcua exist in the Social Network? ${updatedSocialNetwork.existInSocialNetwork( "Isaac Amezcua" )}" )
  println( s"\tDoes Eréndira Ávila exist in the Social Network? ${updatedSocialNetwork.existInSocialNetwork( "Eréndira Ávila" )}" )
  println( s"\tDoes Benjamín Vargas exist in the Social Network? ${updatedSocialNetwork.existInSocialNetwork( "Benjamín Vargas" )}" )
  
  // 4) Unfriend
  println( "4) Unfriending people in the Social Network" )
  val socialNetworkWithFriends = updatedSocialNetwork
      .friend( "Benjamín Vargas", "Isaac Amezcua" )
      .friend( "Eréndira Ávila", "Isaac Amezcua" )
  println( "* Before unfriending ..." )
  println( socialNetworkWithFriends )
  println( "* Isaac Amezcua unfriending Eréndira Ávila" )
  println( socialNetworkWithFriends.unfriend( "Isaac Amezcua", "Eréndira Ávila" ) )
  
  // 5) Number of friends of a person
  println( "5) Number of friends of a person" )
  println( "* Current Social Network" )
  println( socialNetworkWithFriends )
  println( s"* Erendira's friend count: ${socialNetworkWithFriends.showNumberOfFriends( "Eréndira Ávila" )}" )
  println( s"* Benjamín's friend count: ${socialNetworkWithFriends.showNumberOfFriends( "Benjamín Vargas" )}" )
  println( s"* Isaac's friend count: ${socialNetworkWithFriends.showNumberOfFriends( "Isaac Amezcua" )}" )
  
  // 6) Person with most friends
  println( "6) Person with most friends" )
  println( s"Person with most friends: ${socialNetworkWithFriends.personWithMostFriends()}" )
}
