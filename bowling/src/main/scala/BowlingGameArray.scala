
class BowlingGameArray {

  val rolls = new Array[Int](21)
  var bonusFunctions = List[() => Int]()
  var currentRoll = 0
  var frameIndex = 0

  def roll(pins : Int) = {
    rolls(currentRoll) = pins

    if (isStrike) {
      bonusFunctions = bonusFunctions :+ countStrikeBonus(currentRoll)_
      frameIndex += 1
    }
    else if (isSpare) {
      bonusFunctions = bonusFunctions :+ countSpareBonus(currentRoll)_
      frameIndex += 2
    }
    else {
      frameIndex += 2
    }

    currentRoll += 1
  }

  def score = rolls.toList.sum + bonusFunctions.map(_.apply).sum

  def isStrike = rolls(frameIndex) == 10
  def isSpare = !isStrike && rolls(frameIndex) + rolls(frameIndex + 1) == 10

  def countStrikeBonus(roll: Int)() = rolls(roll + 1) + rolls(roll + 2)
  def countSpareBonus(roll: Int)() = rolls(roll + 1)
}
