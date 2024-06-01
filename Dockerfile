FROM ubuntu:latest
WORKDIR /java
ADD . /java
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV MAVEN_HOME /usr/share/maven
ENV PATH ${MAVEN_HOME}/bin:${PATH}
ENV TZ=America/Sao_Paulo
RUN apt-get update && apt-get install -y openjdk-21-jdk maven
RUN mvn clean package
CMD ["java", "-jar", "target/BancoDeSangue-1.0.0.jar"]