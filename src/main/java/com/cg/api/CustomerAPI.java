package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.model.Role;
import com.cg.model.dto.CustomerCreateReqDTO;
import com.cg.model.dto.CustomerCreateResDTO;
import com.cg.model.dto.UserDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.role.IRoleService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;



    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CustomerCreateReqDTO customerCreateReqDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Boolean existsByUsername = userService.existsByUsername(customerCreateReqDTO.getUsername());

        if (existsByUsername) {
            throw new EmailExistsException("Account already exists");
        }

        try {
            CustomerCreateResDTO customerCreateResDTO = customerService.create(customerCreateReqDTO);

            return new ResponseEntity<>(customerCreateResDTO, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again");
        }
    }
}
