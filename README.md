#Employee Management System
 --> Docker compose file configuration
  
 employeemanagement:
    image: employeemanagementsystem:1.0.0
    container_name: employeemanagement
#    networks:
#     my_network:
#        ipv4_address: 10.5.0.1

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


--> Run new Version of elasticsearch
docker run --rm --name elastic --network host -p 9200:9200 -p 9300:9300 -m 1GB -e "discovery.type=single-node"  -e "xpack.security.enabled=false"  docker.elastic.co/elasticsearch/elasticsearch:8.12.0

--> Creating .jar file
 ./mvnw clean package
 
--> Build and run docker images
docker build -t employeemanagement:1.0.0 .
docker run -p 8080:8080 employeemanagement:1.0.0

--> run code locally
 ./mvnw spring-boot:run


 
 <!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-elasticsearch -->
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-elasticsearch</artifactId>
    <version>5.2.2</version>
</dependency>
