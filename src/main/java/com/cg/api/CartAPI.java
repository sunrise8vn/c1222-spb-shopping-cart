package com.cg.api;


import com.cg.exception.UnauthorizedException;
import com.cg.model.Cart;
import com.cg.model.Customer;
import com.cg.model.User;
import com.cg.model.dto.CartItemReqDTO;
import com.cg.model.dto.CartResDTO;
import com.cg.service.cart.ICartService;
import com.cg.service.cartItem.ICartItemService;
import com.cg.service.customer.ICustomerService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;


    @PostMapping("/add-item")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<?> addItem(@RequestBody CartItemReqDTO cartItemReqDTO) {

        String username = appUtils.getPrincipalUsername();

        User user = userService.findByUsername(username).orElseThrow(() -> {
           throw new UnauthorizedException("Bạn chưa xác thực");
        });

        Customer customer = customerService.findByUser(user).orElseThrow(() -> {
            throw new UnauthorizedException("Bạn chưa xác thực");
        });

        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        if (cartOptional.isEmpty()) {
            CartResDTO cartResDTO = cartItemService.create(cartItemReqDTO, customer);
            return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
        }
        else {
            CartResDTO cartResDTO = cartItemService.update(cartItemReqDTO, customer, cartOptional.get());
            return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
        }

    }
}
