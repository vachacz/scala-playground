# scala-playground

I use this repository to learn Scala programming language.

Classes
- **BowlingGameFrames**
 - First naive bowling game kata. It uses OO apprach. Game is divided into Frames. Rolls are passed to current Frame. Each Frame knows it's predecessor in order to pass bonus points back. 
 
 - Tests were written using ScalaTest and FunSuite. Before i learned basics of ScalaTest i played with Scala implicits and builder pattern here *BowlingGameFramesDummyTest*
 
- **BowlingGameEager**
 - Second attempt to bowling game kata. It uses eager approach. Rolls are not remembered anywhere. Instead score is computed on the fly. To make it possible presence of strike or spare bonus need to be remembered for future use. This approach is quite conciese but handling of last frame is a bit hacky.
 - Tests were rewritten using FlatSpec     
