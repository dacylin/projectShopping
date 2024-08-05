package com.example.shoppingweb.model;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductService
{

	@Autowired
	ProductDAO dao;

	public Page<Product> getUsersPageable(int page) {
		Pageable pageable = PageRequest.of(page, 5); // 每頁顯示 5 筆資料
		return dao.findAll(pageable);
	}

	//新增商品	
	public Product addProduct(Product product) {
		List<Product> products=dao.findAll();
		
		products=dao.findAll();
		String max=products.stream()
				.map(p->p.getProductid()).toList().stream()			
				.max(Comparator.comparing(i->i)).get();
		Integer serialNum=Integer.parseInt(max.substring(1, 4))+1;
		product.setProductid("P"+String.format("%03d", serialNum));
		return dao.save(product);
	}

	
	//查詢-所有商品清單
		public List<Product> getAllProducts() {
			return dao.findAll();
		}
	
	// 查詢-根據類別ID查詢商品
	public List<Product> getAllProductsByPtypeid(String ptypeid){
		return dao.findByPtypeid(ptypeid);
	}
	
	// 查詢-根據主類別(PT1XXX)查詢商品
		public List<Product> getAllProductsByPmaintypeid(String ptypeid){
			String maintype = ptypeid.substring(0,3);
			return dao.findByPtypeidStartingWith(maintype);
		}
	
	// 查詢-根據商品名稱查詢商品
	public Product getProductByPname(String pname) {
		return dao.findByPname(pname);
	}
	
	// 查詢-根據productid 找出整筆資料
		public Product getProductById(String productid) throws Exception{
			Product product=dao.findById(productid).orElse(null);
			if(product==null) {
				throw new Exception("Product not found");
			}
			return product;
		}
		
		
		//更新商品資料
		public void updateProduct(String productid,Product updatedProduct)throws Exception{
			boolean productExists=dao.existsById(productid);			
			if(productExists) {
				if(!productid.equals(updatedProduct.getProductid())) {		
					throw new Exception("Product ID cannot be changed");
				}
				
				Product existingProduct=dao.findById(productid).orElse(null);	
				if(existingProduct!=null) {
					existingProduct.setPname(updatedProduct.getPname());
					existingProduct.setPqty(updatedProduct.getPqty());
					existingProduct.setPtext(updatedProduct.getPtext());
					existingProduct.setUnitprice(updatedProduct.getUnitprice());
					existingProduct.setEnabled(updatedProduct.getEnabled());
					dao.save(existingProduct);
				}else {
					throw new Exception("Product not found");
				}
			}else {
				throw new Exception("Product does not exist");
			}
		}
	
	
	//刪除商品
	public void deleteProductByProductid(String productid) throws Exception{
		boolean productExists=dao.existsById(productid);
		
		if(productExists) {
			dao.deleteById(productid);
		}else {
			throw new Exception("Product does not exist");
		}
	}
	
}
	
