package com.example.shoppingweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, String> {
	
	List<Product> findByPtypeid(String ptypeid);
	
	Product findByPname(String pname);
	
	List<Product> findByPtypeidStartingWith(String ptypeid);
	

}
