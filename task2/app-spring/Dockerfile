FROM adoptopenjdk/openjdk14:alpine-slim as BUILD
COPY gradle /usr/src/app/gradle/
COPY gradlew /usr/src/app/
COPY settings.gradle  /usr/src/app/
COPY build.gradle  /usr/src/app/
WORKDIR /usr/src/app
RUN ./gradlew dependencies

COPY src /usr/src/app/src
WORKDIR /usr/src/app
RUN ./gradlew booJar

FROM adoptopenjdk/openjdk14:alpine-jre
COPY --from=BUILD /usr/src/app/build/libs/app.jar /app/app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
