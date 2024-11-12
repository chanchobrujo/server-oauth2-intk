FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/server-oauth2-intk-0.1.jar server-oauth2-intk-0.1.jar
ENTRYPOINT ["java", "-jar", "/server-oauth2-intk-0.1.jar"]