package com.revspeed.dao;

import com.revspeed.model.Customers;
import com.revspeed.service.DatabaseConnection;
import com.revspeed.service.DbConnection;

import javax.swing.*;
import java.io.StringBufferInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerImp implements CustomerDao {

    static Connection con
            = DbConnection.getConnection();

    @Override
    public void add(Customers cus) throws SQLException {
        System.out.println("inside insert");
        String insert = "INSERT INTO Customers(customer_name, ph_no, email, address, zipcode, create_password) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(insert)) {
            // Use the actual values from the Customers object
            ps.setString(1, cus.getCustomer_Name());
            ps.setLong(2, cus.getPh_No());
            ps.setString(3, cus.getEmail());
            ps.setString(4, cus.getAddress());
            ps.setString(5, cus.getZipcode());
            ps.setString(6, cus.getCreate_password());

            int res = ps.executeUpdate();
            System.out.println("res"+res);
            System.out.println("inserted successfully");
//            return void;
        }
    }

        @Override
        public void delete (int id) throws SQLException {
            System.out.println("delete started");
            String delete="delete from customers where customer_id=?";
            try(PreparedStatement ps=con.prepareStatement(delete)){
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("data deleted.");
            }

        }

//
        @Override
        public Customers getCustomer (int cid) throws SQLException {
            System.out.println("profile view");
            String select = "select * from Customers where customer_id=?";
            //created a object scope level
            Customers cus;
            try (PreparedStatement ps = con.prepareStatement(select)) {
                ps.setInt(1,cid);
//                ps.setString(1, user);
//                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                cus = null;

                while (rs.next()) {
                    String name = rs.getString(2);
                    long ph =Long.parseLong(rs.getString(3));
                    String email = rs.getString(4);
                    String address=rs.getString(5);
                    String zipcode=rs.getString(6);
                    cus = new Customers();

                    cus.setCustomer_Name(name);
                    cus.setPh_No(ph);
                    cus.setEmail(email);
                    cus.setAddress(address);
                    cus.setZipcode(zipcode);

//                    System.out.println(rs.getString(2));
//                    System.out.println(rs.getString("ph_no"));
//                    System.out.println(rs.getString("email"));
//                    System.out.println(rs.getString("address"));
//                    System.out.println(rs.getString("zipcode"));

                }

            }
            return cus;
        }
// @Override
//        public void update(String user,String pass,long ph_no) throws SQLException {
//              String update="update customers set ph_no=? where customer_name=? and create_password=?";
//
//              try(PreparedStatement ps=con.prepareStatement(update)){
//                  ps.setLong(1,ph_no);
//                  ps.setString(2,user);
//                  ps.setString(3,pass);
//                  ps.executeUpdate();
//                  System.out.println("updated successfully");
//              }
//        }
 public void update(int cid,long ph_no) throws SQLException {
     String update="update customers set ph_no=? where Customer_id=?";

     try(PreparedStatement ps=con.prepareStatement(update)){
         ps.setLong(1,ph_no);
         ps.setInt(2,cid);

         ps.executeUpdate();
         System.out.println("updated successfully");
     }
 }


    @Override
    public int authenticateCustomer(String user, String password) throws SQLException {
        String find_id="select customer_id from Customers where email =? AND create_password =?";
        try (PreparedStatement ps=con.prepareStatement(find_id)){
            ps.setString(1,user);
            ps.setString(2,password);

            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    return rs.getInt("Customer_id");
                }
            }
        }
        return -1;//if not valid
    }

    @Override
    public void updatepass(Customers newpass,int cid) throws SQLException {
        String password="update customers set create_password=? where customer_id=?";
        try(PreparedStatement ps= con.prepareStatement(password)){

            ps.setString(1, newpass.getCreate_password());
            ps.setInt(2,cid);
            System.out.println("PASSWORD UPDATED SUCCESSFULLY.");

            ps.executeUpdate();
        }
    }
//        @Override
//        public List<Customers> getCustomers () throws SQLException {
//            return null;
//        }


    }

