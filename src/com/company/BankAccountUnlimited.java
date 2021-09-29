package com.company;

public class BankAccountUnlimited {
    private double balance;
    private float interestRate;
    private int accountID;
    private static int nextID = 1000;


    public BankAccountUnlimited(){
        interestRate = 0.02f;
        accountID = nextID;
        nextID++;
    }
    public BankAccountUnlimited(double initialBalance, float initialRate){
        balance = initialBalance;
        interestRate = initialRate;
        accountID = nextID;
        nextID += 1;
    }
    public double addInterest(){
        var interest = balance * interestRate;
        balance += interest;
        return balance;
    }
    public void deposit(double amount){
        balance = balance + amount;
    }
    public boolean withdraw(double amount){
        if(balance < amount)
            return false;
        balance -= amount;
        return true;
    }
    public double checkBalance(){
        return balance;
    }
    public int getAccountID() {
        return accountID;
    }
}
