package com.gl.app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SIMDetails {
    private long simId;
    private long serviceNumber;
    private long simNumber;
    private String status;
    private Long uniqueIdNumber;
	public SIMDetails() {
		super();
		// TODO Auto-generated constructor stub

	}

}