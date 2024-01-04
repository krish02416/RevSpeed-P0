package com.revspeed.dao;

import com.revspeed.model.Customers;

import java.sql.SQLException;

public interface CustomerDao {

    //insert single customers
    public void add(Customers cus)
            throws SQLException;
    public void delete(int id)
            throws SQLException;
    //single customer
    public Customers getCustomer(int cid)
            throws SQLException;
//    //all customer
//    public List<Customers> getCustomers()
//            throws SQLException;
//    public void update(String username,String password,long ph_no)
//            throws SQLException;
void update(int cid,long ph)
        throws SQLException;
    int authenticateCustomer(String email,String password) throws SQLException;

    void updatepass(Customers newpass, int cid) throws SQLException;
}
