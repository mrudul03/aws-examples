FROM openjdk:latest
WORKDIR /opt/springbootapp/
ADD target/ecs-customer-service-0.0.1-SNAPSHOT.jar /opt/springbootapp/ecs-customer-service.jar
RUN chmod +x ecs-customer-service.jar
CMD ["java", "-jar", "ecs-customer-service.jar"]
VOLUME /root/.m2
EXPOSE 9093
