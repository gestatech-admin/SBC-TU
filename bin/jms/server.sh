mvn -f $(dirname $0)./../pom.xml install
mvn -f $(dirname $0)./../pom.xml exec:java -Pjms-server