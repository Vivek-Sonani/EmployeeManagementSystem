# Employee Management System
 --> Docker compose file configuration
  
 employeemanagement:
    image: employeemanagementsystem:1.0.0
    container_name: employeemanagement
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://192.168.0.58:3306/empmanagement
      SPRING_DATASOURCE_USERNAME: vivek
      SPRING_DATASOURCE_PASSWORD: 'Admin-12345'
    ports:
      - "8080:8080"


commands: -->

--> Pulling the image
Obtaining Elasticsearch for Docker is as simple as issuing a docker pull command against the Elastic Docker registry.

  docker pull docker.elastic.co/elasticsearch/elasticsearch:7.5.2

--> Starting a single node cluster with Docker
To start a single-node Elasticsearch cluster for development or testing, specify single-node discovery to bypass the bootstrap checks:

  docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.5.2

--> Build and run docker images
  docker build -t employeemanagement:1.0.0 .
  docker run -p 8080:8080 employeemanagement:1.0.0


--> Creating .jar file
   ./mvnw clean package
