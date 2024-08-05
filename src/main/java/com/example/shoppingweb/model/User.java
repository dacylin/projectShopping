package com.example.shoppingweb.model;

import java.util.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
	@Id
	private String employeeID;
	
	private String department;
	private String name;
	private String email;
	private String userName;
	private String password;
	
    private LocalDateTime createdAt;
    private String createdUser;
    private LocalDateTime updatedAt;
    private String editUser;
 
	private Boolean enabled; //是否啟用
	
	@OneToMany
	@JoinColumn(name="employeeid")
	private List<SalesOrder> salesOrder;
	
	@OneToMany
	@JoinColumn(name="employeeid")
	private List<OrderDetail> orderDetails;
	
	@OneToMany
	@JoinColumn(name="employeeid")
	private List<Product> products;
	
	@OneToMany
	@JoinColumn(name="employeeid")
	private List<ProductType> productTypes;
	
	
    public User() {}
	public User(String employeeID, String department, String name, String email, String userName, String password,
			LocalDateTime createdAt, String createdUser, LocalDateTime updatedAt, String editUser, Boolean enabled) {
		this.employeeID = employeeID;
		this.department = department;
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.createdAt = createdAt;
		this.createdUser = createdUser;
		this.updatedAt = updatedAt;
		this.editUser = editUser;
		this.enabled = enabled;
	}
	
	public User(String department, String name, String email, String userName, String password) {
		this.department = department;
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
//	public List<SalesOrder> getSalesOrder() {
//		return salesOrder;
//	}
//	public void setSalesOrder(List<SalesOrder> salesOrder) {
//		this.salesOrder = salesOrder;
//	}
//	public List<OrderDetail> getOrderDetails() {
//		return orderDetails;
//	}
//	public void setOrderDetails(List<OrderDetail> orderDetails) {
//		this.orderDetails = orderDetails;
//	}
//	public List<Product> getProducts() {
//		return products;
//	}
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//	public List<ProductType> getProductTypes() {
//		return productTypes;
//	}
//	public void setProductTypes(List<ProductType> productTypes) {
//		this.productTypes = productTypes;
//	}

	
}
