FROM maven:ibmjava-alpine

WORKDIR /usr/src/app

# copy over rest of project.
COPY . /usr/src/app/

RUN mvn install

ENTRYPOINT ["/usr/src/app/docker/entrypoint.sh"]
