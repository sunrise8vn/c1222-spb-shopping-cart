package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.User;
import com.cg.model.dto.CustomerCreateReqDTO;
import com.cg.model.dto.CustomerCreateResDTO;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer, Long> {

    Optional<Customer> findByUser(User user);

    CustomerCreateResDTO create(CustomerCreateReqDTO customerCreateReqDTO);

}
