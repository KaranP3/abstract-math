package part2abstractmath

object Monoids {

  // Semigroups provide a way to combine values of a given type
  import cats.Semigroup
  import cats.instances.int._
  import cats.syntax.semigroup._

  val numbers = (1 to 100).toList

  // |+| is associative, allows for combining values (in this case addition)
  val sumLeft: Int = numbers.foldLeft(0)(_ |+| _)
  val sumRight: Int = numbers.foldRight(0)(_ |+| _)

  // general API
 //  def combineFold[T : Semigroup](list: List[T]): T =
 //    list.foldLeft(/** what do you put here? **/)(_ |+| _)

  // monoids
  import cats.Monoid

  val intMonoid = Monoid[Int]
  val combineNumbers = intMonoid.combine(23, 999)

  val zero = intMonoid.empty

  import cats.instances.string._
  val emptyString = Monoid[String].empty
  val combineString = Monoid[String].combine("I understand ", "monoids")

  import cats.instances.option._
  val emptyOption = Monoid[Option[Int]].empty
  val combineOptions = Monoid[Option[Int]].combine(Option(2), Option.empty[Int])

  // extension method for monoids
  val combinedOptionFancy = Option(3) |+| Option(3)

  // TODO 1: implement a reduce by fold
  def combineFold[T : Monoid](list: List[T]): T = list.foldLeft(Monoid[T].empty)(_ |+| _)


  // TODO 2: combine a list of phone books as Maps[String, Int]
  val phonebooks = List(
    Map(
      "Alice" -> 235,
      "Bob" -> 647,
    ),
    Map(
      "Charlie" -> 372,
      "Daniel" -> 809,
    ),
    Map(
      "Tina" -> 123,
    )
  )

  import cats.instances.map._
  val combinedPhonebooks = combineFold(phonebooks)

  // TODO 3: shopping cart and online stores with monoids


  // Imagine that you've got multiple shopping carts in multiple tabs. When you click "checkout" you want these
  // to be combined in some way. A good use case for monoids!

  // A simplified shopping cart (yeah I know you shouldn't use doubles for money lol)
  case class ShoppingCart(items: List[String], total: Double)

  // A monoid instance for the ShoppingCart type
  implicit val shoppingCartMonoid: Monoid[ShoppingCart] = Monoid.instance[ShoppingCart](
    ShoppingCart(List(), 0),
    // this is the "combine" function
    (cart1, cart2) => ShoppingCart(cart1.items ++ cart2.items, cart1.total + cart2.total)
  )

  def checkout(shoppingCarts: List[ShoppingCart]): ShoppingCart =
    combineFold(shoppingCarts)

  val shoppingCarts: List[ShoppingCart] = List(
    ShoppingCart(List("Shoes", "Chocolate"), 800),
    ShoppingCart(List("Book", "iPhone"), 2000)
  )

  val checkoutCart = checkout(shoppingCarts)

  def main(args: Array[String]): Unit = {
    println(sumLeft)
    println(sumRight)

    println(zero)
    println(emptyString)
    println(emptyOption)
    println(combineOptions)
    println(combinedOptionFancy)

    println(combineFold(numbers))
    println(combinedPhonebooks)

    println(checkoutCart)
  }
}
