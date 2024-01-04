package com.revspeed.dao;

import com.revspeed.model.Plans;
import com.revspeed.service.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlansImp implements PlansDao {

    static Connection con
            = DbConnection.getConnection();

    @Override
    public void addPlan(Plans plan) {

    }

    @Override
    public void updatePlan(Plans plan) {

    }

    @Override
    public void deletePlan(long planId) {

    }

    @Override
    public Plans getPlanById(int pid) throws SQLException {
        String find_id = "SELECT * FROM plans WHERE plan_id=?";
        Plans plan = new Plans();
        try (PreparedStatement ps = con.prepareStatement(find_id)) {
            ps.setInt(1, pid);

            ResultSet resultSet = ps.executeQuery();
            System.out.printf("|%-2s| %-20s | %-5s | %-8s | %-10s | %-10s | %-6s | %-10s | %-15s | %-6s | %-10s |%n", "Plan_id",
                    "Plan Name", "Speed", "Validity", "Data Limit", "FUP", "Price", "Quota", "No. of Devices", "Stream", "No. of OTT");
            System.out.println("|-------|----------------------|-------|----------|------------|------------|--------|------------|---------------|--------|------------|");
//            int id=0;
            while (resultSet.next()) {


                // Retrieve the id directly from the ResultSet
                int id = resultSet.getInt("plan_id");


                // Set the other properties of the plan object
                plan.setPlanName(resultSet.getString("plan_name"));
                plan.setSpeed(resultSet.getInt("speed"));
                plan.setValidity(resultSet.getInt("validity"));
                plan.setData_limit(resultSet.getString("data_limit"));
                plan.setFup(resultSet.getString("fup"));
                plan.setPrice(resultSet.getDouble("price"));
                plan.setQuata(resultSet.getString("quata"));
                plan.setNo_of_device(resultSet.getString("no_of_device"));
                plan.setStream(resultSet.getString("stream"));
                plan.setNo_of_ott(resultSet.getString("no_of_ott"));
//                id++;
                // Print each row in table format including id
                System.out.printf("| %-5d | %-20s | %5d | %8d | %-10s | %-10s | %6.2f | %-10s | %15s | %-6s | %10s |%n",
                        id, plan.getPlanName(), plan.getSpeed(), plan.getValidity(), plan.getData_limit(),
                        plan.getFup(), plan.getPrice(), plan.getQuata(), plan.getNo_of_device(),
                        plan.getStream(), plan.getNo_of_ott());

//                plansList.add(plan);
            }
        }
        return plan;
    }


    public int findPlan(int id) throws SQLException {
        String find_id = "select plan_id from plans where plan_id=?";
        try (PreparedStatement ps = con.prepareStatement(find_id)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("plan_id");
                }
            }
        }
        return -1;//if not valid
    }

    public List<Plans> getAllPlans() throws SQLException {
        List<Plans> plansList = new ArrayList<>();

        // Assuming you have a database connection
        String selectall = "select *from plans";
        try (PreparedStatement ps = con.prepareStatement(selectall)) {

            ResultSet resultSet = ps.executeQuery();
            System.out.printf("|%-2s| %-20s | %-5s | %-8s | %-10s | %-10s | %-6s | %-10s | %-15s | %-6s | %-10s |%n", "Plan_id",
                    "Plan Name", "Speed", "Validity", "Data Limit", "FUP", "Price", "Quota", "No. of Devices", "Stream", "No. of OTT");
            System.out.println("|-------|----------------------|-------|----------|------------|------------|--------|------------|------------------|--------|------------|");
//            int id=0;
            while (resultSet.next()) {
                Plans plan = new Plans();

                // Retrieve the id directly from the ResultSet
                int id = resultSet.getInt("plan_id");


                // Set the other properties of the plan object
                plan.setPlanName(resultSet.getString("plan_name"));
                plan.setSpeed(resultSet.getInt("speed"));
                plan.setValidity(resultSet.getInt("validity"));
                plan.setData_limit(resultSet.getString("data_limit"));
                plan.setFup(resultSet.getString("fup"));
                plan.setPrice(resultSet.getDouble("price"));
                plan.setQuata(resultSet.getString("quata"));
                plan.setNo_of_device(resultSet.getString("no_of_device"));
                plan.setStream(resultSet.getString("stream"));
                plan.setNo_of_ott(resultSet.getString("no_of_ott"));
//                id++;
                // Print each row in table format including id
                System.out.printf("| %-5d | %-20s | %5d | %8d | %-10s | %-10s | %6.2f | %-10s | %15s | %-6s | %10s |%n",
                        id, plan.getPlanName(), plan.getSpeed(), plan.getValidity(), plan.getData_limit(),
                        plan.getFup(), plan.getPrice(), plan.getQuata(), plan.getNo_of_device(),
                        plan.getStream(), plan.getNo_of_ott());

                plansList.add(plan);
            }
        }
        return plansList;
    }
}




