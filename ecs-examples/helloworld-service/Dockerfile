FROM openjdk:latest
VOLUME /root/.m2
EXPOSE 9099
WORKDIR /agb/
ADD target/helloworld-service-0.0.1-SNAPSHOT.jar /agb/helloworld-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-jar","/agb/helloworld-service.jar"]