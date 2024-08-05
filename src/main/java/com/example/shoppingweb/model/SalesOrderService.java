package com.example.shoppingweb.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class SalesOrderService {   
	@Autowired	
	SalesOrderDAO dao;
	
    public static String getCurrentDateTime() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date currentDateTime = new Date();
        return dateTimeFormat.format(currentDateTime);
    }
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    public static String calculateSalesOrderNum(String salesDate,List<SalesOrder> list)
	{	
    	String salesOrderNum="";
		String firstCode = "S";
		Integer serialNum = 0;
		String secondCode = salesDate;			
		List<SalesOrder> salesOrderList = list;
//		salesOrderList.add(new SalesOrder("S20240305001","M240306001","Taipei",1000));
//		salesOrderList.add(new SalesOrder("S20240305002","M240306001","Taipei",1000));
		int sizeOfSalesOrderList = salesOrderList.size();
		
		if( sizeOfSalesOrderList > 0)
		{
			
			String getLastSalesOrderId =salesOrderList.get(sizeOfSalesOrderList-1).getSorderId();
			String salesLastDate=getLastSalesOrderId.substring(1,9);
			if(!getLastSalesOrderId.equals(""))
			{	
				if(secondCode.equals(salesLastDate))
				{
					serialNum = Integer.parseInt(getLastSalesOrderId.substring(9,12));
					if(serialNum <10)
						salesOrderNum = firstCode+secondCode+"00"+(++serialNum);
					else if(serialNum < 100)
						salesOrderNum = firstCode+secondCode+"0"+(++serialNum);
					else if(serialNum <999)
						salesOrderNum = firstCode+secondCode+(++serialNum);
				}
				else
				{
					salesOrderNum = firstCode+secondCode+"001";
				}	
			}			
			else
			{
				salesOrderNum = firstCode+secondCode+"001";
			}		
		}
		else
		{
			salesOrderNum = firstCode+secondCode+"001";
		}
		
		return salesOrderNum;
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.print(calculateSalesOrderNum(SalesOrderService.getCurrentDate()));
//
//	}

}
