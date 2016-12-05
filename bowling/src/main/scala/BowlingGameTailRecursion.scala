
class BowlingGameTailRecursion {

  var rolls: List[Int] = List()

  def roll(pins : Int) = rolls = rolls :+ pins
  def score = scoreRecursive(0, 1, rolls)

  def scoreRecursive(score: Int, frame: Int, rolls: List[Int]) : Int = {
    (frame, rolls) match {
      case (11, _)
        => score
      case (_, first :: remainingRolls) if first == 10
        => scoreRecursive(score + 10 + remainingRolls.take(2).sum, frame + 1, remainingRolls)
      case (_, first :: second :: remainingRolls) if first + second == 10
        => scoreRecursive(score + 10 + remainingRolls.head, frame + 1, remainingRolls)
      case (_, first :: second :: remainingRolls)
        => scoreRecursive(score + first + second, frame + 1, remainingRolls)
      case (_, first :: remainingRolls)
        => scoreRecursive(score + first, frame + 1, remainingRolls)
      case _ => score
    }
  }
}