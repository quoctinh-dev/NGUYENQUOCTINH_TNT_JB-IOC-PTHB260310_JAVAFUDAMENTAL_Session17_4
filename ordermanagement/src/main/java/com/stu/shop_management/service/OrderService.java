package com.stu.shop_management.service;

import com.stu.shop_management.dao.OrderDao;
import com.stu.shop_management.entity.Customer;
import com.stu.shop_management.entity.Product;

public class OrderService {

    public boolean addProduct(Product product) {
        return OrderDao.addProduct(product);
    }

    public boolean addCustomer(Customer customer) {
        return OrderDao.addCustomer(customer);
    }

    public boolean updateCustomer(long customerId, Customer customer) {
        return OrderDao.updateCustomer(customerId, customer);
    }

    public boolean createOrder(long customerId, long productId, int quantity) {
        return OrderDao.createOrder(customerId, productId, quantity);
    }

    public void listAllOrders() {
        OrderDao.listAllOrders();
    }

    public void getOrdersByCustomer(long customerId) {
        OrderDao.getOrdersByCustomer(customerId);
    }
}