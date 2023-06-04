FROM bellsoft/liberica-openjdk-alpine:17
COPY build/libs/tariff-management-app-spring-0.0.1.jar /app/

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/tariff-management-app-spring-0.0.1.jar"]
