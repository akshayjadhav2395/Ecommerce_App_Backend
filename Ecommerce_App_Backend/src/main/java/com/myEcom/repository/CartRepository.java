package com.myEcom.repository;

import com.myEcom.entity.Cart;
import com.myEcom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    public Cart findByCartId(int cartId);
    public Optional<Cart> findByUser(User user);

}
