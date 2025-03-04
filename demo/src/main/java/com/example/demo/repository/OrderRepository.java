package com.example.demo.repository;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    // ✅ 특정 사용자(user)의 주문 조회
    List<Orders> findByUser(String user);

    // ✅ 특정 도서(book)의 주문 조회
    List<Orders> findByBook(String book);

    // ✅ 특정 가격 이상의 주문 조회
    List<Orders> findByPriceGreaterThanEqual(int price);
}
