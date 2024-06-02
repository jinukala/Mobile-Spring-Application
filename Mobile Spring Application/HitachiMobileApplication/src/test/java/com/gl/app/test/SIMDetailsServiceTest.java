package com.gl.app.test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gl.app.dao.SIMDetailsDAO;
import com.gl.app.dao.impl.SIMDetailsDAOImpl;
import com.gl.app.entity.SIMDetails;



import static org.junit.jupiter.api.Assertions.*;

import com.gl.app.service.impl.SIMDetailsServiceImpl;
import org.junit.jupiter.api.Test;

import com.gl.app.service.SIMDetailsService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class SIMDetailsServiceTest {
	@Mock
	SIMDetailsDAOImpl simDetailsDAO = mock(SIMDetailsDAOImpl.class);

	@InjectMocks
	SIMDetailsServiceImpl simDetailsService = new SIMDetailsServiceImpl(simDetailsDAO) ;







	@Test
	    public void testFetchSIMDetailsWithActiveStatus() throws SQLException {
	      //write your code here
		List<SIMDetails> simDetailsList = new ArrayList<>();
		SIMDetails sim1 = new SIMDetails(1L, 1234567890L, 9876543210L, "Active", 1L);
		SIMDetails sim2 = new SIMDetails(2L, 1234567891L, 9876543211L, "Active", 2L);
		SIMDetails sim3 = new SIMDetails(3L, 1234567890L, 9876543210L, "Inactive", 3L);
		SIMDetails sim4 = new SIMDetails(4L, 1234567891L, 9876543211L, "Active", 4L);
		simDetailsList.add(sim1);
		simDetailsList.add(sim2);
		when(simDetailsDAO.fetchSIMDetailsWithActiveStatus()).thenReturn(simDetailsList);
		List<SIMDetails> result = simDetailsService.fetchSIMDetailsWithActiveStatus();
		//assertEquals(2,result.size());
		for(SIMDetails simDetails: result){
			System.out.println(simDetails.getStatus());
		}
		result.forEach(x -> assertEquals("Active", x.getStatus()));


		 
	    }
}
