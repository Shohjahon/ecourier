FROM adoptopenjdk:11-jre-hotspot
LABEL maintainer="r.shohjahon@gmail.com"
COPY target/ecourier-1.0.1.jar ecourier-1.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecourier-1.0.1.jar"]
