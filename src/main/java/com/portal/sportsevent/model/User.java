package com.portal.sportsevent.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User implements UserDetails{

	private static final long serialVersionUID = -7596010601656456545L;
	
	@Id
	@GeneratedValue(generator = "SQ_USERS")
	@Column(name = "ID_USER", nullable = false)
	private Long id;
	
	@Column(name = "FULL_NAME", nullable = false)
	private String fullName;
	
	@Column(name = "NICKNAME")
	private String nickname;

	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "CREATION_DATE", columnDefinition = "date default current_date ", insertable = false, updatable = true)
	private Date creationDate;
	
	@Column(name = "ACTIVE", columnDefinition = "boolean default true", insertable = false, updatable = true)
	private boolean active = true;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
