FROM openjdk:11-slim-buster as build

COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw
COPY pom.xml .
COPY src src

RUN sed -i 's/\r$//' mvnw
RUN --mount=type=cache,target=/root/.m2,rw ./mvnw -B clean package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim-buster

COPY --from=build ./target/bless-my-soul-backend-0.0.1-SNAPSHOT.jar /usr/app/bless-my-soul-backend-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/usr/app/bless-my-soul-backend-0.0.1-SNAPSHOT.jar"]
