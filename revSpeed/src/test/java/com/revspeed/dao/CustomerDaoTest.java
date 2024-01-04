package com.revspeed.dao;

import com.revspeed.model.Customers;
import org.mockito.Mockito;

import java.sql.SQLException;
//without using annotation
//manual approach

public class CustomerDaoTest {
    private CustomerDao dao;

    public static void main(String[] args) throws SQLException {
        new CustomerDaoTest().testGetCustomer();
    }
    public void testGetCustomer() throws SQLException {
        dao = Mockito.mock(CustomerDao.class);

        Customers cus=getCustomer();
        Mockito.when(dao.getCustomer(0)).thenReturn(cus);
        System.out.println(dao.getCustomer(0));
    }
    private Customers getCustomer(){
        Customers cus=new Customers();

        cus.setCustomer_Name("hari");
        cus.setEmail("test@gmail.com");
        cus.setPh_No(343543535);
        cus.setAddress("charlotte");
        cus.setZipcode("12345");
        cus.setCreate_password("5678");
        return cus;
    }




}
