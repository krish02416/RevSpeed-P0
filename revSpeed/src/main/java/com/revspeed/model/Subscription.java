package com.revspeed.model;

import java.time.LocalDate;
import java.util.Date;

public class Subscription {
        private long subscriptionId;

        private long customerId; // Already defined in the Customer class
    private LocalDate startDate;
    private LocalDate endDate;
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Subscription(long customerId, long planId, Date startDate, Date endDate) {
    }

    public Subscription(long customerId, int duration, long planId) {
        this.customerId = customerId;
        this.duration = duration;
        this.planId = planId;
    }

    public long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    private long planId;     // Already defined in the Plan class


        // Constructors, getters, and setters

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Subscription() {
            // Default constructor
        }

        public Subscription(long customerId, long planId) {
            this.customerId = customerId;
            this.planId = planId;
            this.startDate = LocalDate.now();
            this.endDate = LocalDate.now().plusDays(30);
        }

        // Other methods, if needed
    }


