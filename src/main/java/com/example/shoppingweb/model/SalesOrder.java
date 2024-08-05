package com.example.shoppingweb.model;



import java.util.*;



import jakarta.persistence.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="salesorder")
public class SalesOrder {	
	@Id	
	private String sorderId;//NN

	private Integer memberId;//NN

	private String generateDate;//NN

	private String receiveAddress;//NN

	private String statusCode="SOS_P";//NN

	private Date shipDate;

	private Integer sorderPrice;//NN

	private Date complateDate;

	private String employeeId;
	
	@OneToMany
	@JoinColumn(name="sorderid")
	private List<OrderDetail> orderDetails;
	
	
	
	
	
	public SalesOrder( Integer memberId, String receiveAddress,Integer sorderPrice,List<OrderDetail> orderDetails) {		
		this.memberId = memberId;
//		this.generateDate = SalesOrderService.getCurrentDateTime();
		this.receiveAddress = receiveAddress;		
		this.sorderPrice = sorderPrice;
		//this.orderDetails=orderDetails;
	}
	
	
}
