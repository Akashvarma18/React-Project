package com.zettamine.entity;


import javax.management.relation.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer userId;
	
	private String firstName;
	private String password;
	private String tempPwd;
	private String lastName;
	private String email;
	private String designation;
	private Integer loginStatus;
	private boolean accountLock;
	private Integer attempts;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Roles role;
	
	
	

}
