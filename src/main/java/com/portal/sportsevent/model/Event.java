package com.portal.sportsevent.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "EVENTS")
public class Event implements Serializable{
	
	private static final long serialVersionUID = 4512808819289785353L;

	@Id
	@GeneratedValue(generator = "SQ_EVENTS")
	@Column(name = "ID_EVENT", nullable = false)
	private Long id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "DATE_TIME", nullable = false)
	private Timestamp dateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_ADDRESS")
	private Address address;
	
	@Column(name = "CREATION_DATE", columnDefinition = "date default current_date ", insertable = false, updatable = true)
	private Date creationDate;
	
	@Column(name = "ACTIVE", columnDefinition = "boolean default true", insertable = false, updatable = true)
	private boolean active = true;
	
	@ManyToMany
	@JoinTable(name="EVENTS_USERS",  
		joinColumns = {@JoinColumn(name="ID_EVENT")}, 
		inverseJoinColumns = {@JoinColumn(name="ID_USER")})
	private List<User> users;
}
