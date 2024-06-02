package com.gl.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.SIMDoesNotExistsException;

public interface SIMDetailsDAO {
	public List<SIMDetails> fetchSIMDetailsWithActiveStatus() throws SQLException;
	public String getSimStatus(long simNumber, long serviceNumber) throws SIMDoesNotExistsException, SQLException;
}
