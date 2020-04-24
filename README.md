# EPG-poller
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=creditoro_epg-poller&metric=alert_status)](https://sonarcloud.io/dashboard?id=creditoro_epg-poller)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=creditoro_epg-poller&metric=coverage)](https://sonarcloud.io/dashboard?id=creditoro_epg-poller)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=creditoro_epg-poller&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=creditoro_epg-poller)
EPG Poller for creditoro. this is use for polling data from tvtid.tv2.dk and use the creditoro REST epg-poller to put data into the database.


### How do I compile?
In order to compile the program you need to have [Maven](https://maven.apache.org/install.html) installed and Java JDK version 13 (either [Oracle's JDK 13](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html) or [OpenJDK 13](https://openjdk.java.net/projects/jdk/13/))

`mvn package --file pom.xml`

### How do I run the program?
`java -jar /path/to/program.jar`
