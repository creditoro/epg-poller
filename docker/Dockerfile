FROM maven:3.6.3-openjdk-11

ARG API_URL
ARG API_WEBURLTV2
ARG API_USER
ARG API_PASSWORD

WORKDIR /usr/src/app

# copy over rest of project.
COPY . /usr/src/app/

RUN API_URL=${API_URL} API_WEBURLTV2=${API_WEBURLTV2} API_USER=${API_USER} API_PASSWORD=${API_PASSWORD} mvn clean install

ENTRYPOINT ["/usr/src/app/docker/entrypoint.sh"]
