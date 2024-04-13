package com.springboot.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.employee.model.Address;
import com.springboot.employee.model.AddressDocument;
import com.springboot.employee.repository.AddressEsRepository;

@Service
public class AddressEsServiceImp implements AddressEsService{
	@Autowired
	private AddressEsRepository addressEsRepository;

	@Override
	public AddressDocument addAddress(Address address) {
		AddressDocument addressDocument = new AddressDocument(address);
		AddressDocument a =addressEsRepository.save(addressDocument);
		return a;
	}

	@Override
	public void removeAddress(Address dbAddress) {
		AddressDocument addressDocument = new AddressDocument(dbAddress);
		addressEsRepository.delete(addressDocument);		
		
	}

	@Override
	public AddressDocument updateAddress(Address address) {
		AddressDocument addressDocument = new AddressDocument(address);
		AddressDocument a =addressEsRepository.save(addressDocument);
		return a;
	}

	@Override
	public AddressDocument getAddress(Address address) {
		return addressEsRepository.findById(address.getId()).get();
	}

}
