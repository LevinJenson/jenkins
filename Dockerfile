FROM openjdk:8
EXPOSE 8080
ADD target/Ajio-clone-devops.jar Ajio-clone-devops.jar
ENTRYPOINT [ "java","-jar","/Ajio-clone-devops.jar" ]