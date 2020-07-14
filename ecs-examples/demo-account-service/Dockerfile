FROM openjdk:latest
WORKDIR /opt/springbootapp/
ADD target/demo-account-service-0.0.1-SNAPSHOT.jar /opt/springbootapp/demo-account-service.jar
RUN chmod +x demo-account-service.jar
CMD ["java", "-jar", "demo-account-service.jar"]
VOLUME /root/.m2
EXPOSE 9092
