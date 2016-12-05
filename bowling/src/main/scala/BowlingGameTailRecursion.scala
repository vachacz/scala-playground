
class BowlingGameTailRecursion {

  var rolls: List[Int] = List()

  def roll(pins : Int) = rolls = rolls :+ pins
  def score = scoreRecursive(0, rolls)

  def scoreRecursive(score: Int, rolls: List[Int]) : Int = {
    rolls match{
      case first :: remainingRolls if first == 10
        => scoreRecursive(score + 10 + remainingRolls.take(2).sum, remainingRolls)
      case first :: second :: remainingRolls if first + second == 10
        => scoreRecursive(score + 10 + remainingRolls.take(1).sum, remainingRolls)
      case first :: second :: remainingRolls
        => scoreRecursive(score + first + second, remainingRolls)
      case first :: remainingRolls
        => scoreRecursive(score + first, remainingRolls)
      case _ => score
    }
  }
}