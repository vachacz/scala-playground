
class BowlingGameEager {

  var score = 0
  var frame = 1

  var frameRoll = 0
  var frameScore = 0

  var rollBonus = 0
  var nextRollBonus = 0

  var countPinsIndicator = 1

  def roll(pins : Int) = {
    frameRoll += 1
    frameScore += pins

    score += pins * (countPinsIndicator + rollBonus)

    rollBonus = nextRollBonus
    nextRollBonus = 0

    if (isStrike || isSpare) rollBonus += 1
    if (isStrike)            nextRollBonus += 1

    if (isFrameComplete) {
      if (isLastFrame) countPinsIndicator = 0
      frame += 1
      frameRoll = 0
      frameScore = 0
    }
  }

  def isStrike = frameRoll == 1 && frameScore == 10 && frame < 11
  def isSpare = frameRoll == 2 && frameScore == 10 && frame < 11
  def isFrameComplete = isStrike || frameRoll == 2
  def isLastFrame = frame == 10
}
