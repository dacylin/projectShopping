package com.example.shoppingweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.shoppingweb.model.*;

@CrossOrigin("*")
@RestController
public class ProductTypeController {
	
	@Autowired
	ProductTypeService typesrv;
	
	//查詢所有商品類別
	@GetMapping("/productstypes")
	public List<ProductType> getAllProductType(){
		return typesrv.getAllProductType();	
	}
	
	// 查詢-根據主類別(PT1XXX)查詢商品類別
		@GetMapping("/productstype/bypmaintypeid/{ptypeid}")
		public List<ProductType> getAllProductsByPmaintypeid(@PathVariable String ptypeid){
			return typesrv.getAllProductsByPmaintypeid(ptypeid);
		}
	
	//查詢指定商品類別
	@GetMapping("/productstype/{ptypeid}")
	public ProductType  getProductType(@PathVariable String ptypeid){
		return typesrv.getProductTypeByPtypeid(ptypeid);
	}
	
	// 新增商品類別
	@PostMapping("/productstype/add")
    public ResponseEntity<String> addProductType(@RequestBody ProductType productType ){
        try {
            typesrv.addProductType(productType);
            return ResponseEntity.ok("Product type added successfully");     
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Failed to add product type: " + e.getMessage());
        }           
    }

	
	// 修改商品類別
	@PutMapping("/productstype/update/{ptypeid}")
	public ResponseEntity<ProductType> updateProductType(@PathVariable String ptypeid, @RequestBody ProductType producttype) {
	    try {
	        ProductType updatedProductType = typesrv.updateProductType(ptypeid, producttype);
	        return ResponseEntity.ok(updatedProductType);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().build();
	    }
	}
	    
	 // 刪除商品類別
	    @DeleteMapping("/productstype/delete/{ptypeid}")
	    public ResponseEntity<String> deleteProductType(@PathVariable String ptypeid) {
	        try {
	            typesrv.deleteProductType(ptypeid);
	            return ResponseEntity.ok("Product type deleted successfully");
	        } catch(Exception e) {
	            return ResponseEntity.badRequest().body("Failed to delete product type: " + e.getMessage());
	        }
	    }
}


	
	

