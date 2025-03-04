package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders") // ✅ 테이블명 지정
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ✅ 주문 ID (자동 증가)

    @Column(nullable = false, length = 50)
    private String user; // ✅ 주문자 이름

    @Column(nullable = false, length = 50)
    private String book; // ✅ 도서명

    @Column(nullable = false)
    private int quantity; // ✅ 주문 수량 (양수)

    @Column(nullable = false)
    private int price; // ✅ 주문 총 가격

    @Column(nullable = false, updatable = false)
    private LocalDateTime orderDate = LocalDateTime.now(); // ✅ 주문일 (자동 생성)
}
