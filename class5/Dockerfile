FROM openjdk:11 as BUILD
COPY gradle /usr/src/app/gradle/
COPY gradlew /usr/src/app/
COPY settings.gradle  /usr/src/app/
COPY service/build.gradle  /usr/src/app/service/
WORKDIR /usr/src/app
RUN ./gradlew dependencies

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN ./gradlew quarkusBuild

FROM openjdk:11
COPY --from=BUILD /usr/src/app/service/build/lib /app/lib/
COPY --from=BUILD /usr/src/app/service/build/service-unspecified-runner.jar /app/app.jar
EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
