package com.example.shoppingweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.shoppingweb.model.*;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
	// private List<Member> memberList = new ArrayList<>();
	@Autowired
	MemberDAO dao;

	@GetMapping
	public List<Member> getAllMember() {
		return dao.findAll();
	}

	@GetMapping("/id/{id}")
	public Optional<Member> getMemberById(@PathVariable Integer id) {
		return (dao.findById(id));
	}

	@PostMapping()
	public ResponseEntity<?> addMember(@RequestBody Member m) {
		Member ex = dao.findByEmail(m.getEmail());
		if (ex != null) {
			// return ResponseEntity.badRequest().body(new ErrorResponse(false, "此 EMAIL
			// 帳戶已經被使用了"));
			return ResponseEntity.ok().body(new ErrorResponse(false, "此 EMAIL帳戶已經被使用了，請用其他帳號註冊", "duplicate_email"));
		} else {
			dao.save(m);
			return ResponseEntity.ok().body(new SuccessResponse(true, "EMAIL 帳號註冊成功"));
		}
	}

	@PostMapping("/changePassword")
	@Transactional
	public ResponseEntity<?> changeMemberPassword(@RequestParam("email") String email, @RequestParam("nid") String nid,
			@RequestParam("password") String newPassword) {
		Member m = dao.findByEmail(email);
		if (m == null) {
			return ResponseEntity.ok(new ErrorResponse(false, "無此email帳號", "no_email"));
		}
		// 驗證nid
		if (!m.getNid().equals(nid)) {
			return ResponseEntity.ok(new ErrorResponse(false, "Social Security Number不正確", "wrong_nid"));
		}
		// 更新密碼
//		System.out.println("newPassword:  " + newPassword + " |  原來pwd: " + m.getEmail());
		int updatedRows = dao.changePassword(m.getEmail(), newPassword);
//		System.out.println(" int update-Row: " + updatedRows);

		if (updatedRows > 0) {
			return ResponseEntity.ok(new SuccessResponse(true, "密碼變更成功"));
		} else {
			return ResponseEntity.ok(new ErrorResponse(false, "密碼更新失敗", "error"));
		}

	}

	@PutMapping("/email/{email}")
	public Member updateEmployee(@PathVariable("email") String email, @RequestBody Member m) {
		Member ex = dao.findByEmail(email);
		if (m != null) {
			// ex.setMemberid(m.getMemberid());
			ex.setAddress(m.getAddress());
			ex.setBirthday(m.getBirthday());
			ex.setMobile(m.getMobile());
			ex.setEmail(m.getEmail());
			ex.setName(m.getName());
			ex.setNid(m.getNid());
			ex.setActive(true);
			ex.setPassword(m.getPassword());
		}
		System.out.println("update:" + ex);
		dao.save(ex);
		return ex;
	}

	@PostMapping("/upMember")
	@Transactional
	public ResponseEntity<?> updateMemberData(@RequestParam("email") String email, @RequestParam("nid") String nid,
			@RequestParam("password") String newPassword) {
		Member m = dao.findByEmail(email);
		if (m == null) {
			return ResponseEntity.ok(new ErrorResponse(false, "無此email帳號", "no_email"));
		}

		// 更新密碼
		System.out.println("newPassword:  " + newPassword + " |  原來pwd: " + m.getEmail());
		int updatedRows = dao.changePassword(m.getEmail(), newPassword);
		System.out.println(" int update-Row: " + updatedRows);

		if (updatedRows > 0) {
			return ResponseEntity.ok(new SuccessResponse(true, "密碼變更成功"));
		} else {
			return ResponseEntity.ok(new ErrorResponse(false, "密碼更新失敗", "error"));
		}

	}

	@DeleteMapping("/{email}")
	public void deleteMember(@PathVariable String email) {
		Member m = dao.findByEmail(email);
		if (m != null)
			dao.delete(m);
	}

	@PostMapping("/login")
	public ResponseEntity memberLogin(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session) {

		// System.out.print("email:" + email + " password:" + password+"/n");

		Member m = dao.findByEmail(email);
		if (m == null) // 找不到 email
			return ResponseEntity.notFound().build(); // 404 NOT Found

		// System.out.print("m.Password:" + m.getPassword() +"/n");
		// System.out.print("form.Password:" + password + "/n");

		if (m.getPassword().equals(password)) { // 驗證密碼是否正確
			session.setAttribute("loginmember", m);
			// System.out.print("驗證密碼正確" + "\n");
			return ResponseEntity.ok(m);
		} else {
			// System.out.print("驗證密碼 Error" + "\n");
			return ResponseEntity.badRequest().build(); // "status": 400, "error": "Bad Request"
		}
		/*
		 * 可以用字串比對 Null: 未註冊 404=> register.html Failed: 密碼錯誤。 400=> 忘記密碼
		 * updateMember.html updatePassword.html
		 */
	}

}

class ErrorResponse {
	private boolean success;
	private String message;
	private String errorType;

	public ErrorResponse(boolean success, String message, String errorType) {
		this.success = success;
		this.message = message;
		this.errorType = errorType;
	}

	public ErrorResponse() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

class SuccessResponse {

	private boolean success;
	private String message;

	public SuccessResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public SuccessResponse() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}