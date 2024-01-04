package com.revspeed.dao;

import com.revspeed.model.Plans;

import java.sql.SQLException;
import java.util.List;

public interface PlansDao {
    void addPlan(Plans plan)throws SQLException;
    void updatePlan(Plans plan)throws SQLException;
    void deletePlan(long planId)throws SQLException;
    Plans getPlanById(int planId)throws SQLException;
    List<Plans> getAllPlans()throws SQLException;
    public int findPlan(int pid) throws SQLException ;
}
