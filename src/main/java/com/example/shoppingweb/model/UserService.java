package com.example.shoppingweb.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.shoppingweb.controller.UserController;

@Service
public class UserService {
	List<User> Users=new ArrayList<>();
	public static String userID;
	
    @Autowired
    private UserDAO dao;

    // 取得分頁使用者資料
    public Page<User> getUsersPageable(int page) {
        Pageable pageable = PageRequest.of(page, 5); // 每頁顯示 5 筆資料
        return dao.findAll(pageable);
    }

	public List<User> getAllUsers() {
		return dao.findAll();
	}

	public List<User> getAllUsersByUserName(String userName) {
		return dao.findByUserName(userName);
	}
	
	public User getAllUsersByUserNameAndPassword(@PathVariable("userName") String userName,@PathVariable("password") String password){
		return dao.findByUserNameAndPassword(userName, password);	
		
	}
	
	public User getqueryByEmployeeID(String employeeID) {
		return dao.queryByEmployeeID(employeeID);
	}

	public User getqueryByUsersByUserNameAndPassword(String userName, String password) {
		return dao.queryUserNameAndPassword(userName,password);
	}
	
    // 新增使用者
    public User addUser(User user)throws Exception {
       	boolean userNameExists=dao.existsByUserName(user.getUserName());
   		if(! userNameExists) {
	    	//資料庫最大會員編號
	    	Users = dao.findAll();
	    	String max=Users.stream().map(u->u.getEmployeeID()).toList().stream().max(Comparator.comparing(i->i)).get();
	    	//編輯訂單編號
	    	Integer serialNum = Integer.parseInt(max.substring(1,4))+1;
	    	user.setEmployeeID("U"+String.format("%03d", serialNum));
	    	
	    	user.setDepartment("E");
	    	
	    	user.setCreatedUser(userID);
   	     	user.setCreatedAt(LocalDateTime.now());
	    	user.setEditUser(userID);
   	    	user.setUpdatedAt(LocalDateTime.now());
   	    	user.setEnabled(true);
   	        return dao.save(user);
   		} else {
   			throw new Exception("UserName is duplicated");
   		}
    	
    }

    // 更新使用者
    public void updateUser(User user) throws Exception{
    	String employeeID=user.getEmployeeID();
    	boolean userExists=dao.existsByEmployeeID(employeeID);
    	if(userExists) {
    		User existingUser=dao.findByEmployeeID(employeeID);
    		System.out.println(user.getDepartment()+" / "+ user.getEnabled());;
    		if(user.getDepartment().length()>0) {
        		existingUser.setDepartment(user.getDepartment());
    		}
    		
    		existingUser.setName(user.getName());
    		existingUser.setEmail(user.getEmail());
    		existingUser.setUserName(user.getUserName());
    		existingUser.setPassword(user.getPassword());

     		existingUser.setUpdatedAt( LocalDateTime.now());
    		existingUser.setEditUser(userID);
    		
    		if(user.getEnabled() !=null) {
    			existingUser.setEnabled(user.getEnabled());
    		}
    		
			dao.save(existingUser);
		}else {
			throw new Exception("User does not exist");
		}   	
     }
    // 刪除使用者
    public void deleteUser(String employeeID) throws Exception {
    	boolean userExists=dao.existsByEmployeeID(employeeID);
		if(userExists) {
			dao.deleteById(employeeID);
		}else {
			throw new Exception("User does not exist");
		}	
     }
    
}    

