package playground

import cats.Eval

object Playground {

  val someValue: Int = 45

  val someOtherValue: String = someValue match {
    case 1 => "one"
    case 2 => "two"
    case _ => "something else "
  }

  def main(args: Array[String]): Unit = {
    println("Hello...")
  }
}
