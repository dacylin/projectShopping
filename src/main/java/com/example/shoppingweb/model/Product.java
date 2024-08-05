package com.example.shoppingweb.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "product")

public class Product {
	@Id
	private String productid;
	private String supplierid;
	private String pname;
	private String ptext;
	private Integer unitcost;
	private Integer unitprice;
	private Integer pqty;
	private String ptypeid;
	private String employeeid;
	private LocalDateTime createtime;
	private Boolean enabled;
	
	@OneToMany
	@JoinColumn(name="productid")
	private List<OrderDetail> details;
	public Product() {
	}

	public Product(String productid, String pname, String ptext, Integer unitprice, Integer pqty, String ptypeid) {

		this.productid = productid;
		this.pname = pname;
		this.ptext = ptext;
		this.unitprice = unitprice;
		this.pqty = pqty;
		this.ptypeid = ptypeid;
	}

	public Product(String productid, String supplierid, String pname, String ptext, Integer unitcost, Integer unitprice,
			Integer pqty, String ptypeid, String employeeid, LocalDateTime createtime, Boolean enabled) {
		this.productid = productid;
		this.supplierid = supplierid;
		this.pname = pname;
		this.ptext = ptext;
		this.unitcost = unitcost;
		this.unitprice = unitprice;
		this.pqty = pqty;
		this.ptypeid = ptypeid;
		this.employeeid = employeeid;
		this.createtime = createtime;
		this.enabled = enabled;
	}

	public Product(String productid, String pname, String ptext, Integer unitprice, Integer pqty, String ptypeid,
			String employeeid, LocalDateTime createtime, Boolean enabled) {

		this.productid = productid;
		this.pname = pname;
		this.ptext = ptext;
		this.unitprice = unitprice;
		this.pqty = pqty;
		this.ptypeid = ptypeid;
		this.employeeid = employeeid;
		this.createtime = createtime;
		this.enabled = enabled;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPtext() {
		return ptext;
	}

	public void setPtext(String ptext) {
		this.ptext = ptext;
	}

	public Integer getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(Integer unitcost) {
		this.unitcost = unitcost;
	}

	public Integer getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Integer unitprice) {
		this.unitprice = unitprice;
	}

	public Integer getPqty() {
		return pqty;
	}

	public void setPqty(Integer pqty) {
		this.pqty = pqty;
	}

	public String getPtypeid() {
		return ptypeid;
	}

	public void setPtypeid(String ptypeid) {
		this.ptypeid = ptypeid;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public LocalDateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
