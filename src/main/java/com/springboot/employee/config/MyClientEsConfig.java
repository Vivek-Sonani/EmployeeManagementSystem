package com.springboot.employee.config;

import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.transport.Header;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


import co.elastic.clients.elasticsearch.ElasticsearchClient;

@Configuration
@EnableElasticsearchRepositories
@EnableElasticsearchAuditing
public class MyClientEsConfig extends ElasticsearchConfiguration {

	@Bean
	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration
				.builder()
				.connectedTo("localhost:9200")
				.build();
	}
	
//	@Bean
//	public ElasticsearchClient elasticsearchClient() {
//	RestClient restClient = RestClient.builder(
//		    new HttpHost("localhost", 9200, "http"),
//		    new HttpHost("localhost", 9201, "http")).build();
//
//		// Create the transport with a Jackson mapper
//		co.elastic.clients.transport.ElasticsearchTransport transport = new co.elastic.clients.transport.rest_client.RestClientTransport(
//		    restClient, new co.elastic.clients.json.jackson.JacksonJsonpMapper());
//
//		// And create the API client
//		ElasticsearchClient esClient = new ElasticsearchClient(transport);
//		return esClient;
//	}
	//ElasticsearchClient elasticsearchClient
	// @SuppressWarnings("deprecation")
//	@Bean
//	    public RestHighLevelClient client() {
//	        ClientConfiguration clientConfiguration 
//	            = ClientConfiguration.builder()
//	                .connectedTo("localhost:9200")
//	                .build();
//	 
//	        return RestClients.create(clientConfiguration).rest();
//	    }
//	 
//	    @Bean
//	    public ElasticsearchOperations elasticsearchTemplate() {
//	        return new ElasticsearchRestTemplate(client());
//	    }

}
