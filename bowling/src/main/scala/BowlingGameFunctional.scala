import scala.collection.mutable

class BowlingGameFunctional {

  val rolls = new Array[Int](21)
  var rollIndex = 0

  var frame = 1
  var frameIndex = 0

  var lastCountedRollIndex = 21
  val bonusFunctions = mutable.MutableList[() => Int]()

  def roll(pins : Int) = {
    rolls(rollIndex) = pins

    if (isStrike) bonusFunctions += countStrikeBonus(rollIndex)
    else if (isSpare) bonusFunctions += countSpareBonus(rollIndex)

    if (isComplete) {
      if (isStrike) frameIndex += 1 else frameIndex += 2
      if (isLastFrame) lastCountedRollIndex = frameIndex
      frame += 1
    }
    rollIndex += 1
  }

  def score = rolls.slice(0, lastCountedRollIndex).sum + bonusFunctions.map(_.apply).sum

  def isStrike = rolls(frameIndex) == 10 && frame < 11
  def isSpare = rolls(frameIndex) + rolls(frameIndex + 1) == 10 && frame < 11
  def isComplete = isStrike || rollIndex == frameIndex + 1
  def isLastFrame = frame == 10

  def countStrikeBonus(roll: Int) = () => { rolls(roll + 1) + rolls(roll + 2) }
  def countSpareBonus(roll: Int) = () =>  { rolls(roll + 1) }
}
