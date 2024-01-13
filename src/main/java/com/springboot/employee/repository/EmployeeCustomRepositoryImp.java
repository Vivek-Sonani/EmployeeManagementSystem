//package com.springboot.employee.repository;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.springboot.employee.model.Employee;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//@Repository
//public class EmployeeCustomRepositoryImp implements EmployeeCustomRepository {
//	
//	@Autowired
//	private EntityManager entityManager;
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Employee> findByName(String firstname) {
//		// TODO Auto-generated method stub
//		CriteriaBuilder cb =  entityManager.getCriteriaBuilder();
//		CriteriaQuery cq = cb.createQuery();
//		
//		Root<Employee> 	employee = cq.from(Employee.class);
//		
//		Predicate firstnamePredicate = cb.equal(employee.get("firstname"), firstname);
//		
//		cq.where(firstnamePredicate);
//		
//		TypedQuery<Employee> query = entityManager.createQuery(cq);
//		
//		 
//		return query.getResultList();
//	}
//
//}
