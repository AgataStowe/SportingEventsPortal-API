package com.portal.sportsevent.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Address")
public class Address implements Serializable{
	
	private static final long serialVersionUID = 7551635165089360962L;
	
	@Id
	@GeneratedValue(generator = "SQ_ADDRESS")
	@Column(name = "ID_ADDRESS", nullable = false)
	private Long id;
	
	@Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;
	
	@Column(name = "PUBLIC_AREA", nullable = false)
	private String publicArea;
	
	@Column(name = "NUMBER")
    private int number;
    
	@Column(name = "NEIGHBORHOOD", nullable = false)
    private String neighborhood;
    
	@Column(name = "CITY", nullable = false)
    private String city;
    
	@Column(name = "STATE", nullable = false)
    private String state;
}
