package part1intro

object Implicits {

  // implicit class
  case class Person (name: String) {
    def greet: String = s"Hi, my name is $name"
  }

  implicit class ImpersonableString (name: String) {
    def greet: String = Person(name).greet
  }

  // explicit
  val impersonableString: ImpersonableString = new ImpersonableString("Karan")
  impersonableString.greet

  // implicit
  val greet: String = "Karan".greet // new ImpersonableString("Karan").greet

  // importing implicit conversions in scope
  import scala.concurrent.duration._

  val oneSecond: FiniteDuration = 1.second

  // implicit arguments and values
  def increment(x: Int)(implicit amount: Int): Int = x + amount
  implicit val defaultAmount = 10
  val incremented2 = increment(2) // implicit argument 10 is passed by the compiler

  def multiply(x: Int)(implicit times: Int): Int = x * times
  val multiplied: Int = multiply(10)

  trait JsonSerializer[T] {
    def toJson(value: T): String
  }

  def listToJson[T](list: List[T])(implicit serializer: JsonSerializer[T]): String =
    list.map(value => serializer.toJson(value))
      .mkString("[", ",", "]")

  implicit val personSeralizer: JsonSerializer[Person] = new JsonSerializer[Person] {
    def toJson(value: Person): String =
      s"""
         |{"name": "${value.name}"}
         |""".stripMargin
  }

  val personsJson = listToJson(List(Person("Alice"), Person("Bob")))
  // implicit arguments are used to PROVE the existence of a type

  // implicit methods
  implicit def oneArgCaseClassSerializer[T <: Product]: JsonSerializer[T] = (value: T) =>
    s"""
       | {"${value.productElementName(0)}": "${value.productElement(0)}"}
       |""".stripMargin

  case class Cat(catName: String)
  case class Invitee(name: String)


  def main(args: Array[String]): Unit = {
    println(incremented2)
    println(multiplied)
    println(personsJson)

    val cats = List(Cat("Alice"), Cat("Bob"))
    println(listToJson(cats))
  }
}
