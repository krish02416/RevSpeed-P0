package com.revspeed.dao;

import com.revspeed.model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao {
     public List<Subscription> getCustomerDetail(int customerId) throws SQLException;
     void subscribeToPlanWithIds(int customerId, int planId) throws SQLException;
     List<Subscription> getAllSubscriptions() throws SQLException;
}
