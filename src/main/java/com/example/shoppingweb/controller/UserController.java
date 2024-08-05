package com.example.shoppingweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingweb.model.UserService;

import jakarta.servlet.http.HttpSession;

import com.example.shoppingweb.model.*;

@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserService srv;
	HttpSession mysession;
	String loginuser;
	//手動執行http://localhost:8080/users
	//查詢全部使用者
	@GetMapping("/users")
	public List<User> getAllUsers() {
	  if(mysession!=null)
	  {
		  
	  }
	   
	   return srv.getAllUsers();
	}
	
	//手動執行 http://localhost:8080/users/userName/bb
	//【使用者帳戶+密碼】查使用者個人資料
	@GetMapping("/users/userName/{userName}")
	public List<User> getAllUsersByUserName(@PathVariable String userName) {
		System.out.println("getAllUsersByUserName:"+userName);
		return srv.getAllUsersByUserName(userName);
	}

	//手動執行 http://localhost:8080/users/employeeID/U005
	@GetMapping("/users/employeeID/{employeeID}")
	public User getqueryByEmployeeID(@PathVariable("employeeID") String employeeID){
		return srv.getqueryByEmployeeID(employeeID);
		
	}	
	
/*	
	//手動執行 http://localhost:8080/users/login/zz/zz
	//【使用者帳戶+密碼】查使用者個人資料
	@GetMapping("/users/login/{userName}/{password}")
	public User getAllUsersByUserNameAndPassword(@PathVariable("userName") String userName,@PathVariable("password") String password){
		return srv.getAllUsersByUserNameAndPassword(userName, password);	
	}
*/
	//【使用者帳戶+密碼】查使用者個人資料
	   @PostMapping("/users/login")
	    public ResponseEntity findUser(@RequestParam("userName")String userName,@RequestParam("password")String password,HttpSession session)
	    {
		   loginuser="No session";
		   User u=srv.getqueryByUsersByUserNameAndPassword(userName, password);
	    	if(u!=null) {
	    		session.setAttribute("loginuser", u);
	    		mysession=session;
	    		loginuser=u.getEmployeeID()+","+u.getDepartment();
	    		UserService.userID=u.getEmployeeID();
	    		
	    		System.out.println(loginuser+'-'+"session~~"+mysession);
	    		return ResponseEntity.ok(u);
	    	}else {
	    		return ResponseEntity.notFound().build();
	    	}
	    }
	
	/*手動執行 http://localhost:8080/users
	 * 
    {
        "department": "E",
        "name": "員工11",
        "email": "gg1@gmail.com.tw",
        "userName": "gg1",
        "password": "gg1",
        "enabled": true
    }	 * */
	//新增使用者
	@PostMapping("/users")
	public ResponseEntity<String> addUser(@RequestBody User user ){
		try {
			srv.addUser(user);
			return ResponseEntity.ok("User added successfully");		
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("User ID is not unique");
		}			
	}
	
	/*http://localhost:8080/users/U012
   {
        "employeeID": "U012",
        "department": "E",
        "name": "員工11",
        "email": "gg1@gmail.com.tw",
        "userName": "gg1",
        "password": "gg1",
        "createdAt": "2024-03-12T23:53:17.302684",
        "createdUser": null,
        "updatedAt": "2024-03-13T01:41:46.911867",
        "editUser": null,
        "enabled": false
    }	*/
	//修改使用者訊息
	@PostMapping("/users/{employeeID}")
	public ResponseEntity<String> updateUser(@PathVariable String employeeID,@RequestBody  User user  ){
		try {
			srv.updateUser(user);
            return ResponseEntity.ok("User updated successfully"); 	
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("User ID is not unique");
		}
                     
	}	
	//http://localhost:8080/users/U016
	//刪除使用者訊息
	@DeleteMapping("/users/{employeeID}")
	public ResponseEntity<String> deleteProductBtId(@PathVariable String employeeID){
		try {
			srv.deleteUser(employeeID);
			return ResponseEntity.ok("User deleted successfully");
		}catch (Exception e){
			return ResponseEntity.badRequest().body("Error deleting User:"+e.getMessage());
		}
	}
	
	//http://localhost:8080/users/session
	//session有值傳→ 員工ID,權限(E員工/M管理者)
	@GetMapping("/users/session")
	public  ResponseEntity<String> ChedkMysession(){
		String mes="No session";
		try {
			  if(mysession!=null)
			  {
				  mes=loginuser;
			  }
			
			return ResponseEntity.ok(mes);
		}catch (Exception e){
			return ResponseEntity.badRequest().body(mes);
		}
	}

}
