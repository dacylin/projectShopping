package com.example.shoppingweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.shoppingweb.model.*;

@CrossOrigin("*")
@RestController
public class ProductController {

	@Autowired
	ProductService srv;
	
	
	//新增商品
	@PostMapping("/products/add")
	public ResponseEntity<String> addProduct(@RequestBody Product product ){
		try {
			srv.addProduct(product);
			return ResponseEntity.ok("Product added successfully");		
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("Product ID is not unique");
		}			
	}

		
	//查詢-所有商品清單
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return srv.getAllProducts();
	}
	
	// 查詢-根據類別ID查詢商品
	@GetMapping("/products/byptypeid/{ptypeid}")
	public List<Product> getAllProductsByPtypeid(@PathVariable String ptypeid){
		return srv.getAllProductsByPtypeid(ptypeid);
	}
	
	// 查詢-根據主類別(PT1XXX)查詢商品
		@GetMapping("/products/bypmaintypeid/{ptypeid}")
		public List<Product> getAllProductsByPmaintypeid(@PathVariable String ptypeid){
			return srv.getAllProductsByPmaintypeid(ptypeid);
		}
	
	// 查詢-根據商品名稱查詢商品
	@GetMapping("/products/bypname/{pname}")
	public Product getProductByPname(@PathVariable String pname) {
		return srv.getProductByPname(pname);
	}
	
	// 查詢-根據productid 找出整筆資料
		 @GetMapping("/products/{productid}")
		 public ResponseEntity<?> getProductById(@PathVariable String productid){
			 try {
		            Product product = srv.getProductById(productid);
		            return ResponseEntity.ok(product);
		        } catch (Exception e) {
		            return ResponseEntity.badRequest().body("Error getting product: " + e.getMessage());
		        }
		 }
	
		 
		//更新商品
			@PostMapping("/products/update/{productId}")
			public ResponseEntity<String> updateProduct(@PathVariable String productId, @RequestBody Product updatedProduct) {
			    try {
			        srv.updateProduct(productId, updatedProduct);
			        return ResponseEntity.ok("Product updated successfully");
			    } catch (Exception e) {
			        return ResponseEntity.badRequest().body("Error updating product: " + e.getMessage());
			    }
			}
	
		 
	//刪除商品
	@DeleteMapping("/products/{productid}")
	public ResponseEntity<String> deleteProductById(@PathVariable String productid){
		try {
			srv.deleteProductByProductid(productid);
			return ResponseEntity.ok("Product deleted successfully");
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("Error deleting product: " + e.getMessage());
		}
	}

	
}
