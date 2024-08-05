package com.example.shoppingweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDAO extends JpaRepository<OrderDetail,String>{
	List<OrderDetail> findBysorderId(String sorderId);
}
