package com.example.demo.controller;

import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000") // React 연동을 위한 CORS 설정
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ 모든 주문 조회
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    // ✅ 주문 추가 (예외 처리 강화)
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Orders order) {
        try {
            Orders createdOrder = orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) { // 예기치 않은 예외 처리 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
        }
    }

    // ✅ 주문 ID로 조회
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") Long id) {
        Optional<Orders> order = Optional.ofNullable(orderService.getOrderById(id));
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ✅ 특정 사용자(user)의 주문 조회
    @GetMapping("/user/{user}")
    public ResponseEntity<List<Orders>> getOrdersByUser(@PathVariable("user") String user) {
        List<Orders> orders = orderService.getOrdersByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    // ✅ 특정 도서(book)의 주문 조회
    @GetMapping("/book/{book}")
    public ResponseEntity<List<Orders>> getOrdersByBook(@PathVariable("book") String book) {
        List<Orders> orders = orderService.getOrdersByBook(book);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    // ✅ 특정 가격 이상의 주문 조회
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Orders>> getOrdersByMinPrice(@PathVariable("price") int price) {
        List<Orders> orders = orderService.getOrdersByMinPrice(price);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    // ✅ 주문 삭제 (204 No Content로 변경)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build(); // 204 응답 (본문 없음)
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
