package com.cg.service.cartItem;

import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Customer;
import com.cg.model.dto.CartItemReqDTO;
import com.cg.model.dto.CartResDTO;
import com.cg.service.IGeneralService;

public interface ICartItemService extends IGeneralService<CartItem, Long> {

    CartResDTO create(CartItemReqDTO cartItemReqDTO, Customer customer);

    CartResDTO update(CartItemReqDTO cartItemReqDTO, Customer customer, Cart cart);
}
