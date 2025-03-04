package com.example.demo.service;


import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 모든 사용자 조회
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    // 사용자 생성
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    // ID로 사용자 조회
    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // 사용자 이름으로 조회
    public List<Users> getUsersByName(String name) {
        return userRepository.findByName(name);
    }
    // 특정 나이 이상의 사용자 조회
    public List<Users> getUsersByMinAge(int age) {
        return userRepository.findByAgeGreaterThanEqual(age);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
