package com.springboot.employee.specification;

import org.springframework.data.jpa.domain.Specification;

import com.springboot.employee.model.Employee;
import com.springboot.employee.model.Employee_;

public class EmployeeSpecification {

	public static Specification<Employee> hasFirstname(String firstname) { // why static because this method called like
																			// classname.methodName ,
																			// EmployeeSpecification.hasFirstname
		return ((root, Query, builder) -> {
			Query.distinct(true);
			System.out.println("running.......");// distinct value we get
			return builder.equal(root.get(Employee_.firstname), firstname);
		});
	}

	public static Specification<Employee> hasPosition(String position) {
		return ((root, CriteriaQuery, CriteriaBuilder) -> {
			CriteriaQuery.distinct(true);
			System.out.println("position");
			return CriteriaBuilder.equal(root.get(Employee_.position), position);
		});
	}
}
//	GET index/_search
//	{
//	  "query": {
//	    "bool": {
//	      "should": [
//	        {
//	          "bool": {
//	            "must": [
//	              {
//	                "term": {
//	                  "field1": {
//	                    "value": "value1"
//	                  }
//	                }
//	              },
//	              {
//	                "term": {
//	                  "field2": {
//	                    "value": "value2"
//	                  }
//	                }
//	              }
//	            ]
//	          }
//	        },
//	        {
//	          "bool": {
//	            "must": [
//	              {
//	                "term": {
//	                  "field1": {
//	                    "value": "value3"
//	                  }
//	                }
//	              }
//	            ],
//	            "must_not": [
//	              {
//	                "term": {
//	                  "field2": {
//	                    "value": "value3"
//	                  }
//	                  }
//	              }
//	            ]
//	          }
//	        }
//	      ]
//	    }
//	  }
//	}

//	BoolQueryBuilder outerBoolQuery = QueryBuilders.boolQuery()
//	        .should(
//	                QueryBuilders.boolQuery()
//	                        .must(QueryBuilders.termQuery("field1", "value1"))
//	                        .must(QueryBuilders.termQuery("field2", "value2"))
//	        )
//	        .should(
//	                QueryBuilders.boolQuery()
//	                        .must(QueryBuilders.termQuery("field1", "value3"))
//	                        .mustNot(QueryBuilders.termQuery("field2", "value3"))
//	        );
