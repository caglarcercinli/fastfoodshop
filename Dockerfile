FROM openjdk:11
COPY target/fastfoodshop-0.0.1-SNAPSHOT.jar fastfoodshop-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","fastfoodshop-0.0.1-SNAPSHOT.jar"]