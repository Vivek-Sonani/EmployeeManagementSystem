package com.springboot.employee.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.employee.repository.*;
import com.springboot.employee.event.FamilyInfoCreatedEvent;
import com.springboot.employee.event.FamilyInfoDeletedEvent;
import com.springboot.employee.event.FamilyInfoUpdatedEvent;
import com.springboot.employee.model.Employee;
import com.springboot.employee.model.Employeedocument;
import com.springboot.employee.model.FamilyInfo;
import com.springboot.employee.model.FamilyInfoDocument;

@Service
public class FamilyInfoEsServiceImp implements FamilyInfoEsService {

//	@Autowired
//	private FamilyInfoEsRepository familyInfoEsRepository;
//
//	@Autowired
//	private FamilyInfoRepository familyInfoRepository;
//
//	@Autowired
//	private EmployeeEsRepository employeeEsRepository;
//	
//	@Autowired
//	private EmployeeRepository employeeRepository;
//
//	private static final Logger logger = LoggerFactory.getLogger(FamilyInfoEsServiceImp.class);
//	
//
//	@Override
//	public void indexFamilyInfo(Integer employeeId) { // get data from mysql and set or add to ElastiSearch
//		List<FamilyInfo> familyInfoList = new ArrayList<>();
//		FamilyInfo familyInfo = familyInfoRepository.findById(employeeId).get();
//		FamilyInfoDocument familyInfoDocument = new FamilyInfoDocument(familyInfo);
//		Integer employId = familyInfo.getEmployee().getEmployId();
//		Employee employee = employeeRepository.findById(employId).get();
//		logger.info("employee before familyinfo" + employee);
//		familyInfoList.add(familyInfo);
//		employee.setFamilyInfo(familyInfoList);
//		familyInfo.setEmployee(employee);
//		logger.info("employee after familyinfo : " + employee);
//		Employeedocument employeedocument = new Employeedocument(employee);
//		//addFamilyInfoToEmployee(employeedocument);
//		employeeEsRepository.save(employeedocument);
//	}
//
//
//	@Override
//	public void updateFamilyInfo(Integer id) {
//		FamilyInfo familyInfo = familyInfoRepository.findById(id).get();
//		FamilyInfoDocument familyInfoDocument = new FamilyInfoDocument(familyInfo);
//		familyInfoEsRepository.save(familyInfoDocument);
//	}
//
//	@Override
//	public FamilyInfoDocument getFamilyInfo(Integer id) {
//		return familyInfoEsRepository.findById(id).get();
//	}
//
//	@Override
//	public List<FamilyInfoDocument> getByEmployeeId(Integer employeeId) {
//		return familyInfoEsRepository.findByEmployeeId(employeeId);
//	}
//
//	@Override
//	public void removeFamilyInfo(Integer id) {
//		Optional<FamilyInfoDocument> familyInfoDocument = familyInfoEsRepository.findById(id);
//		if (!familyInfoDocument.isEmpty()) {
//			familyInfoEsRepository.deleteById(id);
//		} else {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//		}
//
//	}

//	@EventListener
//	public void onFamilyInfoCreated(FamilyInfoCreatedEvent event) {
//		Integer id = event.getId();
//		indexFamilyInfo(id);
//		logger.info("successfull...");
//
//	}

//	@EventListener
//	public void onFamilyInfoUpdated(FamilyInfoUpdatedEvent event) {
//		Integer id = event.getId();
//		updateFamilyInfo(id);
//		logger.info("successfull...");
//
//	}
//
//	@EventListener
//	public void onFamilyInfoDeleted(FamilyInfoDeletedEvent event) {
//		Integer id = event.getId();
//		removeFamilyInfo(id);
//		logger.info("successfull...");
//	}

}
