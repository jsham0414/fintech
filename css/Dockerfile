FROM openjdk:11
RUN mkdir -p deploy
WORKDIR /deploy
COPY ./build/libs/css-0.0.1.jar css.jar
EXPOSE 8081:8081
ENTRYPOINT ["java", "-jar", "/deploy/css.jar"]