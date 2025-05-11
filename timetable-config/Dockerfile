FROM amazoncorretto:21
WORKDIR /app
COPY target/timetable-config-0.1-SNAPSHOT.jar timetable-config.jar

ENTRYPOINT ["java", "-jar", "timetable-config.jar"]