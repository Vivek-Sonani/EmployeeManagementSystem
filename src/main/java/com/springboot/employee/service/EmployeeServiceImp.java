package com.springboot.employee.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.employee.model.Address;
import com.springboot.employee.model.Employee;
import com.springboot.employee.model.Employee_;
import com.springboot.employee.repository.AddressRepository;
import com.springboot.employee.repository.EmployeeRepository;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class EmployeeServiceImp implements EmployeeService {
	// List<Employee> list = new ArrayList<Employee>();
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	// @Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ObjectMapper mapper;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> list = employeeRepository.findAll();
		System.out.println("get all employee" + list.toString());
		return list.stream().map(e -> dozerBeanMapper.map(e, Employee.class, "employee-full"))
				.collect(Collectors.toList());
	}

	@Override
	public Employee getEmployee(Integer id) {
		// TODO Auto-generated method stub
		// Employee optionalEmployee =
		// list.stream().filter((emp)->emp.getEmployId().equals(id)).findFirst();
		java.util.Optional<Employee> e = employeeRepository.findById(id);
		if (e.isPresent()) {
			Employee emp = e.get();
			System.out.println(emp);
			// Address add = addressService.getAddress(emp.getAddress());
			// emp.setAddress(add);
			// System.out.println(emp.toString());
			Employee destionation = dozerBeanMapper.map(emp, Employee.class, "employee-full");
			// System.out.println(destionation);
			System.out.println(destionation.getEmployId() + " " + destionation.getFirstname() + " "
					+ destionation.getContactNo() + " " + destionation.getLastname() + " " + destionation.getEmail());
			// destionation.setLastname(destionation.getLastname());
			// System.out.println(emp.getAddress());
			// System.out.println(destionation.getLastname());
			return destionation;
		} else {
			return (Employee) Optional.empty().get();
		}

	}

	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee savedEmployee = employeeRepository.save(employee);
		System.out.println("savedEmployee : " + savedEmployee);
		Address requestedAddress = employee.getAddress();
		// List<FamilyInfo> f = e.getFamilyInfo();
		System.out.println(requestedAddress);
		requestedAddress.setEmployee(savedEmployee); // set employee id to address table
		System.out.println("reuqestAddress : " + requestedAddress);

		Address savedAddress = addressService.addAddress(requestedAddress);
		System.out.println("savedAddress : " + savedAddress);
		savedEmployee.setAddress(savedAddress);
		System.out.println("add employee ->" + savedEmployee);

		// List<FamilyInfo> info = familyInfoService.addInfo(f);
		// e.setFamilyInfo(info);
		return dozerBeanMapper.map(savedEmployee, Employee.class, "employee-full");
	}

	@Override
	public void deleteEmployee(Integer id) {
		// TODO Auto-generated method stub
		java.util.Optional<Employee> e = employeeRepository.findById(id);
		if (e.isPresent()) {
			Employee emp = e.get();
			addressService.removeAddress(emp.getAddress());
			employeeRepository.deleteById(id);
		} else {
			System.out.println("Empoyee not found");
		}

	}

	private Address manageAdderess(Address requestAddress, Address dbAddress, Employee emp) {
		if (requestAddress != null && dbAddress != null) {
			// requestAddress.setEmployee(emp);
			System.out.println(requestAddress);
			requestAddress.setId(dbAddress.getId());
			System.out.println(requestAddress);
			Address add = addressService.updateAddress(requestAddress);
			System.out.println("not null");
			return add;
		} else if (requestAddress == null && dbAddress != null) {
			addressService.removeAddress(dbAddress);
			System.out.println("add when there is no address");
		} else {
			requestAddress.setEmployee(emp);
			// requestAddress.setId(dbAddress.getId());
			Address add = addressService.addAddress(requestAddress);
			System.out.println("when there is no address");
			return add;
		}
		return null;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		System.out.println("starting...");
		Optional<Employee> e = employeeRepository.findById(employee.getEmployId());
		// Employee updatedEmp = employeeRepository.getOne(employee.getEmployId());
		if (!e.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			Employee emp = e.get();
			emp.setFirstname(employee.getFirstname());
			emp.setMiddlename(employee.getMiddlename());
			emp.setLastname(employee.getLastname());
			emp.setContactNo(employee.getContactNo());
			emp.setPosition(employee.getPosition());
			emp.setEmail(employee.getEmail());
			emp.setSalary(employee.getSalary());
			Address dbAddress = emp.getAddress();
			Address requestAddress = employee.getAddress();

			Address add = manageAdderess(requestAddress, dbAddress, emp);
			System.out.println("After ManageAddress : " + add);
			// add.setEmployee(employee);
			emp.setAddress(add);

			// String loggedInUsername =
			// SecurityContextHolder.getContext().getAuthentication().getName();
			// System.out.println(loggedInUsername);
			// emp.setModifiedBy(loggedInUsername);

			System.out.println("update all");
			Employee e1 = employeeRepository.save(emp);
			entityManager.flush(); // forcefully add to database, Synchronize the persistence context to the
									// underlying database.
			return dozerBeanMapper.map(e1, Employee.class);
		}
	}

	@Override
	public void deleteEmployeesByPosition(String position) {
		// TODO Auto-generated method stub
		employeeRepository.deleteByLastName(position);
	}

	@Override
	public List<Employee> getEmployeeByFirstName(String firstname) {
		// TODO Auto-generated method stub
		List<Employee> b = employeeRepository.getEmployeeByFirstName(firstname);
		System.out.println(b);
		if (!b.isEmpty()) {
			return employeeRepository.getEmployeeByFirstName(firstname);
		} else {
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee with first name not found");
	}

	@Override
	public List<Employee> FindAllByPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("employId").ascending());
		Page<Employee> page = employeeRepository.findAll(pageable);
		// Slice<Employee>
		// if(page.hasContent()) {
		List<Employee> list = page.getContent();
		// }
//		list.stream()
//	    .filter(p -> page != null && page.hasContent())
//	    .forEach(p -> {
//	        List<Employee> content = page.getContent();
//	    });
		return list.stream().map(e -> dozerBeanMapper.map(e, Employee.class, "employee-full"))
				.collect(Collectors.toList());
	}

	@Override
	public List<Employee> findByName(String firstname) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

		Root<Employee> employee = cq.from(Employee.class);

		Predicate firstnamePredicate = cb.equal(employee.get(Employee_.firstname), firstname);
		// LOGGER.log((LogRecord) firstnamePredicate);
		System.out.println(firstnamePredicate);

		cq.where(firstnamePredicate);
		System.out.println(cq);

		TypedQuery<Employee> query = entityManager.createQuery(cq);
		System.out.println(query.getFirstResult());

		return query.getResultList();
	}

	@Override
	// @Transactional
	public List<Employee> findByPosition(String position) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();

		Root<Employee> employee = cq.from(Employee.class);
		Predicate firstnamePredicate = cb.equal(employee.get(Employee_.position), position);

		cq.where(firstnamePredicate);

		TypedQuery<Employee> query = entityManager.createQuery(cq);
		System.out.println(query.getResultList());
		LOGGER.info(query.getResultList().toString());
		List<Employee> list = query.getResultList();

		return dozerBeanMapper.map(list, List.class);
	}

	@Override
	public Employee patch(String id, Map<String, Object> employee) {
		Employee dbEmployee = employeeRepository.findById(Integer.parseInt(id)).get();
		employee.entrySet().forEach(entry -> {
//			if (StringUtils.equalsAnyIgnoreCase(entry.getKey(), "id")) {
//				return;
//			}
			if (StringUtils.equalsAnyIgnoreCase(entry.getKey(), "address")) {
				try {
					if (entry.getValue() == null) {
						deleteExistingAddress(dbEmployee.getAddress());
						return;
					}
					String addressJson = mapper.writeValueAsString(entry.getValue());
					@SuppressWarnings("unchecked")
					HashMap<String, Object> address = mapper.readValue(addressJson, HashMap.class);
					Integer addressId = dbEmployee.getAddress() == null ? null : dbEmployee.getAddress().getId();
					Address updatedAddress = patchAddress(addressId, address, dbEmployee);
					dbEmployee.setAddress(updatedAddress);
					return;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				PropertyUtils.setProperty(dbEmployee, entry.getKey(), entry.getValue());
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		});
		Employee updatedEmployee = employeeRepository.save(dbEmployee);
		return dozerBeanMapper.map(updatedEmployee, Employee.class);
	}

	private void deleteExistingAddress(Address address) {
		if (address == null) {
			return;
		}
		addressRepository.delete(address);
	}

	private Address patchAddress(Integer addressId, Map<String, Object> address, Employee dbEmployee) {
		Address dbAddress = addressId == null ? new Address() : addressRepository.findById(addressId).get();
		dbAddress.setEmployee(dbEmployee);
		// same as above for address
		address.entrySet().forEach(entry -> {
			try {
				PropertyUtils.setProperty(dbAddress, entry.getKey(), entry.getValue());
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		});
		return addressRepository.save(dbAddress);
	}
}