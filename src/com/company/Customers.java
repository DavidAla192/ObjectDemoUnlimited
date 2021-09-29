package com.company;

import java.util.ArrayList;
import java.util.Optional;

public class Customers{
    private int CustomerID;
    private String name;
    private ArrayList<BankAccountUnlimited> accounts;

    public Customers(String CustomerName, int taxID){
        CustomerID = taxID;
        name = CustomerName;
        accounts = new ArrayList<BankAccountUnlimited>();
    }

    public BankAccountUnlimited openAccount(double initialBalance){
        var newAccount = new BankAccountUnlimited();
        newAccount.deposit(initialBalance);
        var didSucceed= accounts.add(newAccount);
        return newAccount;
    }

    public Optional<BankAccountUnlimited> closeAccount(int accountNumber){
        //look for the right account in accounts
        //if found remove account and return it in an optional
        for(var currentAccount: accounts){
            if(currentAccount.getAccountID()==accountNumber){
                accounts.remove(currentAccount);
                return Optional.of(currentAccount);
            }
        }
        //if not found return empty optional
        System.out.println("Tried to remove an account that didnt exist");
        return Optional.empty();
    }

    public String getName(){
        return name;
    }

    public int getID(){
        return CustomerID;
    }

    public void Customer(){

    }
}
