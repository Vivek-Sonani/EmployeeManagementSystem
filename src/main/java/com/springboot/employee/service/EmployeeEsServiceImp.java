package com.springboot.employee.service;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.springboot.employee.event.EmployeeCreatedEvent;
import com.springboot.employee.event.EmployeeDeletedEvent;
import com.springboot.employee.event.EmployeeUpdatedEvent;
import com.springboot.employee.event.FamilyInfoCreatedEvent;
import com.springboot.employee.event.FamilyInfoDeletedEvent;
import com.springboot.employee.event.FamilyInfoUpdatedEvent;
import com.springboot.employee.model.Employee;
import com.springboot.employee.model.Employeedocument;
import com.springboot.employee.repository.EmployeeEsRepository;
import com.springboot.employee.repository.EmployeeRepository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery.Builder;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.util.ObjectBuilder;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.search.SearchHit;
import java.io.IOException;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class EmployeeEsServiceImp implements EmployeeEsService {
	@Autowired
	private EmployeeEsRepository employeeEsRepository;

	@Autowired
	private AddressEsService addressEsService;

	@Autowired
	private EmployeeRepository employeeRepository;

	// @Autowired
	// ApplicationEventPublisherAware applicationEventPublisherAware;

	@Autowired
	private ElasticsearchClient elasticsearchClient;

//	@Autowired
//	private ClientConfiguration clientConfiguration;

	// @Autowired
	// private RestHighLevelClient client;

	@Autowired
	private RestClient restClient;

//	@Autowired
//	private ElasticsearchTransport transport;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeEsServiceImp.class);

	@Override
	public void getEsEmployee(Integer id) {
		index(id);
	}

	@Override
	public void index(Integer emplyeeId) {
		// Read employee by id from Sql DB.
		logger.info("starting index method:");
		Employee employee = employeeRepository.findById(emplyeeId).get();
		// Transform sql employee to ES employee
		Employeedocument employeedocument = new Employeedocument(employee);
		// save es employee using employeeEsRepo
		employeeEsRepository.save(employeedocument);
		logger.info("end of index method");
	}

	@Override
	public void deleteEsEmployee(Integer id) {
		employeeEsRepository.deleteById(id);
	}

	@Override
	public Iterable<Employeedocument> getAllEsEmployee() {
		// event)
		// Employeedocument Employeedocument = new Employeedocument();
		Iterable<Employeedocument> Employeedocument = employeeEsRepository.findAll();
		return Employeedocument;
	}

	@Override
	public Employeedocument updateEmployee(Integer id) {
		logger.info("starting updateEmloyee method:");
		Employee employee = employeeRepository.findById(id).get();
		// Transform sql employee to ES employee
		Employeedocument employeedocument = new Employeedocument(employee);
		// save es employee using employeeEsRepo
		logger.info("end of updateEmployee method");
		return employeeEsRepository.save(employeedocument);
	}

	@Override
	public List<Employeedocument> searchEmployee(String text) {
		List<Employeedocument> results = new ArrayList<>();

//			SearchResponse<Employeedocument> search = elasticsearchClient.search(s -> {
//				return s.index("employee_index")
//						.query(q -> q
//								.bool(bool -> bool.should(QueryBuilders.queryStringQuery(text))
//										.should(QueryBuilders.nestedQuery("nestedField", QueryBuilders.boolQuery()
//												.should(QueryBuilders.matchQuery("nestedField.field1", text))
//												.should(QueryBuilders.matchQuery("nestedField.field2", text))))));
//			}, Employeedocument.class, RequestOptions.DEFAULT);
		// boolQuery().must(termQuery("tags", "elasticsearch"));

//			SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//            sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("middlename", text)));

		try {
			SearchResponse<Employeedocument> response = elasticsearchClient.search(
					s -> s.index("employee_index").query(q -> q.match(t -> t.field("middlename").query(text))),
					Employeedocument.class);

			// BoolQueryBuilder first =
			// QueryBuilders.boolQuery().must(QueryBuilders.termQuery("middlename", text));
//			SearchResponse<Employeedocument> response = elasticsearchClient.search(
//					s -> s.index("employee_index").query(q -> q.queryString(t -> t.query(text + "*"))
//							),
//					Employeedocument.class);
//			final QueryBuilder secondNestedQuery = QueryBuilders.nestedQuery(
//				     "familyInfo",
//				     QueryBuilders.queryStringQuery(text), null
//				         );
//			final QueryBuilder queryBuilder = QueryBuilders.boolQuery()
//				     .must(first)
//				     .must(secondNestedQuery);
			for (Hit<Employeedocument> hit : response.hits().hits()) {
				results.add(hit.source());
			}
		} catch (ElasticsearchException | IOException e) {
			e.printStackTrace();
		}
//			 BoolQueryBuilder first = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("middlename", text));
//	            BoolQueryBuilder secondNestedQuery = QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(text));
//	            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(first).must(QueryBuilders.nestedQuery("familyInfo", secondNestedQuery, null));
//
//	            // Building the search source
//	            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//	            sourceBuilder.query(queryBuilder);
//
//	            // Executing the search request
//	            SearchResponse response = elasticsearchClient.search(sourceBuilder.index("employee_index"), Employeedocument.class);
//
//	            // Processing the search hits
//	            for (SearchHit hit : response.getHits()) {
//	                Employeedocument employeeDocument = hit.getSourceAsObject(Employeedocument.class);
//	                results.add(employeeDocument);
//	            }
//		
		return results;
	}

	@Override
	public List<Employeedocument> searchByFirstnameAndRelationship(String firstname, String relationship) {
		List<Employeedocument> results = new ArrayList<>();
		try {
			Query byFirstname = MatchQuery.of(m -> m 
				    .field("firstname")
				    .query(firstname)
				)._toQuery(); 
			logger.info("byFirstname" + byFirstname);

			Query byRelationship = MatchQuery.of(r -> r
				    .field("familyInfo.relationship")
				    .query(relationship)
				)._toQuery();
			logger.info("byRelationship" + byRelationship);

			Query nested = NestedQuery.of(n -> n
					.path("familyInfo")
					.query(byRelationship))._toQuery();
			
		//	MultiMatchQuery.of(null)
//			MultiMatchQuery.of(m -> ms
//					.fields(results))._toQuery();

			SearchResponse<Employeedocument> response = elasticsearchClient.search(
					                                    s -> s.index("employee_index")
					                                    .query(q->q
					                                    		.bool(b->b
					                                    				.must(byFirstname)
					                                    				.must(nested)
					                                    				)
					                                    				),
					                                    Employeedocument.class
					                                    );
															
			for (Hit<Employeedocument> hit : response.hits().hits()) {
				results.add(hit.source());
			}
		} catch (ElasticsearchException | IOException e) {
			e.printStackTrace();
		}
		logger.info("Elasticsearch" + results);
		   return results;
	}

	// TODO method1 : @EventListner void onEmployeeCreated(EmployeeCreatedEvent event)
	// - index(event.getId());
	@EventListener
	public void onEmployeeCreated(EmployeeCreatedEvent event) {
		Integer employeeId = event.getEmployeeId();
		index(employeeId);
		logger.info("successfull....");
	}

	// print success log
	// TODO method2 : onEmployeeUpdated application event
	@EventListener
	public void onEmployeeUpdated(EmployeeUpdatedEvent event) {
		Integer employeeId = event.getEmployeeId();
		updateEmployee(employeeId);
		logger.info("successfull....");
	}

	// TODO: method3 : onEMployeeDeleted
	// delete()
	@EventListener
	public void onEmployeeDeleted(EmployeeDeletedEvent event) {
		Integer employeeId = event.getEmployeeId();
		deleteEsEmployee(employeeId);
		logger.info("successfull....");
	}

	@EventListener
	public void onFamilyInfoCreated(FamilyInfoCreatedEvent event) {
		Integer employeeId = event.getEmployeeId();
		index(employeeId);
		logger.info("successfull....");
	}

	@EventListener
	public void onFamilyInfoUpdated(FamilyInfoUpdatedEvent event) {
		Integer id = event.getEmployeeId();
		index(id);
		logger.info("successfull...");

	}

	@EventListener
	public void onFamilyInfoDeleted(FamilyInfoDeletedEvent event) {
		Integer id = event.getEmployeeId();
		index(id);
		logger.info("successfull...");
	}

}
