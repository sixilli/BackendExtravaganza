lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  //.enablePlugins(ScalikejdbcPlugin)
  .settings(
    name := """scala-backend""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.4",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
	    "org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
	    "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
      "org.scalikejdbc" %% "scalikejdbc-config" % "3.5.0",
      "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5",
      "io.lemonlabs" %% "scala-uri" % "1.5.1",
      "net.codingwell" %% "scala-guice" % "4.2.6",
      "net.logstash.logback" % "logstash-logback-encoder" % "6.2",
      "com.github.tototoshi" %% "play-json-naming" % "1.5.0"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    ),
    initialCommands := """
      import scalikejdbc._, config._
      import models._, utils._
      DBs.setupAll
      DBInitializer.run()
      implicit val autoSession = AutoSession
    """,
  )

PlayKeys.devSettings := Seq("play.server.http.port" -> "12082")