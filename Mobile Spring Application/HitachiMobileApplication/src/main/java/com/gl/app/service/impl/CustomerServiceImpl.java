package com.gl.app.service.impl;
import com.gl.app.dao.*;
import com.gl.app.entity.*;
import com.gl.app.dao.impl.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.service.CustomerService;
import com.gl.app.util.HitachiUtil;

public class CustomerServiceImpl implements CustomerService {
	CustomerDAO customerDAO = new CustomerDAOImpl();

	public List<SIMDetails> fetchCustomerList(Long customerId) {
		// TODO Auto-generated method stub
		List<SIMDetails> list = customerDAO.fetchSIMDetails(customerId);
		try {
			if(list.isEmpty()){
				System.out.println("Customer Does not Exists");
			} else {
				return customerDAO.fetchSIMDetails(customerId);
			}
		}catch (CustomerDoesNotExistException e ) {
			System.out.println("CUSTOMER TABLE DOES NOT EXISTS");
		}
		return customerDAO.fetchSIMDetails(customerId);
	}

	@Override
	public String updateCustomerAddress(Long customerId, String city) throws CustomerDoesNotExistException, SQLException {
		return customerDAO.updateCustomerAddress(customerId, city);
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerTableEmptyException {
		return customerDAO.getAllCustomers();

	}
}
