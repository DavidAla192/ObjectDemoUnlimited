package com.company;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Bank {
    private ArrayList<BankAccountUnlimited> allAccounts;
    private ArrayList<Customers> allCustomers;

    public Bank(){
        allCustomers = new ArrayList<Customers>();
        allAccounts = new ArrayList<BankAccountUnlimited>();
    }
    public void doBanking(){
        var inputReader = new Scanner(System.in);
        while(true){
            printMenu();
            var userChoice = inputReader.nextInt();
            switch(userChoice){
                case 1:
                    System.exit(0);
                case 2:
                    addCustomer(inputReader);
                    break;
                case 3:
                    System.out.println("what is the customer ID of the customer to select.");
                    var idToFind = inputReader.nextInt();
                    var customer = getCustomer(idToFind);
                    if(customer.isPresent()){
                        doCustomerMenu(inputReader, Customers.get());
                    }
                    else
                        System.out.println("No such customer exists in this bank");
                    break;
            }
        }
    }

    private void doCustomerMenu(Scanner inputReader, Customers customersID){
        while(true){
            printCustomerMenu();
            System.out.print("Enter selection");
            var Choice = inputReader.nextInt();
            switch(Choice){
                case 1:
                    BankAccountUnlimited newAccount = addAccountToCustomer(inputReader, Customer);
                    allAccounts.add(newAccount);
                    break;
                case 2:
                    closeAccount(inputReader, Customer);
                case 3:
                    return;
                default:
                    System.out.println("Please choose one of the choices in the list");
            }
        }
    }
    private void closeAccount(Scanner inputReader, Customers customers){
        //ask the user for the account number to close
        System.out.println("enter what account ID do you want to close");
        var closeID = inputReader.nextInt();
        //call close account on the customer
        var closedAccount = customers.closeAccount(closeID);
        //if successful remove the account from allAccounts as well.
        if(closedAccount.isPresent()){
            System.out.println("Closing account ...");
            allAccounts.remove(closedAccount.get());
        }

    }
    private BankAccountUnlimited addAccountToCustomer(Scanner inputReader, Customers cust) {
        //ask user how much the initial balance should be
        System.out.println("how much is the initial balance going to be for this new customer?");
        var initialDeposit = inputReader.nextDouble();
        //call open account in the customer
        var newAccount = addAccountToCustomer(initialDeposit);
        //return the newly created account
        return newAccount;
    }

    private void printCustomerMenu(){
       System.out.println("#################################################");
       System.out.println("Please select what to do with this customer");
       System.out.println("    [1] open account");
       System.out.println("    [2] close account");
       System.out.println("    [3] return to main menu");
       System.out.println( "#################################################";
   }

    private Optional<Customers> getCustomer(int CustomerID){
        for(var currentCustomer : allCustomers){
            if(currentCustomer.getID() == CustomerID)
                return Optional.of(currentCustomer);
        }
        return Optional.empty();
    }
    private void addCustomer(Scanner inputReader) {
        inputReader.nextLine();//eats \n from previous call to nextInt
        System.out.print("Enter the new Customers name:");
        var newCustomerName  = inputReader.nextLine();
        System.out.print("Enter the new Customers Tax Id (SSN):");
        var newCustomerTaxId = inputReader.nextInt();
        var newCustomer = new Customers(newCustomerName, newCustomerTaxId);
        allCustomers.add(newCustomer);
        System.out.println("Congrats,you created a new customer with the name: " +
                newCustomer.getName() + " and taxID: " + newCustomer.getID());
    }

    private void printMenu() {
        System.out.println("===========================================");
        System.out.println("What do you want to do next:");
        System.out.println("   [1] Exit program");
        System.out.println("   [2] Add a new customer" );
        System.out.println("   [3] Get Help:");
        System.out.println("===========================================");
        System.out.println("Type the # of the options you want:");
    }
}
