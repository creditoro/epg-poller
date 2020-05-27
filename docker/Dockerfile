FROM maven:3.6.3-openjdk-11

WORKDIR /usr/src/app

ARG API_URL
ARG API_WEBURLTV2
ARG API_USER
ARG API_PASSWORD

COPY . /usr/src/app/

# Create the jar file
RUN API_URL=${API_URL} API_WEBURLTV2=${API_WEBURLTV2} API_USER=${API_USER} API_PASSWORD=${API_PASSWORD} mvn clean install

ENTRYPOINT ["java", "-jar target/epg_poller-0.0.1.jar"]
