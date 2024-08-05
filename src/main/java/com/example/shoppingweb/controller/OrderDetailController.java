package com.example.shoppingweb.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.shoppingweb.model.Cart;
import com.example.shoppingweb.model.OrderDetail;
import com.example.shoppingweb.model.OrderDetailDAO;
import com.example.shoppingweb.model.ProductDAO;
import com.example.shoppingweb.model.SalesOrder;
import com.example.shoppingweb.model.SalesOrderDAO;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderDetail")
public class OrderDetailController {
	@Autowired
	OrderDetailDAO dDao;
	@Autowired
	SalesOrderDAO dao;
	@Autowired
	ProductDAO pDao;

	@GetMapping("/")
	public ModelAndView gotoIndex() {
		ModelAndView model = new ModelAndView("detailIndex");
		return model;
	}

	// search all detail
	@GetMapping("/queryDetail")
	public List<OrderDetail> getDetail() {
		List<OrderDetail> details = dDao.findAll();
		return details;
	}

	// search detail by sorderId
	@GetMapping("/queryDetail/{id}")
	public List<OrderDetail> getDetailByOrderid(@PathVariable("id") String id) {
		List<OrderDetail> od = dDao.findBysorderId(id);
		return od;
	}

	@PostMapping("/createDetail/{sid}")
	public List<OrderDetail> createDetail(@PathVariable("sid") String sid, @RequestBody List<OrderDetail> details) {
		for (OrderDetail o : details) {
			o.setSorderId(sid);
			o.setSdetailId(sid + o.getSIndex());
			dDao.save(o);
		}
		return details;
	}

	// create Cart
	@PostMapping("/createCart")
	public List<OrderDetail> createCart(@RequestBody List<Cart> carts) {
		System.out.print(carts);
		List<OrderDetail> details = new ArrayList<>();
		int index = 1;
		for (Cart c : carts) {
			String name = pDao.findById(c.getProductId()).get().getPname();
			Integer unitprice = pDao.findById(c.getProductId()).get().getUnitprice();
			OrderDetail od = new OrderDetail();
			od.setSIndex(index++);
			od.setProductId(c.getProductId());
			od.setPname(name);
			od.setQuantity(c.getProductQty());
			od.setUnitPrice(unitprice);
			od.setSubTotal(c.getProductQty() * unitprice);
			details.add(od);
		}
		return details;
	}

	@PostMapping("/updateDetail")
	public List<OrderDetail> updateDetailByid(@RequestParam("amount") Integer amount, @RequestParam("id") String id) {
		OrderDetail d = dDao.findById(id).get();
		d.setQuantity(amount);
		d.setSubTotal(amount * d.getUnitPrice());
		dDao.save(d);
		return getDetail();
	}

	@PostMapping("/deleteDetail")
	public List<OrderDetail> deleteDetailByid(@RequestParam("id") String id) {
		Optional<OrderDetail> orderDetailOptional = dDao.findById(id);
		if (orderDetailOptional.isPresent()) {
			OrderDetail d = orderDetailOptional.get();
			dDao.delete(d);
		}
		return getDetail();
	}

	@PostMapping("/updateDetail/{sorderId}")
	public void updateAllDetailByid(@PathVariable("sorderId") String sorderid, @RequestBody List<OrderDetail> details) {
		System.out.println(sorderid);
		SalesOrder order = dao.findById(sorderid).get();
		List<OrderDetail> dd = dDao.findAll().stream().filter(d -> d.getSorderId().equals(sorderid)).toList();
		for (OrderDetail d : dd) {
			for (OrderDetail ddd : details) {
				if (d.getSdetailId().equals(ddd.getSdetailId())) {
					System.out.println(ddd.getSdetailId());
					d.setQuantity(ddd.getQuantity());
					d.setSubTotal(ddd.getQuantity() * ddd.getUnitPrice());
					dDao.save(d);
				}
			}
		}
	}

}
