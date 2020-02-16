FROM maven:alpine

ARG APP_DIR=app
WORKDIR ${APP_DIR}
COPY . .

RUN mvn clean install

EXPOSE 80 8080 8081

RUN ls
RUN pwd

ENTRYPOINT ["java"]
CMD ["-jar", "./target/survey-service-0.0.0-SNAPSHOT.jar"]