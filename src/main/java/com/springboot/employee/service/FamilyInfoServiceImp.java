package com.springboot.employee.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.employee.event.EmployeeUpdatedEvent;
import com.springboot.employee.event.FamilyInfoCreatedEvent;
import com.springboot.employee.event.FamilyInfoDeletedEvent;
import com.springboot.employee.event.FamilyInfoUpdatedEvent;
import com.springboot.employee.model.Employee;
import com.springboot.employee.model.FamilyInfo;
import com.springboot.employee.repository.EmployeeRepository;
import com.springboot.employee.repository.FamilyInfoRepository;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class FamilyInfoServiceImp implements FamilyInfoService {

	@Autowired
	private FamilyInfoRepository familyInfoRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger logger = LoggerFactory.getLogger(FamilyInfoServiceImp.class);

	@Override
	public FamilyInfo addFamilyInfo(FamilyInfo familyInfo) {
		System.out.println("Implementation starting..");
		Employee e = familyInfo.getEmployee();
		System.out.println(e);
		Employee e1 = employeeRepository.findById(e.getEmployId()).get();
		System.out.println(e1);
		familyInfo.setEmployee(e1);
		System.out.println(familyInfo);
		// Optional<Employee> e1=employeeRepository.findById(e.getEmployId());
		FamilyInfo f = familyInfoRepository.save(familyInfo);
		// familyInfoEsService.addFamilyinfo(familyInfo);
		FamilyInfoCreatedEvent familyInfoCreatedEvent = new FamilyInfoCreatedEvent(
				familyInfo.getEmployee().getEmployId());
		applicationEventPublisher.publishEvent(familyInfoCreatedEvent);

		System.out.println("saved successfully....");
		return dozerBeanMapper.map(f, FamilyInfo.class, "family-info-basic");
	}

	@Override
	public FamilyInfo updateFamilyInfo(FamilyInfo familyInfo) {
		List<FamilyInfo> familyList = familyInfoRepository
				.findByEmployeeEmployId(familyInfo.getEmployee().getEmployId());
		Optional<FamilyInfo> familyOptional = familyList.stream().filter(f -> f.getId().equals(familyInfo.getId()))
				.findFirst();
		// if (family.isPresent()) {
		// Optional<FamilyInfo> familyOptional =
		// familyInfoRepository.findById(familyInfo.getId());
		if (!familyOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			FamilyInfo f = familyOptional.get();
			f.setName(familyInfo.getName());
			f.setDob(familyInfo.getDob());
			f.setOccupation(familyInfo.getOccupation());
			f.setRelationship(familyInfo.getRelationship());

			FamilyInfo family = familyInfoRepository.save(f);
			// familyInfoEsService.updateFamilyInfo(family);
			FamilyInfoUpdatedEvent familyInfoUpdatedEvent = new FamilyInfoUpdatedEvent(
					familyInfo.getEmployee().getEmployId());
			applicationEventPublisher.publishEvent(familyInfoUpdatedEvent);
			System.out.println("Updated successfully....");
			return dozerBeanMapper.map(family, FamilyInfo.class, "family-info-basic");

		}
	}

	@Override
	public FamilyInfo getFamilyInfo(Integer id) {
		Optional<FamilyInfo> f = familyInfoRepository.findById(id);
		if (f.isPresent()) {
			FamilyInfo familyInfo = f.get();
			// familyInfoEsService.getFamilyInfo(id);
			return familyInfo;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<FamilyInfo> getByEmployeeId(Integer employeeId) {
		List<FamilyInfo> familyList = familyInfoRepository.findByEmployeeEmployId(employeeId);
		// System.out.println(e);
		// familyInfoRepository.findAllById(familyInfo.getId());
		System.out.println(familyList);
		// familyInfoEsService.getByEmployeeId(employeeId);
		return familyList.stream().map(e -> dozerBeanMapper.map(e, FamilyInfo.class, "family-info-basic"))
				.collect(Collectors.toList());
	}

	@Override
	public void removeFamilyInfo(Integer id, Integer employeeId) {
		List<FamilyInfo> familyList = familyInfoRepository.findByEmployeeEmployId(employeeId);
		Optional<FamilyInfo> family = familyList.stream().filter(f -> f.getId().equals(id)).findFirst();
		if (family.isPresent()) {
			familyInfoRepository.deleteById(id);
			entityManager.flush();
			FamilyInfoDeletedEvent familyInfoDeletedEvent = new FamilyInfoDeletedEvent(employeeId);
			applicationEventPublisher.publishEvent(familyInfoDeletedEvent);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		}
		System.out.println("deleted successfully...");

	}

//	@Override
//	public Employee patchFamilyInfo(Integer employId, Map<String, Object> employee) {
//		Employee dbEmployee = employeeRepository.findById(employId).get();
//		employee.entrySet().forEach(entry -> {
//			if (StringUtils.equalsAnyIgnoreCase(entry.getKey(), "familyInfo")) {
//				try {
//					String familyInfoJson = objectMapper.writeValueAsString(entry.getValue());
//					@SuppressWarnings("unchecked")
//					HashMap<String, Object> familyInfo = objectMapper.readValue(familyInfoJson, HashMap.class);
//					List<FamilyInfo> familyInfos = familyInfoRepository.findByEmployeeEmployId(employId);
//					List<FamilyInfo> updatedFamilyInfo = patch(familyInfos, familyInfo, dbEmployee);
//					dbEmployee.setFamilyInfo(updatedFamilyInfo);
//					return;
//				} catch (JsonProcessingException e) {
//					e.printStackTrace();
//				}
//				try {
//					PropertyUtils.setProperty(dbEmployee, entry.getKey(), entry.getValue());
//				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//		logger.info("updatedEmployee saved...");
//		Employee updatedEmployee = employeeRepository.save(dbEmployee);
//		logger.info("event publish..");
//		EmployeeUpdatedEvent employeeUpdatedEvent = new EmployeeUpdatedEvent(employId);
//		applicationEventPublisher.publishEvent(employeeUpdatedEvent);
//		return dozerBeanMapper.map(updatedEmployee, Employee.class);
//	}
//
//	private List<FamilyInfo> patch(List<FamilyInfo> familyInfos, HashMap<String, Object> familyInfo,
//			Employee dbEmployee) {
//        List<FamilyInfo> f = new ArrayList<>();
//		for (FamilyInfo familyInfo2 : familyInfos) {
//			familyInfo.entrySet().forEach(entry -> {
//				if (StringUtils.equalsIgnoreCase(entry.getKey(), "id") || entry.getValue() == null) {
//					if (entry.getValue() == familyInfo2.getId() || entry.getValue() == null) {
//						FamilyInfo dbFamilyInfo = familyInfo2.getId() == null ? new FamilyInfo()
//								: familyInfoRepository.findById(familyInfo2.getId()).get();
//						dbFamilyInfo.setEmployee(dbEmployee);
//						try {
//							PropertyUtils.setProperty(dbFamilyInfo, entry.getKey(), entry.getValue());
//						} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//							e.printStackTrace();
//						}
//						 f.add(familyInfoRepository.save(dbFamilyInfo));
//
//					}
//				}
//			});
//		}
//		return f;
//	}
}
