package com.springboot.employee.service;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.employee.model.Address;
import com.springboot.employee.repository.AddressRepository;
import com.springboot.employee.repository.EmployeeRepository;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class AddressServiceImp implements AddressService {
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AddressEsService addressEsService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Address addAddress(Address address) {
		Address add = addressRepository.save(address);
		addressEsService.addAddress(address);
		// entityManager.flush();
		return add;
	}

	@Override
	public Address updateAddress(Address address) {
		System.out.println(address.getId());
		Optional<Address> optionalAddress = addressRepository.findById(address.getId());
		if (optionalAddress.isPresent()) {
			Address add = optionalAddress.get();
			add.setCity(address.getCity());
			add.setState(address.getState());
			//add.getEmployee().setModifiedBy(add.getModifiedBy());
			Address a = addressRepository.save(add);
			//entityManager.flush();
			//employeeRepository.save(add.getEmployee());
			entityManager.flush();
			System.out.println("save address -> " + a);
			return a;
		} else {
			System.out.println("null return");
			return null;
		}

	}

	@Override
	public void removeAddress(Address dbAddress) {
//		Optional<Address> optionalAddress=addressRepository.findById(dbAddress.getId());
//		if(optionalAddress.isPresent()) {
		addressRepository.deleteById(dbAddress.getId());
		// }

	}

	@Override
	public Address getAddress(Address address) {
		Optional<Address> optionalAddress = addressRepository.findById(address.getId());
		if (optionalAddress.isPresent()) {
			return optionalAddress.get();
		}
		return null;
	}

}
