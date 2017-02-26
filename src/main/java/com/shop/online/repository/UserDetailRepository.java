package com.shop.online.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.online.model.UserDetail;


public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

	//@Query("select c from Cult c join c.part p join p.sau s join s.farm f where f.idFarm = ?1")
	@Query("SELECT u.fName FROM UserDetail u")
	public List<String> findAllUnicqueFname();
	
	@Query("SELECT u FROM UserDetail u Where u.fName = ?1 and u.lName = ?2 and u.location = ?3")
	public List<UserDetail> findUncqueByFnameLnameLocation(String fName,String lName,String location);
}
