package com.springboot.employee.service;

import com.springboot.employee.model.Address;
import com.springboot.employee.model.AddressDocument;

public interface AddressEsService {
	public AddressDocument addAddress(Address address);
	public void removeAddress(Address dbAddress);
	public AddressDocument updateAddress(Address address);
	public AddressDocument getAddress(Address address);


}
