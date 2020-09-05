# Start with a base image
FROM openjdk:8

# Maintainer
LABEL maintainer="Aristide"

#Copy all target files
COPY target/patient-0.0.1-SNAPSHOT.jar /home/oc/

EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java","-jar","/home/oc/patient-0.0.1-SNAPSHOT"]