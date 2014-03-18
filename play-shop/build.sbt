name := """play-shop"""

version := "1.0-SNAPSHOT"

resolvers += "Scala tools snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Scala tools releases"  at "https://oss.sonatype.org/content/groups/scala-tools"

libraryDependencies ++= Seq(
  // Select Play modules
  //jdbc,      // The JDBC connection pool and the play.api.db API
  //anorm,     // Scala RDBMS Library
  //javaJdbc,  // Java database API
  //javaEbean, // Java Ebean plugin
  //javaJpa,   // Java JPA plugin
  //filters,   // A set of built-in filters
  javaCore,  // The core Java API
  // WebJars pull in client-side web libraries
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars" % "bootstrap" % "2.3.1",
  //Pull in the barcode generator
  "net.sf.barcode4j" % "barcode4j" % "2.0",
  //Pull in the mongo support.
  "org.mongodb" %% "casbah" % "2.7.0-RC2"
  // Add your own project dependencies in the form:
  // "group" % "artifact" % "version"
)

play.Project.playScalaSettings
