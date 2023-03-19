//BSC-COM-23-20
import java.util.*;
import java.io.*;
public class LupoPeanuts {
    
    public static void main(String[] merna) throws FileNotFoundException { 
        System.out.println("Lupo Peanuts Cafeteria");
    System.out.println("\n______________________________________________________________________");
	Scanner sham = new Scanner(System.in);

    
    
    
    System.out.print("\nEnter your 12 digit mobile number starting with 265: ");
    String num = sham.next();
    
    while(!num.startsWith("265") || (num.length() > 12 || num.length() < 12)) {
        System.out.print("\nInvalid number! Please re-enter your number : ");
        num = sham.next();      
    } 

    System.out.println("Welcome to Lupo Peanuts Cafeteria!");

        ArrayList<String> names_of_customers = new ArrayList<>();
        ArrayList<Integer> gym_numbers = new ArrayList<>();
        ArrayList<Integer> meals = new ArrayList<>();
        ArrayList<String> members_n_freeMeal = new ArrayList<>();


        boolean control = true; // used to terminate the loop
        
       while(control) {
           System.out.println("\nWhat do you want to do ?");
	        System.out.println("\n"); 
            System.out.println("\t1. Add new gym members");
            System.out.println("\t2. Delete gym member");
            System.out.println("\t3. Issue meal cards");
            System.out.println("\t4. Print report");
             System.out.println("\t5. Logout"); 

            System.out.print("Choice : ");
            int choice = sham.nextInt();

            Customers customer; // object defined
            switch (choice) {
            case 1 : 
                 System.out.print("Enter name without spaces as DanFord : ");
                String customer_name = sham.next();

                System.out.print("Enter your 6 digit gym number : "); 
                int gym_num = sham.nextInt();
                
                while (("" + gym_num).length() < 6 || ("" + gym_num).length() > 6) {
                    System.out.print("Re-Enter : ");
                    gym_num = sham.nextInt();
                }
                
                // object 
                customer = new Customers(customer_name, gym_num); 
                
                names_of_customers.add(customer_name); // add name to the arrayList
                gym_numbers.add(gym_num); // add gym number to the arrayList 

                System.out.println("You have successfully added " + customer_name + 
                " with gym number " + gym_num);
                 
                break;

            case 2 : 
                System.out.println("Enter your 6 digit gym number : ");
                gym_num = sham.nextInt();
                 
                while (("" + gym_num).length() < 6 || ("" + gym_num).length() > 6) {
                    System.out.print("Re-Enter : ");
                    gym_num = sham.nextInt();
                } 
                 
                for (int x = 0; x < gym_numbers.size(); x++) {
                    if (gym_numbers.get(x) == gym_num) {
                        gym_numbers.remove(x);
                        names_of_customers.remove(x);
                    }
                }
		System.out.println("Customer with gym number " + gym_num + " has been deleted successfully");

                break;

            case 3 : 
                 System.out.println("Enter gym number : ");
                gym_num = sham.nextInt();
                
                while (("" + gym_num).length() < 6 || ("" + gym_num).length() > 6) {
                    System.out.print("Re-Enter : ");
                    gym_num = sham.nextInt();
                }
                 
                String authorised_member = "";

                for (int x = 0; x < gym_numbers.size(); x++) {
                     if (gym_numbers.get(x) == gym_num) {
                        authorised_member = names_of_customers.get(x);
                    }
                }

                if (meals.contains(gym_num)) {
                    System.out.println("Denied! " + authorised_member + " has already been issued a free meal card");
                }

                else {
                    System.out.println("Take out the meal card on the printer");
                    meals.add(gym_num);
                    members_n_freeMeal.add(authorised_member);
                }
               
                break;

            case 4 : 
                ourGymMembers(gym_numbers, names_of_customers, meals, members_n_freeMeal,num);
                System.out.println("The report has been exported to csv");
                break;

            case 5 : 
                System.out.println("Bye! Bye!");
                control = false; // break the loop
                break;

            default : System.out.println("Invalid input");  
        }
       } 
    }
    
    public static void ourGymMembers(ArrayList<Integer> gym_numbers, ArrayList<String> names_of_customers, ArrayList<Integer> meals, ArrayList<String> members_n_freeMeal, String mobile_number) {
        try (PrintWriter writer = new PrintWriter("all_members.csv")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Names Of Members");
            stringBuilder.append(',');
            stringBuilder.append("Gym Numbers");
            stringBuilder.append('\n');
            
            for (int x = 0; x < names_of_customers.size(); x++) {
                stringBuilder.append(names_of_customers.get(x));
                stringBuilder.append(',');
                stringBuilder.append(gym_numbers.get(x));
                stringBuilder.append('\n');
            }
             
            // write to the cvs file
            writer.write(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            System.out.println("Error occured");
        }

        // creating a report for gym members with free meals
        try (PrintWriter writer = new PrintWriter("authorised_members.csv")) {
            StringBuilder authorised_members = new StringBuilder();
            authorised_members.append("Name of Member");
            authorised_members.append(',');
            authorised_members.append("Gym number");
            authorised_members.append('\n');

            for (int x = 0; x < meals.size(); x++) {
                authorised_members.append(members_n_freeMeal.get(x)); 
                authorised_members.append(',');
                authorised_members.append(meals.get(x));
                authorised_members.append('\n');
            } 

            // write to the cvs file
            writer.write(authorised_members.toString());

        } catch (FileNotFoundException e) {
            System.out.println("Error occured");
        }

        // create a report for gym_members with free meals
        try (PrintWriter writer = new PrintWriter("Report.csv")) {
            StringBuilder report = new StringBuilder();
            report.append("Phone number");
            report.append('\n');

            report.append(""+ mobile_number);
            report.append('\n');

            report.append("Name of Member");
            report.append(',');
            report.append("Gym number");
            report.append(',');
            report.append('\n');


            for (int x = 0; x < names_of_customers.size(); x++) {
                report.append(names_of_customers.get(x));
                report.append(',');
                report.append(gym_numbers.get(x));
                report.append('\n');         
            } 

            report.append('\n');
            report.append("Authorised member issued free meal");
            report.append(',');
            report.append("Corresponding Gym number issued free meal");
            report.append('\n');

            for (int x = 0; x < meals.size(); x++) {
                report.append(members_n_freeMeal.get(x));
                report.append(',');
                report.append(meals.get(x));  
                report.append('\n');
       
            }
             
            // write to the cvs file
            writer.write(report.toString());

        } catch (FileNotFoundException e) {
            System.out.println("Error occured");
        }

    }

}