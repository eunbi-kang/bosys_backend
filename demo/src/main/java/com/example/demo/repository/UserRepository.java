package com.example.demo.repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // 사용자 이름으로 검색 (쿼리 메서드)
    List<Users> findByName(String name);

    // 특정 나이 이상의 사용자 검색
    List<Users> findByAgeGreaterThanEqual(int age);

}
