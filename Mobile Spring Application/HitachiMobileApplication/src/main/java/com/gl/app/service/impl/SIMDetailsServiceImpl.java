package com.gl.app.service.impl;
import java.util.List;
import com.gl.app.dao.*;
import com.gl.app.dao.impl.*;
import java.util.stream.Collectors;
import java.util.ArrayList;

import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.SIMDoesNotExistsException;
import com.gl.app.service.SIMDetailsService;
import com.gl.app.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class SIMDetailsServiceImpl implements SIMDetailsService{
	SIMDetailsDAO simDetailsDAO = new SIMDetailsDAOImpl();
	public SIMDetailsServiceImpl()	{

	}
	public SIMDetailsServiceImpl(SIMDetailsDAO simDetailsDAO)	{
		this.simDetailsDAO = simDetailsDAO;
	}
@Override
	public List<SIMDetails> fetchSIMDetailsWithActiveStatus() throws SQLException {
		List<SIMDetails> simDetailsList = new ArrayList<>();
		List<SIMDetails> list = simDetailsDAO.fetchSIMDetailsWithActiveStatus();
		list.forEach(simDetails -> {
			if(simDetails.getStatus().equals("Active")){
				 simDetailsList.add(simDetails);
			};
		});
			return simDetailsList;
	    }

	@Override
	 public String getSimStatus(long simNumber, long serviceNumber) throws SIMDoesNotExistsException, SQLException {

          return simDetailsDAO.getSimStatus(simNumber,serviceNumber);
    }
	
		
	}


