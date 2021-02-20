FROM openjdk:14
VOLUME /tmp
EXPOSE 9090
COPY demo-dermasoft-1.0.0.jar /demo-dermasoft-1.0.0.jar
ENTRYPOINT ["java","-jar","demo-dermasoft-1.0.0.jar"]