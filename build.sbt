name := "BowlingGame"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

mainClass in Compile := Some("bowling.Main")

mainClass in assembly := Some("bowling.Main")
assemblyJarName in assembly := s"${name.value}-${version.value}.jar"

exportJars := true
//assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
