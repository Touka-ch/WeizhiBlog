FROM openjdk:11-jre-slim
VOLUME /weizhiblog
ADD *.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8090
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]