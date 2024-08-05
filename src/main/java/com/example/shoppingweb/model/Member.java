package com.example.shoppingweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Entity
@Table(name="members")
@Data
@NoArgsConstructor
@AllArgsConstructor	
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="memberid", unique=true)
	private Integer memberid;
	
	@Column(name="email", unique=true)
	private String email;
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="address")
	private String address;
	@Column(name="mobile")
	private String mobile;
	@Column(name="birthday")
	private String birthday;
	@Column(name="nid")
	private String nid;
	@Column(name="active")
	private Boolean active; //是否啟用
	
	@OneToMany
	@JoinColumn(name="memberid")
	private List<SalesOrder> salesOrders;
	
	@Override
	public String toString() {
		return "Member [memberid=" + memberid + ", email=" + email + ", password=" + password + ", address=" + address
				+ ", mobile=" + mobile + ", birthday=" + birthday + ", nid=" + nid + ", active=" + active + "]";
	}
	

}

