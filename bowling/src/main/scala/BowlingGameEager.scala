
class BowlingGameEager {

  var score = 0
  var frame = 1

  var frameRoll = 0
  var frameScore = 0

  var bonusMultiplier = 0
  var nextBonusMultiplier= 0

  var countPinsIndicator = 1

  def roll(pins : Int) = {
    frameRoll += 1
    frameScore += pins

    score += pins * (countPinsIndicator + bonusMultiplier)

    bonusMultiplier = nextBonusMultiplier
    nextBonusMultiplier = 0

    if (isStrike || isSpare) bonusMultiplier += 1
    if (isStrike)            nextBonusMultiplier += 1

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
