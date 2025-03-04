package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // ✅ 모든 주문 조회
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ 주문 생성 (예외 처리 강화)
    public Orders createOrder(Orders order) {
        try {
            validateOrder(order);
            return orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("주문 생성 실패: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("서버 오류로 인해 주문을 생성할 수 없습니다.", e);
        }
    }

    // ✅ 주문 ID로 조회 (Optional 반환 대신 예외 처리)
    public Orders getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 주문을 찾을 수 없습니다: " + id));
    }

    // ✅ 특정 사용자(user)의 주문 조회
    public List<Orders> getOrdersByUser(String user) {
        return orderRepository.findByUser(user);
    }

    // ✅ 특정 도서(book)의 주문 조회
    public List<Orders> getOrdersByBook(String book) {
        return orderRepository.findByBook(book);
    }

    // ✅ 특정 가격 이상의 주문 조회
    public List<Orders> getOrdersByMinPrice(int price) {
        return orderRepository.findByPriceGreaterThanEqual(price);
    }

    // ✅ 주문 삭제 (존재하지 않는 주문 삭제 시 예외 처리)
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 ID의 주문이 존재하지 않습니다: " + id);
        }
        orderRepository.deleteById(id);
    }

    // ✅ 입력값 검증 (잘못된 데이터 방지)
    private void validateOrder(Orders order) {
        if (order.getUser() == null || order.getUser().trim().isEmpty()) {
            throw new IllegalArgumentException("사용자 이름이 비어 있습니다.");
        }
        if (order.getBook() == null || order.getBook().trim().isEmpty()) {
            throw new IllegalArgumentException("도서명이 비어 있습니다.");
        }
        if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("주문 수량은 1 이상이어야 합니다.");
        }
        if (order.getPrice() < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
        if (order.getOrderDate() == null) {
            order.setOrderDate(java.time.LocalDateTime.now());
        }
    }
}
