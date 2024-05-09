package com.zettamine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zettamine.entity.Roles;



@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer>{
	
	
	
	

}
