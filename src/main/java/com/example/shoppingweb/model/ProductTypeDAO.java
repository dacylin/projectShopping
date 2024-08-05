package com.example.shoppingweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeDAO extends JpaRepository<ProductType, String> {
	
	ProductType findByPtypeid(String ptypeid);
	
	List<ProductType> findByPtypeidStartingWith(String ptypeid);

	
}
