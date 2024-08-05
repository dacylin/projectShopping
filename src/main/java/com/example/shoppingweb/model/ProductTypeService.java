package com.example.shoppingweb.model;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeService {

	@Autowired
	ProductTypeDAO typedao;
	
	// 查詢-所有商品類別
	public List<ProductType> getAllProductType() {
		return typedao.findAll();
	}
	
	//查詢-指定商品類別（模糊查詢）
		public List<ProductType> getAllProductsByPmaintypeid(String ptypeid){
			String maintype = ptypeid.substring(0,3);
			return typedao.findByPtypeidStartingWith(maintype);
		}
	
	//查詢-指定商品類別
	public ProductType getProductTypeByPtypeid(String ptypeid) {
		return typedao.findByPtypeid(ptypeid);
	}

	// 新增商品類別
	public ProductType addProductType(ProductType productType) {
		List<ProductType> productTypes=typedao.findAll();
		
		String max=productTypes.stream()
				.map(ProductType::getPtypeid)
				.max(Comparator.comparing(String::valueOf))
                .orElse("0");
		
		int newPtypeid = Integer.parseInt(max.substring(2)) + 1;
        String newPtypeIdString = String.format("PT%03d", newPtypeid);
        productType.setPtypeid(newPtypeIdString);

	
        return typedao.save(productType);
	}

	
	// 修改商品類別
    public ProductType updateProductType(String ptypeid,ProductType productType ) throws Exception {
        ProductType existingProductType  = typedao.findById(ptypeid).orElse(null);

        if (existingProductType != null) {
            existingProductType.setPtypename(productType.getPtypename());
            existingProductType.setEnabled(productType.getEnabled());
            return typedao.save(existingProductType);
        } else {
        	throw new Exception("Product type does not exist");
        }
    }
    
    // 刪除商品類別
    public void deleteProductType(String ptypeid) throws Exception {
        ProductType producttype = typedao.findById(ptypeid).orElse(null);

        if (producttype != null) {
            typedao.deleteById(ptypeid);
        } else {
            throw new Exception("Product type does not exist");
        }
    }
   
}
