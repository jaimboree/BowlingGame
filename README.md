# BowlingGame Project

Implements a bowling game to tally an input of single rolls. Standard bowling game rules apply:
1. Each frame can have 1-2 rolls totalling at most 10 pins, with the exception of the last frame potentially have a third roll.
2. A bonus is included if the first roll is a strike or frame is a spare of next 2 or 1 rolls respectively

The code requires the number of pins in a roll must be non negative and less than the total pins allowed in a frame. In this game it is 10 pins and requires scores for 10 frames.
Any scores for frames over that will not be tallied.

# Importing

The project can be opened locally with an IDE such as IntelliJ or Eclipse. IntelliJ can be installed from:
https://www.jetbrains.com/idea/download/

The project contains the sbtplugin for eclipse so it can be imported as existing project. Eclipse can be installed from:

http://scala-ide.org/download/sdk.html


Java will need to be downloaded if not installed:
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

# Dependencies
The project is dependent on scala version 2.12.3. Needed dependencies are listed below and should be included with installation of sbt in next section.

	org.scala-lang.modules:scala-parser-combinators_2.12:1.0.4:jar
	org.scala-lang.modules:scala-xml_2.12:1.0.5:jar
	org.scala-lang:scala-library:2.12.3:jar
	org.scala-lang:scala-reflect:2.12.3:jar
	org.scalactic:scalactic_2.12:3.0.1:jar
	org.scalatest:scalatest_2.12:3.0.1:jar

# Building
The project uses sbt to build. SBT can be downloaded from:

http://www.scala-sbt.org/download.html

The build command will create an executable JAR file that will be found in "application/target/scala-2.12". Name and version can be modified in build.sbt. The project contains a plugin to sbt-assembly to create jar file with dependencies.

To clear existing jar then build with dependencies run the commands: 
	sbt>clean
	sbt>assembly


*The current jar file in the bundle was built without scala dependencies in the interest of reducing size. The assemblyOption line in build.sbt has been commented out so any subsequent local builds will include the scala dependencies above.

# Running the Application Locally
The jar can be executed with scala or java. You can execute the JAR file using either of the following commands:

	scala ./target/scala-2.12/BowlingGame-0.1.jar 'rolls input'

  	java -jar ./target/scala-2.12/BowlingGame-0.1.jar 'rolls input'
		- or if built without dependencies -
	java -cp "your_scala_library.jar" ./target/scala-2.12/BowlingGame-0.1.jar 'rolls input'
  

Where 'rolls input' as a white space delimited list of ints i.e.

	scala ./target/scala-2.12/BowlingGame-0.1.jar 10 10 10 10 10 10 10 10 10 10 10 10
	java -jar ./target/scala-2.12/BowlingGame-0.1.jar 10 10 10 10 10 10 10 10 10 10 10 10



# Testing
The unit testing can be found in BowlingGameSuite. This can be run locally with the command sbt>test
