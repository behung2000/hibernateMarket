package BLL;

import hibernatebanhang.DAL.Customers;
import hibernatebanhang.DAL.CustomersDAL;

import java.util.List;

public class CustomersBLL {
    private CustomersDAL customersDAL;

    public CustomersBLL() {
        customersDAL = new CustomersDAL();
    }

    public List<Customers> getList() {
        return customersDAL.getList();
    }

    private Customers getCustomers(int id) {
        return customersDAL.getCustomers(id);
    }
}
