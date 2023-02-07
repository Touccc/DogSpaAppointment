package dogspa;

import java.util.Scanner; 

import java.time.LocalDate; 

import java.time.LocalTime; 

import java.time.DayOfWeek; 

import java.io.FileWriter; 

import java.io.PrintWriter; 


  

//Menu class to handle all the processes 

class Menu implements Register { 

  private double[] prices = {50.00, 30.00, 100.00, 70.00}; //prices for items 

  

  //class attributes 

  private String name; 

  private String phone; 

  private String email; 

  private String dogName; 

  private LocalDate date; 

  LocalTime time; 

  private double total = 0; 

  

  

  public Scanner input = new Scanner(System.in); 

  

  //default constructor 

  public Menu(){ 

    name = ""; 

    phone = ""; 

    email = ""; 

    dogName = ""; 

  } 

  

  //accessor for name 

  public String getName() { 

return name; 

} 

  

//accessor for dogName 

public String getDogName() { 

return dogName; 

} 

  

//accessor for Total 

public double getTotal(){ 

  return total; 

} 

  //class implementation of welcome() interface method 

  //prints out the welcome message. 

  public void welcome(){ 

    System.out.println("Hello and welcome to Spencer's Dog Spa"); 

    System.out.println("Please choose an option:"); 

    System.out.println("1. Make an appointment"); 

    System.out.println("2. Quit\n\n"); 

     

  } 

  

  //class implementation of MakeAppointment() interface method 

  //creates sets the time and date of an appoinment 

  public void MakeAppointment(){ 

     

    System.out.println("Enter the date of the appointment (yyyy-MM-dd): "); 

    String dateStr = input.nextLine(); 

    date = LocalDate.parse(dateStr); 

  

    System.out.println("Enter the time of the appointment (HH:mm): "); 

    String timeStr = input.nextLine(); 

     time = LocalTime.parse(timeStr); 

  

   if (time.isBefore(LocalTime.NOON) || time.isAfter(LocalTime.of(20, 0))) { 

  System.out.println("Appointments can only be made from 12 PM to 8 PM."); 

  System.exit(0); 

} else { 

  System.out.println("Appointment confirmed on " + date + " at " + time + "."); 

} 

  } 

  

  //accessor for date 

  public LocalDate getDate(){ 

    return date; 

  } 

  

  //accessor for time 

  public LocalTime getTime(){ 

    return time; 

  } 

   

  //member function to display the menu for the 

  //services. 

  //also adds the items' prices together for a total 

  public void Service(){ 

    int choice; 

    do { 

            System.out.println("\nMenu:"); 

            System.out.println("1. bath ($50.00)"); 

            System.out.println("2. ear care ($30.00)"); 

            System.out.println("3. furcut ($100.00)"); 

            System.out.println("4. nail trim ($70.00)"); 

            System.out.println("5. Checkout"); 

            System.out.print("\nEnter your choice: "); 

            choice = input.nextInt(); 

  

            if (choice >= 1 && choice <= 4) { 

                total += prices[choice - 1]; 

                System.out.println("Item " + choice + " added to your cart."); 

            }  

            else if (choice != 5) { 

                System.out.println("Invalid choice. Please try again."); 

            } 

    } while (choice != 5); 

      input.close(); 

  

    System.out.println("\nYour total is $" + total); 

  } 

  

  //member function that stores the name 

  //phone number, email, and dog's name. 

  public void Info(){ 

    System.out.println("\n\nPlease enter some information."); 

    System.out.print("Enter your name: "); 

    name = input.nextLine(); 

    System.out.print("Enter your phone number (XXX-XXX-XXXX): "); 

    phone = input.nextLine(); 

    System.out.print("Enter your email: "); 

    email = input.nextLine(); 

    System.out.print("Enter your dog's name: "); 

    dogName = input.nextLine(); 

  } 

} 

  

//main 

class Spa { 

  public static void main(String[] args) { 

    //user object of class Menu 

    Menu user = new Menu(); 

  

    user.welcome(); 

    Scanner pick = new Scanner(System.in); 

    int choice = pick.nextInt(); 

  

    //switch statement to either make an  

    //appointment or quit 

    switch(choice){ 

      case 1:                                     //User chose to make an appointment 

        user.MakeAppointment(); 

        user.Info(); 

        user.Service(); 

        System.out.println("Loading Order Summary"); 

  

        //writes receipt in another text file 

        try{ 

          FileWriter fileWriter = new FileWriter("receipt.txt"); 

          PrintWriter printWriter = new PrintWriter(fileWriter); 

  

          printWriter.println("Spencer's Dog Spa"); 

          printWriter.println("Customer: " + user.getName()); 

          printWriter.println("Dog Name: " + user.getDogName()); 

          printWriter.println("Appointment Date: " + user.getDate() +  

                              "Time: " + user.getTime()); 

          printWriter.println("Total: " + "$" + user.getTotal()); 

          printWriter.close(); 

        } 

        catch (Exception e){ 

          System.out.println("An error occurred while writing to the file."); 

        } 

        System.out.println("Receipt has loaded please check desktop for reciept.txt"); 

        pick.close(); 

        System.exit(0); 

        break; 

      case 2:                                      //User chose to quit 

        System.out.println("Goodbye"); 

        pick.close(); 

        System.exit(0); 

        break; 

      default:                                     //User did not choose either 

        System.out.println("Invalid option"); 

        System.exit(0); 

         

    } 

     

  } 

} 

 
