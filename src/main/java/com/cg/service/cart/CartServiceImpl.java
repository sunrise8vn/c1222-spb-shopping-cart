package com.cg.service.cart;

import com.cg.model.Cart;
import com.cg.model.Customer;
import com.cg.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cart> findByCustomer(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public void delete(Cart cart) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
