FROM openjdk:16-jdk-alpine

WORKDIR /home/auth
COPY ./build/libs/auth-0.0.1-SNAPSHOT.jar auth.jar

EXPOSE 9301

CMD java -jar auth.jar
