package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.Role;
import com.cg.model.User;
import com.cg.model.dto.CustomerCreateReqDTO;
import com.cg.model.dto.CustomerCreateResDTO;
import com.cg.model.enums.ERole;
import com.cg.repository.CustomerRepository;
import com.cg.repository.RoleRepository;
import com.cg.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByUser(User user) {
        return customerRepository.findByUser(user);
    }

    @Override
    public CustomerCreateResDTO create(CustomerCreateReqDTO customerCreateReqDTO) {

        Role role = roleRepository.findByName(ERole.ROLE_CUSTOMER);

        User user = new User();
        user.setUsername(customerCreateReqDTO.getUsername());
        user.setPassword(customerCreateReqDTO.getPassword());
        user.setRole(role);
        userService.save(user);

        Customer customer = new Customer();
        customer.setPhone(customerCreateReqDTO.getPhone());
        customer.setFullName(customerCreateReqDTO.getFullName());
        customer.setUser(user);
        customerRepository.save(customer);

        CustomerCreateResDTO customerCreateResDTO = new CustomerCreateResDTO();
        customerCreateResDTO.setId(customer.getId());
        customerCreateResDTO.setUsername(user.getUsername());
        customerCreateResDTO.setFullName(customer.getFullName());
        customerCreateResDTO.setPhone(customer.getPhone());

        return customerCreateResDTO;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
