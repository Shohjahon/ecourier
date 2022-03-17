FROM adoptopenjdk:11-jre-hotspot
LABEL maintainer="r.shohjahon@gmail.com"
ADD target/ecourier-1.0.0.jar ecourier-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecourier-1.0.0.jar"]
