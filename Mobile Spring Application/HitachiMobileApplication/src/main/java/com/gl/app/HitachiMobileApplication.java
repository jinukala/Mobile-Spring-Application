package com.gl.app;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.gl.app.entity.Customer;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.*;
import com.gl.app.service.*;
import com.gl.app.service.impl.*;

public class HitachiMobileApplication {

	
public static void main(String[] args) throws SQLException {
	CustomerService customerService = new CustomerServiceImpl();
    SIMDetailsService simDetailsService = new SIMDetailsServiceImpl();
   
	
	 Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("1. Fetch Sim Details by Customer ID");
        System.out.println("2. Update customer address");
        System.out.println("3. Get all customers");
        System.out.println("4. Fetch active SIM details");
        System.out.println("5. Get SIM status");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
           // write code to fetch sim details by customer id
                System.out.println("Enter UniqueId/CustomerId : ");
                Long uniqueId = scanner.nextLong();
                System.out.println(customerService.fetchCustomerList(uniqueId).toString());

                
                break;
            case 2:
                System.out.println("Enter UniqueId(CustomerId : )");
                Long customerId = scanner.nextLong();
                System.out.println("Enter New Address");
                String address = scanner.next();
                System.out.println(customerService.updateCustomerAddress(customerId,address));
                break; 
            case 3:
               //write code to fetch all customers
                List<Customer> customerList = customerService.getAllCustomers();
                customerList.forEach(System.out::println);

                break;
                
         
            case 4:
              //Write code to fetch active sim details
                List<SIMDetails> simDetailsList = simDetailsService.fetchSIMDetailsWithActiveStatus();
                simDetailsList.forEach(System.out::println);

                break;
            case 5:
               //Write code to fetch sim status
                System.out.println("ENTER SIM NUMBER : ");
                long simNumber = scanner.nextLong();
                System.out.println("ENTER SERVICE NUMBER : ");
                long serviceNumber = scanner.nextLong();
                System.out.println(simDetailsService.getSimStatus(simNumber,serviceNumber));
                break;
           
            case 6:
                System.out.println("Exiting...");

			try {
				scanner.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        }
    }
}
}
