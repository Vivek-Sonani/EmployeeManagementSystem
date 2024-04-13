FROM openjdk:17
ADD target/EmployeeManagementSystem-0.0.1-SNAPSHOT.jar EmployeeManagementSystem-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","EmployeeManagementSystem-0.0.1-SNAPSHOT.jar" ]