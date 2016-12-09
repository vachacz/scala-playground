package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = (c, r) match {
    case (0, _) => 1
    case (x, y) if x == y => 1
    case (x, y) => pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char], bracesOpen: Int = 0): Boolean = chars match {
    case Nil => bracesOpen == 0
    case '(' :: tail => balance(tail, bracesOpen + 1)
    case ')' :: tail if bracesOpen > 0 => balance(tail, bracesOpen - 1)
    case ')' :: tail => false
    case _ :: tail  => balance(tail, bracesOpen)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty || money == 0) return 0

    def _count(money: Int, coins: List[Int]): Int = (money, coins) match {
      case (0, _) => 1
      case (_, Nil) => 0
      case (m, coin :: tail) if m >= coin => _count(m - coin, coins) + _count(m, tail)
      case _ => 0
    }

    _count(money, coins.sortWith(_ < _))
  }
}
