FROM maven:3.6.3-openjdk-15

WORKDIR /usr/src/app

# copy over rest of project.
COPY . /usr/src/app/

RUN mvn compile
RUN mvn install -e

ENTRYPOINT ["/usr/src/app/docker/entrypoint.sh"]
