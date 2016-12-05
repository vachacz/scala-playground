
class BowlingGameTailRecursion {

  var rolls: List[Int] = List()

  def roll(pins : Int) = rolls = rolls :+ pins
  def score = scoreRecursive(0, 1, rolls)

  def scoreRecursive(score: Int, frame: Int, rolls: List[Int]) : Int = {
    (frame, rolls) match {
      case (11, _) => score
      case (_, 10 :: tail)
        => scoreRecursive(score + 10 + tail.take(2).sum, frame + 1, tail)
      case (_, first :: second :: tail) if first + second == 10
        => scoreRecursive(score + 10 + tail.head, frame + 1, tail)
      case (_, first :: second :: tail)
        => scoreRecursive(score + first + second, frame + 1, tail)
      case (_, first :: tail)
        => scoreRecursive(score + first, frame + 1, tail)
      case _ => score
    }
  }
}