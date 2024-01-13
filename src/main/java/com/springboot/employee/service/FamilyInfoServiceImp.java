package com.springboot.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

	@Override
	public FamilyInfo addFamilyInfo(FamilyInfo familyInfo) {
		// TODO Auto-generated method stub
		System.out.println("Implementation starting..");
		Employee e = familyInfo.getEmployee();
		System.out.println(e);
		Employee e1 = employeeRepository.findById(e.getEmployId()).get();
		System.out.println(e1);
		familyInfo.setEmployee(e1);
		System.out.println(familyInfo);
		// Optional<Employee> e1=employeeRepository.findById(e.getEmployId());
		FamilyInfo f = familyInfoRepository.save(familyInfo);
		System.out.println("saved successfully....");
		return dozerBeanMapper.map(f, FamilyInfo.class, "family-info-basic");
	}

	@Override
	public FamilyInfo updateFamilyInfo(FamilyInfo familyInfo) {
		// TODO Auto-generated method stub
		Optional<FamilyInfo> familyOptional = familyInfoRepository.findById(familyInfo.getId());
		if (!familyOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			FamilyInfo f = familyOptional.get();
			f.setName(familyInfo.getName());
			f.setDob(familyInfo.getDob());
			f.setOccupation(familyInfo.getOccupation());
			f.setRelationship(familyInfo.getRelationship());

			FamilyInfo family = familyInfoRepository.save(f);
			System.out.println("Updated successfully....");
			return dozerBeanMapper.map(family, FamilyInfo.class, "family-info-basic");

		}

	}

	@Override
	public FamilyInfo getFamilyInfo(Integer id) {
		// TODO Auto-generated method stub
		Optional<FamilyInfo> f = familyInfoRepository.findById(id);
		if (f.isPresent()) {
			FamilyInfo familyInfo = f.get();
			return familyInfo;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<FamilyInfo> getByEmployeeId(Integer employeeId) {
		// TODO Auto-generated method stub
		List<FamilyInfo> familyList = familyInfoRepository.findByEmployeeEmployId(employeeId);
		// System.out.println(e);
		// familyInfoRepository.findAllById(familyInfo.getId());
		System.out.println(familyList);
		return familyList.stream().map(e -> dozerBeanMapper.map(e, FamilyInfo.class,"family-info-basic")).collect(Collectors.toList());
	}

	@Override
	public void removeFamilyInfo(Integer id) {
		// TODO Auto-generated method stub
		familyInfoRepository.deleteById(id);
		System.out.println("deleted successfully...");

	}

}
