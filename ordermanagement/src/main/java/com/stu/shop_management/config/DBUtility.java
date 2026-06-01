package com.stu.shop_management.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtility {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/shop_db";

    private static final String USER =
            "postgres";

    private static final String PASSWORD =
            "123456";

    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName(
                    "org.postgresql.Driver"
            );

            con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return con;
    }

    public static void closeConnection(
            ResultSet rs,
            PreparedStatement ps,
            Connection con) {

        try {

            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}