package com.stu.shop_management.dao;

import com.stu.shop_management.config.DBUtility;
import com.stu.shop_management.entity.Customer;
import com.stu.shop_management.entity.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDao {

    public static boolean addProduct(Product product) {
        Connection con = null;
        PreparedStatement psCheck = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;
        try {
            con = DBUtility.getConnection();
            psCheck = con.prepareStatement("select * from products where product_name=?");
            psCheck.setString(1, product.getProductName());
            rs = psCheck.executeQuery();

            if (rs.next()) {
                System.out.println("Product already exists!");
                return false;
            }

            psInsert = con.prepareStatement("insert into products(product_name,price) values(?,?)");
            psInsert.setString(1, product.getProductName());
            psInsert.setBigDecimal(2, product.getPrice());
            return psInsert.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psCheck != null) {
                try { psCheck.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            DBUtility.closeConnection(rs, psInsert, con);
        }
        return false;
    }

    public static boolean addCustomer(Customer customer) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtility.getConnection();
            ps = con.prepareStatement("insert into customers(customer_name,email) values(?,?)");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Email already exists!");
        } finally {
            DBUtility.closeConnection(null, ps, con);
        }
        return false;
    }

    public static boolean updateCustomer(long customerId, Customer customer) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtility.getConnection();
            ps = con.prepareStatement("update customers set customer_name=?, email=? where customer_id=?");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getEmail());
            ps.setLong(3, customerId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtility.closeConnection(null, ps, con);
        }
        return false;
    }

    public static boolean createOrder(long customerId, long productId, int quantity) {
        Connection con = null;
        PreparedStatement psCheck = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;
        try {
            con = DBUtility.getConnection();
            psCheck = con.prepareStatement("select price from products where product_id=?");
            psCheck.setLong(1, productId);
            rs = psCheck.executeQuery();

            if (rs.next()) {
                BigDecimal price = rs.getBigDecimal("price");
                BigDecimal total = price.multiply(new BigDecimal(quantity));

                psInsert = con.prepareStatement("insert into orders(customer_id,order_date,total_amount) values(?,CURRENT_DATE,?)");
                psInsert.setLong(1, customerId);
                psInsert.setBigDecimal(2, total);
                return psInsert.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psCheck != null) {
                try { psCheck.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            DBUtility.closeConnection(rs, psInsert, con);
        }
        return false;
    }

    public static void listAllOrders() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtility.getConnection();
            ps = con.prepareStatement("select o.order_id,c.customer_name,o.order_date,o.total_amount from orders o join customers c on o.customer_id=c.customer_id");
            rs = ps.executeQuery();
            System.out.println("ORDER_ID | CUSTOMER | ORDER_DATE | TOTAL_AMOUNT");
            while (rs.next()) {
                System.out.println(rs.getLong("order_id") + " | " + rs.getString("customer_name") + " | " + rs.getDate("order_date") + " | " + rs.getBigDecimal("total_amount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtility.closeConnection(rs, ps, con);
        }
    }

    public static void getOrdersByCustomer(long customerId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtility.getConnection();
            ps = con.prepareStatement("select * from orders where customer_id=?");
            ps.setLong(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("order_id") + " | " + rs.getDate("order_date") + " | " + rs.getBigDecimal("total_amount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtility.closeConnection(rs, ps, con);
        }
    }
}