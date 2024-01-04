package com.revspeed.dao;

import com.revspeed.model.Customers;
import com.revspeed.model.Subscription;
import com.revspeed.service.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriptionImp implements SubscriptionDao {
    // Assume you have a database connection (con) in your DAO class
    // and also CustomerDAO and PlanDAO instances

    // Other methods
    static Connection con
            = DbConnection.getConnection();

    @Override
    public List<Subscription> getCustomerDetail(int customerId) throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String selectQuery = "select *from subscription where customer_id=?";

        try (PreparedStatement ps = con.prepareStatement(selectQuery)) {
            ps.setInt(1, customerId);

            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int subscriptionId = resultSet.getInt("subscription_id");
                    int planId = resultSet.getInt("plan_id");
                    // Add other subscription details as needed


                    Subscription subscription = new Subscription(customerId, planId);
                    subscription.setSubscriptionId(subscriptionId);
                    subscriptions.add(subscription);
                }
            }
        }

        return subscriptions;
    }


    public void subscribeToPlanWithIds(int customerId, int planId) throws SQLException {
        String insertQuery = "INSERT INTO subscription (customer_id, plan_id, start_date, expiry_date) VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY))";

        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, customerId);
            ps.setLong(2, planId);

            // Execute the query
            ps.executeUpdate();
            System.out.println("subscribed successfully.");
        }
    }

    public List<Subscription> getAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String selectQuery = "SELECT * FROM subscriptions";

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                long subscriptionId = resultSet.getLong("subscription_id");
                long customerId = resultSet.getLong("customer_id");
                long planId = resultSet.getLong("plan_id");
                Date startDate=resultSet.getDate("start_date");
                Date endDate=resultSet.getDate("end_date");

                Subscription subscription = new Subscription(customerId, planId,startDate,endDate);
                subscription.setSubscriptionId(subscriptionId);

                subscriptions.add(subscription);
            }
        }

        return subscriptions;
    }
}

