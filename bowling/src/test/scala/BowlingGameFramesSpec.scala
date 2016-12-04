import org.scalatest.{BeforeAndAfterEach, FunSuite, Matchers}

class BowlingGameFramesSpec extends FunSuite with Matchers with BeforeAndAfterEach {

  var game : BowlingGameFrames = _

  override def beforeEach() {
    game = new BowlingGameFrames
  }

  test("should score zero before the game starts") {
    game.score should equal(0)
  }

  test("should roll 3 once") {
    game.roll(3)
    game.score should equal(3)
  }

  test("should roll twice") {
    game.roll(3)
    game.roll(3)
    game.score should equal(6)
  }

  test("should roll in the second frame") {
    game.roll(3)
    game.roll(3)
    game.roll(3)
    game.score should equal(9)
  }

  test("should roll strike and calculate bonus from first shot after strike") {
    game.roll(10)
    game.roll(3)
    game.score should equal(16)
  }

  test("should roll strike and calculate bonus from first and second shot after strike") {
    game.roll(10)
    game.roll(3)
    game.roll(3)
    game.score should equal(22)
  }

  test("should not calculate bonus for the third shot after strike") {
    game.roll(10)
    game.roll(3)
    game.roll(3)
    game.roll(1)
    game.score should equal(23)
  }

  test("should roll spare and calculate bonus from first shot after spare") {
    game.roll(5)
    game.roll(5)
    game.roll(3)
    game.score should equal(16)
  }

  test("should not calculate bonus for the second shot after spare") {
    game.roll(5)
    game.roll(5)
    game.roll(3)
    game.roll(2)
    game.score should equal(18)
  }

  test("should roll strike twice in a row and non strike after") {
    game.roll(10)
    game.roll(10)
    game.roll(4)
    game.score should equal(24+14+4)
  }

  test("should roll 10 x strikes") {
    for (i <- 1 to 10) game.roll(10)
    game.score should equal(270)
  }

  test("should roll 10 x strikes and twice 10 as a bonus") {
    for (i <- 1 to 10) game.roll(10)
    game.roll(10)
    game.roll(10)
    game.score should equal(300)
  }

  test("should roll spare )the last round") {
    for (i <- 1 to 9) game.roll(10)
    game.roll(6)
    game.roll(4)
    game.roll(1)
    game.score should equal(267)
  }

  test("should roll normal last frame") {
    for (i <- 1 to 9) game.roll(10)
    game.roll(6)
    game.roll(3)
    game.score should equal(264)
  }

}
