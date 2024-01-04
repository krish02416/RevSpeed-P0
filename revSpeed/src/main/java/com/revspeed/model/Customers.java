package com.revspeed.model;

public class Customers {

    private String customer_Name;
    private long ph_No;
    private String email;
    private String address;
    private String zipcode;
    private String create_password;


    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public long getPh_No() {
        return ph_No;
    }

    public void setPh_No(long ph_No) {
        this.ph_No = ph_No;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCreate_password() {
        return create_password;
    }

    public void setCreate_password(String create_password) {
        this.create_password = create_password;
    }



    @Override
    public String toString() {

        return "Customers{" +
                "customer_Id='" + customer_Name + '\'' +
                ", ph_No=" + ph_No +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", create_password='" + create_password + '\'' +
                '}';
    }
}
