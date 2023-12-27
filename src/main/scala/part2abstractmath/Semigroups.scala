package part2abstractmath


object Semigroups {

  // semigroups COMBINE elements of the same type
  import cats.Semigroup
  import cats.instances.int._
  import cats.instances.string._
  import cats.instances.option._

  val naturalIntSemigroup: Semigroup[Int] = Semigroup[Int]
  val intCombination: Int = naturalIntSemigroup.combine(1, 2) // addition

  val naturalStringSemigroup: Semigroup[String] = Semigroup[String]
  val stringCombination: String = naturalStringSemigroup.combine("Hello, ", "world") // concatenation

  private def reduceInts(list: List[Int]): Int = list.reduce(naturalIntSemigroup.combine)
  private def reduceStrings(list: List[String]): String = list.reduce(naturalStringSemigroup.combine)

  // general reduction API - the power of semigroups
  private def reduceThings[T](list: List[T])(implicit semigroup: Semigroup[T]): T =
    list.reduce(semigroup.combine)


  // TODO 1: support a new type
  case class Expense(id: Long, amount: Double)

  implicit val ExpenseSemigroup: Semigroup[Expense] = Semigroup.instance[Expense] {(e1, e2) =>
    Expense(Math.max(e1.id, e2.id), e1.amount + e2.amount)
  }

  // extension methods from semigroup - |+|
  import cats.syntax.semigroup._

  val anIntSum = 2 |+| 3
  val aStringConcat = "we like " |+| "semigroups"
  val aCombinedExpense = Expense(4, 80) |+| Expense(8, 56)

  // TODO 2: implement reduceThings2
  private def reduceThings2[T : Semigroup](list: List[T]): T = list.reduce(_ |+| _)

  def main(args: Array[String]): Unit = {
    println(intCombination) // 3
    println(stringCombination) // "Hello, world"

    val numbers = (1 to 10).toList
    println(reduceInts(numbers))

    val strings = List("I'm ", "starting ", "to ", "enjoy ", "semigroups")
    println(reduceStrings(strings))

    // general API
    reduceThings(numbers)
    reduceThings(strings)

    val numberOptions: List[Option[Int]] = numbers.map(n => Option(n))
    println(reduceThings(numberOptions)) // an Option[Int] containing the sum of all numbers

    // test todo 1
    val expenses = List(Expense(1, 99), Expense(2, 33), Expense(3, 50))
    println(reduceThings(expenses))
    println(reduceThings2(expenses))
  }
}
