package com.cg.service.bill;

import com.cg.model.Bill;
import com.cg.model.Customer;
import com.cg.service.IGeneralService;

public interface IBillService extends IGeneralService<Bill, Long> {

    void pay(Customer customer);
}
