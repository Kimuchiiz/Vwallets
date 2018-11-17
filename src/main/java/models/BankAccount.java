/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Xclos
 */
@Entity
public class BankAccount implements Serializable {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String number, name ,pin;
    private double balance;
   
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "Acc_Bank")
    List<Account> account;

    public BankAccount() {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
            EntityManager em = emf.createEntityManager();
            TypedQuery<BankAccount> query = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
            List<BankAccount> result = query.getResultList();
            int lastacc = result.size();
            long nextnumber = 0;
            if (lastacc != 0) {
                nextnumber = Long.parseLong(result.get(lastacc-1).number) + 1;
            }
        this.number = String.format("%010d", nextnumber);*/
        account = new ArrayList<Account>();
    }
    
    public BankAccount(String name, String pin) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
            EntityManager em = emf.createEntityManager();
            TypedQuery<BankAccount> query = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
            List<BankAccount> result = query.getResultList();
            int lastacc = result.size();
            long nextnumber = 0;
            if (lastacc != 0) {
                nextnumber = Long.parseLong(result.get(lastacc-1).number) + 1;
            }
        this.number = String.format("%010d", nextnumber);
        this.name = name;
        this.pin = pin;
        this.balance = 0;
        account = new ArrayList<Account>();
    }
    
    public void genNumber(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
            EntityManager em = emf.createEntityManager();
            TypedQuery<BankAccount> query = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
            List<BankAccount> result = query.getResultList();
            int lastacc = result.size();
            long nextnumber = 0;
            if (lastacc != 0) {
                nextnumber = Long.parseLong(result.get(lastacc-1).number) + 1;
            }
        this.number = String.format("%010d", nextnumber);
    }
    
    public List<Account> getAccount() {
        return account;
    }

    public void addAccount(Account account) {
        this.account.add(account);
    }
    
    public void removeAccount(Account account) {
        this.account.remove(account);
    }
    
    /*public void remove() {
        for(Account acc : account) {
            acc.removeBankaccount(this);
        }
    }*/
    
    public boolean deposit(double depositAmount)                      {                                                                
        if (depositAmount > 0.0){ // if the depositAmount is valid     
            this.balance += depositAmount; // add it to the balance
            return true;
        }
        return false;
    }
    
    public int withdraw(double withdrawAmount)
   {
    if (withdrawAmount > balance){    
      System.out.println("Insufficient Funds!!!");
      return 1;
    } else if(withdrawAmount < 0.0){
        System.out.println("Minus Amount!!!");
      return 2;
    }
    else{
      this.balance -= withdrawAmount;
      return 0;
    }
  }

    public double getBalance(){                                    
        return balance;                   
    }     
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "BankAccount{" + "number=" + number + '}';
    }
    
}
