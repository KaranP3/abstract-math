package part1intro

object TCVariance {

  import cats.Eq
  import cats.instances.int._
  import cats.instances.option._
  import cats.syntax.eq._

  val aComparison = Option(2) === Option(3)

  //  val anInvalidComparison = Some(2) === None

  // variance
  class Animal
  class Cat extends Animal

  // covariant type - subtyping is propagated to the generic type
  class Cage[+T]
  val cage: Cage[Animal] = new Cage[Cat] // Cat <: Animal, so Cage[Cat] <: Cage[Animal]

  // contravariant type - subtyping is propagated backwards to the generic type
  class Vet[-T]
  val vet: Vet[Cat] = new Vet[Animal] // Cat <: Animal, then Vet[Animal] <: Vet[Cat]

  // rule of thumb: if a generic type "has a T" = covariant, "Acts on T" = contravariant
  // variance effects how TC instances are going to be fetched

  // contravariant TC
  trait SoundMaker[-T]
  implicit object AnimalSoundMaker extends SoundMaker[Animal]
  def makeSound[T](implicit soundMaker: SoundMaker[T]): Unit = println("sound...")
  makeSound[Animal]
  makeSound[Cat]
  // rule 1: contravariant TC's can use the superclass if nothing is available
  // strictly for that type

  implicit object OptionSoundMaker extends SoundMaker[Option[Int]]
  makeSound[Option[Int]]
  makeSound[Some[Int]]

  // Covariant TC
  trait AnimalShow[+T]{
    def show: String
  }

  implicit object GeneralAnimalShow extends AnimalShow[Animal] {
    override def show: String = "animals everywhere..."
  }

  implicit object CatShow extends AnimalShow[Cat] {
    override def show: String = "cats everywhere..."
  }

  def organiseShow[T](implicit event: AnimalShow[T]): String = event.show
  // rule 2: covariant TC's will always use the more specific TC instance for that type
  // but may confuse the compiler if the general TC is also present in scope

  println(organiseShow[Cat]) // the compiler will inject cats show as implicit

  // rule 3: you can't have both benefits
  // Cats uses Invariant TC's

  def main(args: Array[String]): Unit = {
    println(organiseShow[Cat])
    //    println(organiseShow[Animal]) // this will not compile
  }
}
