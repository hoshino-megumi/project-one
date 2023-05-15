package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
    public static User LoginCheck(String usern, String passw){
        String url = "jdbc:mysql://localhost:3306/userdb";
        String un = "root";
        String psw = "123456";

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs =null;

        User u = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, un, psw);

            String sql = "select id,username,password from userdb.user where username = ? and password = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, usern);
            pst.setString(2, passw);

            rs = pst.executeQuery();
            while (rs.next()){
                u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (connection != null){
                    connection.close();
                }
                if (pst != null){
                    pst.close();
                }
                if (rs != null){
                    rs.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return u;

    }

}
