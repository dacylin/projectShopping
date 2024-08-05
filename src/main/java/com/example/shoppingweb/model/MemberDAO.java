package com.example.shoppingweb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberDAO extends JpaRepository<Member, Integer>{
	
    //使用了Spring Data JPA的自动化命名规则，根据方法名生成了一个查询
    // 使用自動化命名規則進行條件搜尋
    Member findByEmail (String email);
    Member findByName (String name);
    Member findByEmailAndPassword (String email,String password);

    // 使用自動化命名規則進行條件搜尋(多條件)
    //List<Member> findByNameAndAge(String name, Integer age);
    //List<Member> findByNameOrAge(String name, Integer age);

    // 自定義SQL查詢，把 ?1" 帶入 queryByName(String name)
    @Query(value = "select * from members where email = ?1", nativeQuery = true)
    Member queryByEmail(String email);
    
    @Modifying
    @Query("UPDATE Member m SET m.password = :newPassword WHERE m.email = :email")
    int changePassword(@Param("email") String email, @Param("newPassword") String newPassword);

}