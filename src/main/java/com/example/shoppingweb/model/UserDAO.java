package com.example.shoppingweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserDAO extends JpaRepository<User, String>{

	List<User> findByUserName(String userName);
	User findByEmployeeID(String employeeID);
	
    // 使用自動化命名規則進行條件搜尋(多條件)
    User findByUserNameAndPassword(String userName, String password);
    
	boolean existsByUserName(String UserName);	
	boolean existsByEmployeeID(String employeeID);

    // 自定義SQL查詢
    @Query(value = "select * from user where employeeID = ?1", nativeQuery = true)
    User queryByEmployeeID(String employeeID);
    
    @Query(value = "select * from user where userName = ?1 and password= ?2 and enabled=true", nativeQuery = true)
    User queryUserNameAndPassword(String userName, String password);
 
}
