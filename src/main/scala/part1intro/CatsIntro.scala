package part1intro

object CatsIntro {

  // Eq
  val aComparison: Boolean = 2 == "a string"

  // part 1 - type class import
  import cats.Eq

  // part 2 - import tc instances for the types you neeed
  import cats.instances.int._

  // part 3 - use the tc API
  val intEquality = Eq[Int]

  val aTypeSafeComparison: Boolean = intEquality.eqv(2, 3)
  //  val anUnsafeComparison: Boolean = intEquality.eqv(2, "a string")

  // part 4 - extension methods
  import cats.syntax.eq._

  val anotherTypeSafeComp: Boolean = 2 === 3
  val yetAnotherTypeSafeComp: Boolean = 2 =!= 3

  // part 5 - extending the type class operations to composite types
  val aListComparison: Boolean = List(2) === List(2)

  // part 6 - create a type class instance for a custom type
  case class ToyCar(model: String, price: Double)
  implicit val ToyCarEq: Eq[ToyCar] = Eq.instance[ToyCar] {(car1, car2) =>
    car1.price == car2.price
  }

  def main(args: Array[String]): Unit = {
    println(aListComparison)
  }
}
