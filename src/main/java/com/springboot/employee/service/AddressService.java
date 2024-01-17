package com.springboot.employee.service;

import com.springboot.employee.model.Address;

public interface AddressService {

	public Address addAddress(Address address);
	public Address updateAddress(Address address);
	public void removeAddress(Address dbAddress);
	//Address updateAddress(Address address, Employee emp);
	public Address getAddress(Address address);

}
