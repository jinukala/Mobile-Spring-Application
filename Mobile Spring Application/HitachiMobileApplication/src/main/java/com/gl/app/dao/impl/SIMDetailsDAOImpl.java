package com.gl.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.gl.app.dao.SIMDetailsDAO;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.SIMDoesNotExistsException;
import com.gl.app.util.HitachiUtil;

public class SIMDetailsDAOImpl implements SIMDetailsDAO{
	Connection conn;
	
	@Override
	public List<SIMDetails> fetchSIMDetailsWithActiveStatus() throws SQLException {

		// Write code to fetch SIM details with active status
		try {
			List<SIMDetails> simDetailsList = new ArrayList<>();
			conn = HitachiUtil.getConnection();
			String query = "SELECT * FROM hitachimobileapplication.simdetails ";
//			String query = "SELECT * FROM hitachimobileapplication.simdetails WHERE status= 'Active'";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long simId = resultSet.getLong("sim_id");
				long serviceNumber = resultSet.getLong("service_number");
				long simNumber = resultSet.getLong("sim_number");
				String status = resultSet.getString("status");
				Long customerId = resultSet.getLong("customer_id");
				SIMDetails simDetails = new SIMDetails(simId, serviceNumber, simNumber, status, null);
				simDetailsList.add(simDetails);
			}
			return simDetailsList;
		}catch (SQLException e){
			throw new SQLException("Connection Error");
		}
	}


	@Override
	 public String getSimStatus(long simNumber, long serviceNumber) throws SIMDoesNotExistsException, SQLException {
           // Write code to get SIM status
		try{
		conn = HitachiUtil.getConnection();
		String query ="SELECT status FROM hitachimobileapplication.simdetails Where sim_number=? and service_number=?";
			//String query = "SELECT * FROM hitachimobileapplication.simdetails";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setLong(1,simNumber);
		statement.setLong(2,serviceNumber);
		ResultSet resultSet = statement.executeQuery();

		if(resultSet.next()){
			String status = resultSet.getString("status");
			return "Status "+status;

		}
		}catch (SIMDoesNotExistsException simDoesNotExistsException) {

			throw new SIMDoesNotExistsException("SIM DOES NOT EXISTS WITH GIVEN SIM NUMBER " + simNumber + " AND SERVICE NUMBER " + serviceNumber);
		}
		return "SIM DOES NOT EXISTS WITH GIVEN SIM NUMBER " + simNumber + " AND SERVICE NUMBER " + serviceNumber;
    }
	

	
}
