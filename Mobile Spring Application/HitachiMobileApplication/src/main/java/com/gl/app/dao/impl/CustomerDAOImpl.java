package com.gl.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gl.app.dao.CustomerDAO;
import com.gl.app.entity.Customer;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.util.HitachiUtil;

public class CustomerDAOImpl implements CustomerDAO{

	Connection con = null;
	@Override
	 public String updateCustomerAddress(Long customerId, String newAddress) throws CustomerDoesNotExistException {
		// Write code to update customer address
		try{
			con=HitachiUtil.getConnection();
			String query="UPDATE  hitachimobileapplication.customer SET address = ? WHERE customer_id = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1,newAddress);
			statement.setLong(2,customerId);
			int count = statement.executeUpdate();
			if(count==1){
				return "ADDRESS UPDATED SUCCESSFULLY";
			}
		}catch (CustomerDoesNotExistException | SQLException e){
			throw new CustomerDoesNotExistException(e.getMessage());
		}
		return "PLEASE ENTER A VALID CUSTOMER_ID";
    }

	@Override
	public List<Customer> getAllCustomers() throws CustomerTableEmptyException {
	   // Write code to fetch all customers
		List<Customer> customerList = new ArrayList<>();
		try{
			con=HitachiUtil.getConnection();
			String query="SELECT * FROM hitachimobileapplication.customer";
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()){
				Long customerId = resultSet.getLong("customer_id");
				String dateOfBirth = resultSet.getString("date_of_birth");
				String emailAddress = resultSet.getString("email_address");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String id_type = resultSet.getString("id_type");
				String address = resultSet.getString("address");
				String state = resultSet.getString("state");
				Customer customer = new Customer(customerId,dateOfBirth,emailAddress,firstName,lastName,id_type,address,state);
				customerList.add(customer);
			}
			return customerList;

		}catch (CustomerTableEmptyException|CustomerDoesNotExistException | SQLException e){
			throw new CustomerTableEmptyException("customer table is empty");
		}

	}


        @Override

	public List<SIMDetails> fetchSIMDetails(Long customerId) {
		List<SIMDetails> simDetailsList = new ArrayList<>();
		try {

			con = HitachiUtil.getConnection();
			String query = "SELECT * FROM hitachimobileapplication.simdetails where customer_id = ? ";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setLong(1, customerId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				long simId = resultSet.getLong("sim_id");
				long serviceNumber = resultSet.getLong("service_number");
				long simNumber = resultSet.getLong("sim_number");
				String status = resultSet.getString("status");
				Long customerId1 = resultSet.getLong("customer_id");
				SIMDetails simDetails = new SIMDetails(simId,serviceNumber,simNumber,status,customerId1);
				simDetailsList.add(simDetails);
			}

		}catch (CustomerDoesNotExistException | SQLException e){
			throw new CustomerDoesNotExistException(e.getMessage());
		}
	    
	    return simDetailsList;
	}
}
