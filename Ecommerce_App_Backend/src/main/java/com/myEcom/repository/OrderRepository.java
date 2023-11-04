package com.myEcom.repository;

import com.myEcom.entity.Order;
import com.myEcom.entity.User;
import com.myEcom.payload.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public Order findByOrderId(int orderId);
    public List<Order> findByUser(User user);
}
