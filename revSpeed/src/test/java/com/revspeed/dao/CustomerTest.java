package com.revspeed.dao;

import com.revspeed.model.Customers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

public class CustomerTest {
    @Mock
    private CustomerTest dao;
//mocking using annotatoin
    public static void main(String[] args) throws SQLException {
        new CustomerDaoTest().testGetCustomer();
    }
    public void testGetCustomer() throws SQLException {
//        dao = Mockito.mock(CustomerTest.class);
        //initMock--deprecated--used to validate in mockito 2
        //openMocks --used in mockito 3
        MockitoAnnotations.openMocks(this);

        Customers cus=getCustomer();
        Mockito.when(dao.getCustomer()).thenReturn(cus);
        System.out.println(dao.getCustomer());
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
