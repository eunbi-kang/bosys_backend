package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping             /* GET 방식으로 Mapping */
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 사용자 생성
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        Users createUser = userService.createUser(users);
        return  new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    // ID로 사용자 조회
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // 사용자 이름으로 조회
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Users>> getUsersByName(@PathVariable String name) {
        List<Users> users = userService.getUsersByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 특정 나이 이상의 사용자 조회
    @GetMapping("/age/{age}")
    public ResponseEntity<List<Users>> getUsersByMinAge(@PathVariable int age) {
        List<Users> users = userService.getUsersByMinAge(age);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
