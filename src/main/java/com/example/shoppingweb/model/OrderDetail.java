package com.example.shoppingweb.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orderdetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
	@Id	
	private String sdetailId;//NN
	
	private String sorderId;//NN FK from salesOrder
	
	private Integer sIndex;//NN
	
	private String productId;//NN FK from product
	private String pname;
	private Integer unitPrice;//NN
	private Integer quantity;//NN
	
	private Integer subTotal;//NN
	private String employeeId;
	
	
}
