package com.revspeed.main;

import com.mysql.cj.log.Slf4JLogger;
import com.revspeed.dao.*;
import com.revspeed.model.Customers;
import com.revspeed.model.Plans;
import com.revspeed.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;


public class Application {
    static {
        System.out.println("*****************************  WELCOME TO REVSPEED *******************");

    }

    private static final Logger logger= LoggerFactory.getLogger(Application.class);

    static int cid;
    static int pid;
    static Scanner sc = new Scanner(System.in);
    static CustomerDao dao = new CustomerImp();
    static PlansDao pdao = new PlansImp();
    static SubscriptionDao sdao = new SubscriptionImp();

    public static void main(String[] args) throws SQLException {
        logger.info("*****************************  WELCOME TO REVSPEED *******************");

        boolean cond = true;
        while (cond) {
            logger.debug("Application login");
            logger.debug("1.Login\n2.Register\n3.Forgot Password");
            System.out.println("Application login");
            System.out.println("1.Login\n2.Register\n3.Forgot Password\n4.BACK TO LOGIN");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("email:");//CHANGE TO MAIL BECAUSE ITS UNIQUE
                    String username = sc.next();

                    System.out.print("password:");
                    String password = sc.next();

                    if (dao.authenticateCustomer(username, password) != -1) {

                        cid = dao.authenticateCustomer(username, password);
                        logger.info("User with ID {} successfully logged in", cid);
                        System.out.println("CUSTOMER ID:"+cid);
                        //logging to next page
                        System.out.println("successfully logged in");
                        boolean cond1 = true;
                        while (cond1) {
                            System.out.println("HOME");
                            System.out.println("1.profile\n2.current plan\n3.Add plan\n4.BACK TO LOGIN");

                            int option1 = sc.nextInt();

                            switch (option1) {

                                case 1:
                                      logger.info("Profile method() -- select query");
//
                                    Customers c = dao.getCustomer(cid);

                                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                                    System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-15s |\n", "Customer Name", "Email", "Phone Number", "Address", "Zipcode");
                                    System.out.println("------------------------------------------------------------------------------------------------------------------------|");

                                    System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-15s |\n", c.getCustomer_Name(), c.getEmail(), c.getPh_No(), c.getAddress(), c.getZipcode());
                                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                                    boolean cond2 = true;
                                    while (cond2) {
                                        System.out.println("PROFILE SETTING ");
                                        System.out.println("1.update\n2.Back");
                                        int option2 = sc.nextInt();
                                        switch (option2) {
                                            case 1:
                                                System.out.println("update");
                                                System.out.println("modify which column");
                                                boolean cond3 = true;
                                                while (cond3) {
                                                    System.out.println("EDIT PROFILE");
                                                    System.out.println("1.PhoneNumber:\n2.update password:\n3.BACK");
                                                    int option3 = sc.nextInt();
                                                    Customers modcus = new Customers();

                                                    switch (option3) {


                                                        case 1:
                                                            System.out.print("New PhoneNumber:");
                                                            long ph = sc.nextLong();
                                                            dao.update(cid, ph);
                                                            break;

                                                        case 2:
                                                            System.out.println("UPDATE PASSWORD");
                                                            System.out.println("OLD PASSWORD:");
                                                            String opass = sc.next();

                                                            if (password.equals(opass)) {
                                                                Customers newpass = new Customers();
                                                                System.out.println("Enter the new password:");
                                                                String npass = sc.next();
                                                                System.out.println("Retype new password:");
                                                                String npass1 = sc.next();
                                                                if (npass.equals(npass1)) {
                                                                    newpass.setCreate_password(npass);
                                                                    dao.updatepass(newpass, cid);
                                                                } else {
                                                                    System.out.println("Please provide correct password.");
                                                                }

                                                            } else {
                                                                System.out.println("old password is incorrect.");
                                                            }
                                                            break;
                                                        case 3:
                                                            cond3 = false;
                                                            break;

                                                        default:
                                                            System.out.println("invalid modication.");
                                                    }
                                                }
                                                break;
//                                            case 2:
//
//                                                System.out.println("confirm do you want to delete your account?");
//                                                String option4 = sc.next();
//
//                                                if (option4.equalsIgnoreCase("YES")) {
//                                                    dao.delete(cid);
//                                                } else if (option4.equalsIgnoreCase("NO")) {
//                                                    //return to home
//                                                } else {
//                                                    System.out.println("Entered Invalid Input.");
//                                                }
//                                                break;
                                            case 2:
                                                logger.info("Going back to the HOME screen");
                                                cond2 = false;
                                                break;

                                            default:
                                                System.out.println("invalid");
                                                logger.warn("Invalid option inside PROFILE SETTING switch: {}", option2);
                                        }
                                    }
                                    break;
                                case 2:
//                                    System.out.println("current plan --select *from Subscription; and plan detail");
                                    logger.info("Displaying current plan information");
                                    List<Subscription> al = sdao.getCustomerDetail(cid);
//                                for (Subscription sub:al){
//                                    System.out.println(sub);
//                                }
                                    LocalDate currentDate = LocalDate.now();
                                    System.out.printf("| %-15s | %-15s | %-15s | %-15s |%-15s |%-15s\n", "Subscription ID", "Customer ID", "Plan ID", "Start date", "Expiry date", "No of days left");
                                    System.out.println("|-----------------|-----------------|-----------------|-----------------|----------------|---------------");

                                    for (Subscription subscription : al) {
                                        // Print each row
                                        LocalDate endDate = subscription.getEndDate();
//                                        System.out.println("current date:"+currentDate);
//                                        System.out.println("end date:"+endDate);
                                        long daysLeft = ChronoUnit.DAYS.between(currentDate, endDate)-1;

                                        System.out.printf("| %-15d | %-15d | %-15d | %-15s |%-15s |%-15d\n", subscription.getSubscriptionId(), subscription.getCustomerId(), subscription.getPlanId(), subscription.getStartDate(), subscription.getEndDate(), daysLeft);
                                    }

//
                                    break;
                                case 3:
                                    logger.info("Adding a new plan");
                                    System.out.println("Add plan z-- select*from plan;");
                                    List<Plans> ls = pdao.getAllPlans();
//                            for (Plans allplans : ls) {
//                                System.out.println(allplans);
//                            }
                                    System.out.print("Select the plan form the list:");
                                    int option3 = sc.nextInt();
                                    //select the plan
                                    pid = pdao.findPlan(option3);
                                    pdao.getPlanById(pid);
                                    Subscription sub = new Subscription();

                                    try {
                                        sdao.subscribeToPlanWithIds(cid, pid);
                                    } catch (Exception e) {
                                        System.out.println("You have already subscribed  another plan.");
                                        List<Subscription> la = sdao.getCustomerDetail(cid);
                                        LocalDate current = LocalDate.now();
                                        System.out.printf("| %-15s | %-15s | %-15s | %-15s |%-15s |%-15s\n", "Subscription ID", "Customer ID", "Plan ID", "Start date", "Expiry date", "No of days left");
                                        System.out.println("|-----------------|-----------------|-----------------|-----------------|----------------|---------------");

                                        for (Subscription subscription : la) {
                                            // Print each row

                                            LocalDate endDate = subscription.getEndDate();

                                            long daysLeft = ChronoUnit.DAYS.between(current, endDate);

                                            System.out.printf("| %-15d | %-15d | %-15d | %-15s |%-15s |%-15d\n", subscription.getSubscriptionId(), subscription.getCustomerId(), subscription.getPlanId(), subscription.getStartDate(), subscription.getEndDate(),daysLeft);
                                        }
                                    }

                                    break;
                                case 4:
                                    logger.info("Going back to the HOME screen");
                                    //back
                                    cond1 = false;
                                    break;
                                default:
                                    System.out.println("Invalid option");
                            }
                        }
                    } else {
                        logger.error("Invalid password or EMAIL");
                        System.out.println("**************INVALID EMAIL AND PASSWORD****************");
                    }
                    break;
//register
                case 2:
                    logger.info("******** REVSPEED REGISTRATION PAGE***********");
                    System.out.println("******** REVSPEED REGISTRATION PAGE***********");

                    System.out.println("=============================");

                    //used to store the NEW CUSTOMER details.
                    Customers newCustomer = new Customers();
//


                    System.out.println("Enter the Name:");
                    while (true) {
                        String customerName = sc.next();

                        if (customerName.matches("[a-zA-Z ]+")) {
                            newCustomer.setCustomer_Name(customerName);
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid name.");
//
                        }
                        logger.debug("Name: {}", newCustomer.getCustomer_Name());
                    }

                    System.out.println("Phone number:");
                    while (true) {
                        String ph = sc.next();

                        if (ph.matches("^[0-9]{10}$")) {
                            newCustomer.setPh_No(Long.parseLong(ph));
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid PhoneNumber.");
                        }
                    }
                    logger.debug("Phone number: {}", newCustomer.getPh_No());

                    System.out.println("Email:");
                    while (true) {
                        String Email = sc.next();

                        if (Email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                            newCustomer.setEmail(Email);
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid Email.");
                        }logger.debug("Email: {}", newCustomer.getEmail());
                    }

                    System.out.println("Address:");
                    newCustomer.setAddress(sc.next());
                    sc.nextLine();
                    logger.debug("Address: {}", newCustomer.getAddress());

                    System.out.println("ZipCode:");
                    while (true) {
                        String ZipCode = sc.next();
                        //zipcode length in US is 5digit
                        if (ZipCode.matches("^[0-9]{5}$")) {
                            newCustomer.setZipcode(ZipCode);
                            break;
                        } else {
                            System.out.println("Invalid input.Please enter a valid ZipCode (5 DIGIT).");
                        }
                    }
                    logger.debug("Zipcode: {}", newCustomer.getZipcode());

                    while(true) {
                        System.out.print("Create password:");

                        String cpass = sc.next();

                        System.out.print("Retype password:");

                        String rpass = sc.next();

                        if (cpass.equals(rpass)) {
                            newCustomer.setCreate_password(cpass);
                            dao.add(newCustomer);
                            logger.info("Successfully registered a new customer");
                            break;
                        } else {
                            System.out.println("Please Enter valid password.");
                            logger.info("unSuccessfully registered a new customer");
                        }
                    }

                    System.out.println("=================================");
                    break;
//forgot password
                case 3://forgot password
                    System.out.println("Forgot password?");
                    System.out.print("Enter your email address: ");
                    String userEmail = sc.nextLine();

//                    // Check if the provided email matches the stored email
//                    if (userEmail.equals(storedEmail)) {
//                        // Generate a random token (you might want to use a more secure method in a real application)
//                        String randomToken =Application.generateRandomToken();
//
//                        // Simulate storing the token in the database
//                        String storedToken = randomToken;
//
//                        System.out.println("A password reset link has been sent to your email.");
//                        System.out.print("Enter the received token: ");
//                        String enteredToken = sc.nextLine();
//
//                        // Check if the entered token matches the stored token
//                        if (enteredToken.equals(storedToken)) {
//                            System.out.println("Token is valid. You can now reset your password.");
//                            // Implement the logic to reset the password
//                        } else {
//                            System.out.println("Invalid token. Password reset failed.");
//                        }
//                    } else {
//                        System.out.println("Email not found. Password reset failed.");
//                    }

                    break;
            }
//            private static String generateRandomToken() {
//                // Generate a random token using Math.random (you might want to use a more secure method)
//                return String.valueOf((int) (Math.random() * 1000000));
//            }
        }

    }
}

