package part1intro

import scala.util.Try

object Essentials {

  // values
  val aBoolean: Boolean = true

  // expressions are EVALUATED to a value
  val anIfExpr = if (2 > 3) "yes" else "no"

  // instructions vs. expressions
  val theUnit = println("Scala") // Unit = void in other languages

  // OOP
  class Animal
  class Cat extends Animal
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  // inheritance model : extend at most 1 class but mix in >= 0 traits
  class Crocodile extends Animal with Carnivore {
    def eat(animal: Animal): Unit = println("Carnivore eating")
  }

  // singleton
  object MySingleton // singleton pattern in one line

  // companion objects
  object Carnivore {
    def staticThing(): Unit = println("This is a static method")
  }

  // generics
  class MyList[A]

  // method notation
  val three = 1 + 2
  val anotherThree = 1.+(2) // + is a method on Int

  // functional programming
  val incrementor: Function[Int, Int] = x => x + 1
  val incremented = incrementor(45) // 46

  // map, flatMap, Filter
  val processed = List(1, 2, 3).map(incrementor) // List(2, 3, 4)
  val aLongerList = List(1, 2, 3).flatMap(x => List(x, x * 1)) // List(1, 2, 2, 3, 3, 4)

  // options and try
  val anOption: Option[Int] = Option(/** something that might be null **/ 3) // Some(3)
  val doubled: Option[Int] = anOption.map(_ * 2)

  val anAttempt = Try(/** something that might throw **/ 42) // Success(42)
  val aModifiedMap: Try[Int] = anAttempt.map(_ + 10)

  // pattern matching
  val anUnknown: Any = 45

  val ordinal: String = anUnknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }

  // For comprehensions
  var anotherVal = for {
    a <- List(1, 2, 3)
    b <- List('a', 'b', 'c')
  } yield (a, b)

  // some more advanced stuff
  trait HigherKindedType[F[_]]
  trait SequenceChecker[F[_]] {
    def isSequential: Boolean
  }

  val listChecker = new SequenceChecker[List] {
    override def isSequential: Boolean = true
  }

  def main(args: Array[String]): Unit = {

  }
}
