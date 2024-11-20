
package eventtrckr;

import java.util.Scanner;

public class Customer {
    Scanner scan = new Scanner(System.in);
    Config conf = new Config();
    
    public void customerConfig() {
        int option;
        do {
            System.out.println("\n╭──────────────────────────────╮");
            System.out.println("│               ++ Customer Menu ++               │");
            System.out.println("╰──────────────────────────────╯\n");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Edit Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Exit");
            
            System.out.print("Choose an option: ");
            option = scan.nextInt();
            scan.nextLine();
                    

            switch (option) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomers();
                    break;
                case 3:
                    editCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 5);
    }

    private void addCustomer() {
        System.out.println("Enter Customer Details:");
        String name;
        while (true){
            System.out.println("\nName: ");
            name= scan.nextLine().trim();
            if (name.length() >= 2 && name.length() <= 50 && name.matches("[a-zA-Z]+")){
                break;
            }else {
                System.out.println("Invalid name. Please enter 2-50 alphabetic characters only.");
            }
        }
        
      
        String email;
         do { System.out.print("Enter Email Address: ");
         email = scan.nextLine();
         if (!email.matches("^[A-Za-z0-9+.-]+@[A-Za-z0-9.-]+$")) 
         { System.out.println("Invalid email format. Please enter a valid email address (e.g., example@domain.com).");
             }
         } while (!email.matches("^[A-Za-z0-9+.-]+@[A-Za-z0-9.-]+$"));
         
        String contactInfo;
        while (true){
            System.out.println("Contact Number: ");
             contactInfo = scan.nextLine();
             if (contactInfo.matches("\\d{11}")){
                 break;
             } else {
                 System.out.println("Invalid contact number. It must contain exactly 11 digits. ");
             } 
        }

        String sql = "INSERT INTO customer (name, email, contact_info) VALUES (?, ?, ?)";
        conf.addRecord(sql, name, email, contactInfo);
    }

    public void viewCustomers() {
        String query = "SELECT * FROM customer";
        String[] headers = {"ID", "Name", "Email", "Contact Info"};
        String[] columns = {"id", "name", "email", "contact_info"};
        conf.viewRecords(query, headers, columns);
    }

    private void editCustomer() {
        System.out.print("Enter Customer ID to edit: ");
        int id = scan.nextInt();
        scan.nextLine();

        if (!conf.doesIDExist("customer", id)) {
            System.out.println("Customer ID not found.");
            return;
        }

        System.out.println("Enter New Details:");
        String name;
        while (true){
            System.out.println("\nName: ");
            name= scan.nextLine().trim();
            if (name.length() >= 2 && name.length() <= 50 && name.matches("[a-zA-Z]+")){
                break;
            }else {
                System.out.println("Invalid name. Please enter 2-50 alphabetic characters only.");
            }
        }
       String email;
         do { System.out.print("Enter Email Address: ");
         email = scan.nextLine();
         if (!email.matches("^[A-Za-z0-9+.-]+@[A-Za-z0-9.-]+$")) 
         { System.out.println("Invalid email format. Please enter a valid email address (e.g., example@domain.com).");
             }
         } while (!email.matches("^[A-Za-z0-9+.-]+@[A-Za-z0-9.-]+$"));
        
        String contactInfo;
        while (true){
            System.out.println("Contact Number: ");
             contactInfo = scan.nextLine();
             if (contactInfo.matches("\\d{11}")){
                 break;
             } else {
                 System.out.println("Invalid contact number. It must contain exactly 11 digits. ");
             } 
        }

        String sql = "UPDATE customer SET name = ?, email = ?, contact_info = ? WHERE id = ?";
        conf.updateRecord(sql, name, email, contactInfo, id);
    }

    private void deleteCustomer() {
        System.out.print("Enter Customer ID to delete: ");
        int id = scan.nextInt();

        String sql = "DELETE FROM customer WHERE id = ?";
        conf.deleteRecord(sql, id);
    }

   
   }


