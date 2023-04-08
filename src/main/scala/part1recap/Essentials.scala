package part1recap

import com.sun.net.httpserver.Authenticator.Success

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try
import scala.util.Success
import scala.util.Failure

object Essentials {
  // values
  val aBoolean: Boolean = true

  // expressions are EVALUATED
  val x = 10
  val expr: Boolean = if (x % 2 == 0) true else false

  // instructions vs. expressions
  val theUnit: Unit = println("hello scala")

  // OOP
  class Animal
  class Cat extends Animal
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  // Inheritance model: extend at most one class, but inherit from at least 0 trais.
  // you can mix in as many traits as you want
  class Crocodile extends Animal with Carnivore {
    def eat(animal: Animal): Unit = println("eating...")
  }

  // singleton
  object MySingleton // singleton pattern in one line

  // companions
  object Carnivore // companion object of the class carnivore. Similar to Java, C++ static methods

  // generics
  class MyList[A]

  // method notation
  val three: Int = 1 + 2 // the operator + is a method on the Int type
  val anotherThree: Int = 1.+(2)
  // every method that has a single argument can be in-fixed

  // functional programming
  val incrementer: Int => Int = x => x + 1 // anonymous function
  val incremented: Int = incrementer(20) // 21

  // map, flatMap, filter
  val processedList: List[Int] = List(1, 2, 3).map(incrementer)
  val aLongerList: List[Int] = List(1, 2, 3).flatMap(x => List(x, x + 1)) // (1, 2, 2, 3, 3, 4)

  // for comprehensions
  val checkerboard: Seq[(Int, Char)] = List(1, 2, 3).flatMap(n => List('a', 'b', 'c').map(c => (n, c)))
  val anotherCheckerboard: Seq[(Int, Char)] = for {
    n <- List(1, 2, 3)
    c <- List('a', 'b', 'c')
  } yield (n, c) // equivalent expression

  // options and try
  val anOption: Option[Int] = Option(2) // Some(3)
  // here you're actually calling the apply method of the companion object of the Option abstract class
  val doubledOption: Option[Int] = anOption.map(_ * 2)

  val anAttempt: Try[Int] = Try(/** something that mgiht throw **/42) // Success(42)
  val aModifiedAttempt: Try[Int] = anAttempt.map(_ + 10)

  // pattern matching
  val anUknown: Any = 45;
  val ordinal: String = anUknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "uknown"
  }

  val isOptionEmpty: Boolean = anOption match {
    case Some(_) => false
    case None => true
  }

  // Futures - these require an implicit execution context
  implicit val ec: ExecutionContext =
    ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(8))
  val aFuture: Future[Int] = Future {
    // quite a bit of code
    42
  }

  // on complete function - a partial function
  aFuture.onComplete{
    case Success(value) => println(s"the async meaning of life is: $value")
    case Failure(exception) => println(s"Meaning of value failed with exception: $exception")
  }

  // map a future
  val improvedFuture: Future[Int] = aFuture.map(_ + 1) // Future(43) when it completes

  // partial functions
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 43
    case 8 => 56
    case 100 => 999
  }

  // some more advanced stuff
  trait HigherKindedType[F[_]]
  trait SequenceChecker[F[_]] {
    def isSequential: Boolean
  }

  val listChecker: SequenceChecker[List] = new SequenceChecker[List] {
    override def isSequential: Boolean = true
  }

  def main(args: Array[String]): Unit = {

  }
}
