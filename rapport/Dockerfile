# Start with a base image
FROM openjdk:8

# Maintainer
LABEL maintainer="Aristide"

#Copy all target files
COPY target/rapport-0.0.1-SNAPSHOT.jar /home/oc/

EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/home/oc/rapport-0.0.1-SNAPSHOT"]