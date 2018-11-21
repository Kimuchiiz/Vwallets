/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Xclos
 */

import bluepay.BluePay;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;

@Entity
public class Account implements Serializable{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String username,password,name;
    private double balance;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<CreditCard> creditcard;
    
    @ManyToMany(mappedBy="account",cascade= {CascadeType.PERSIST, 
        CascadeType.MERGE})
    List<BankAccount> bankaccount;
    
    @OneToMany(mappedBy = "account",cascade = CascadeType.PERSIST, orphanRemoval = false)
    List<TransactionLog> transactionlog;

    public Account() {
        bankaccount = new ArrayList<BankAccount>();
        creditcard = new ArrayList<CreditCard>();
        transactionlog = new ArrayList<TransactionLog>();
        this.balance = 0;
    }
    
    public Account( String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = 0;
        bankaccount = new ArrayList<BankAccount>();
        creditcard = new ArrayList<CreditCard>();
        transactionlog = new ArrayList<TransactionLog>();
    }

    public List<BankAccount> getBankaccount() {
        return bankaccount;
    }

    public void addBankaccount(BankAccount bankaccount) {
        this.bankaccount.add(bankaccount);
    }
    
    public void removeBankaccount(BankAccount bankaccount) {
        this.bankaccount.remove(bankaccount);
    }
    
    public void remove() {
        for(BankAccount bacc : bankaccount) {
            bacc.removeAccount(this);
        }
        for(TransactionLog tlog : transactionlog){
            tlog.setAccount(null);
        }
    }
    
    /*public int addBalance(String bankaccnum,double addAmount) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
            EntityManager em = emf.createEntityManager();
            TypedQuery<BankAccount> query = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
            List<BankAccount> result = query.getResultList();
            BankAccount bankaccount = null;
            for (BankAccount i : result) {
                if (i.getNumber().equals(bankaccnum.substring(0,9)) ){
                    bankaccount = i;
                    break;
                }
            }
            if(bankaccount == null){
                System.out.println("BankAccount Not Found!!!");
                return 1;
            }
            if(!bankaccount.getPin().equals(bankaccnum.substring(10))){
                System.out.println("Invalid Pin!!!");
                return 2;
            } else{
                if (addAmount < 0.0){    
                    System.out.println("Minus Amount!!!");
                    return 3;
                } else if(addAmount > bankaccount.getBalance()){
                    System.out.println("Insufficient Funds!!!");
                    return 4;
                } else{
                    this.balance += addAmount;
                    bankaccount.withdraw(addAmount);
                return 0;
                }
            }
    }*/
    
    public boolean addBalance(double addAmount) {
        if (addAmount < 0.0){    
            System.out.println("Minus Amount!!!");
            return false;
        } else{
            this.balance += addAmount;
        return true;
        }
    }
    
    public int chargeBank(BankAccount bankaccount,double addAmount) {
        if (addAmount < 0.0){    
            System.out.println("Minus Amount!!!");
            return 1;
        } else if(addAmount > bankaccount.getBalance()){
            System.out.println("Insufficient Funds!!!");
            return 2;
        } else{
            this.balance += addAmount;
            bankaccount.withdraw(addAmount);
        return 0;
        }
    }
    
    public int transfer(Account account, double transferAmount) {
        if (transferAmount < 0.0){    
            System.out.println("Minus Amount!!!");
            return 1;
        } else if(transferAmount > this.balance){
            System.out.println("Insufficient Funds!!!");
            return 2;
        } else{
            this.balance -= transferAmount;
            account.addBalance(transferAmount);
        return 0;
        }
    }
    
    public int withdraw(BankAccount bankaccount,double withdrawAmount) {
        if (withdrawAmount < 0.0){  
            System.out.println("Minus Amount!!!");
            return 1;
        }
        else if(this.balance < withdrawAmount){
            System.out.println("Insufficient Funds!!!");
            return 2;
        }
        else{
            this.balance -= withdrawAmount;
            bankaccount.deposit(withdrawAmount);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBankaccount(List<BankAccount> bankaccount) {
        this.bankaccount = bankaccount;
    }

    public List<CreditCard> getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(List<CreditCard> creditcard) {
        this.creditcard = creditcard;
    }
    
    public void addCreditcard(CreditCard creditcard) {
        this.creditcard.add(creditcard);
    }
    
    public void removeCreditcard(CreditCard creditcard) {
        this.creditcard.remove(creditcard);
    }

    public List<TransactionLog> getTransactionlog() {
        return transactionlog;
    }

    public void setTransactionlog(List<TransactionLog> transactionlog) {
        this.transactionlog = transactionlog;
    }
    
    public void addTransactionlog(TransactionLog transactionlog) {
        this.transactionlog.add(transactionlog);
    }
    
    public void removeTransactionlog(TransactionLog transactionlog) {
        this.transactionlog.remove(transactionlog);
    }
    
    @Override
    public String toString() {
        return "Account{" + "username=" + username + '}';
    }
    
    public boolean chargeCC(String firstName, String lastName, String address1, String address2, String city, String state, String zip, String country, 
            String phone, String email, String cardNumber, String expirationDate, String cvv2, String amount){ 
    
    String ACCOUNT_ID = "100640816938";
    String SECRET_KEY = "TKFB40ZQ2RLANPHFAOMLU.537PENYSUT";
    String MODE = "TEST";

    BluePay payment = new BluePay(
        ACCOUNT_ID, 
        SECRET_KEY, 
        MODE
    );

    // Set Customer Information  
    HashMap<String, String> customerParams = new HashMap<>();
    customerParams.put("firstName", firstName);
    customerParams.put("lastName", lastName);
    customerParams.put("address1", address1);
    customerParams.put("address2", address2);
    customerParams.put("city", city);
    customerParams.put("state", state);
    customerParams.put("zip", zip);
    customerParams.put("country", country);
    customerParams.put("phone", phone);
    customerParams.put("email", email);
    payment.setCustomerInformation(customerParams);

    // Set Credit Card Information
    HashMap<String, String> ccParams = new HashMap<>();
    ccParams.put("cardNumber", cardNumber);
    ccParams.put("expirationDate", expirationDate);
    ccParams.put("cvv2", cvv2);
    payment.setCCInformation(ccParams);

    // Set sale amount: $3.00
    HashMap<String, String> saleParams = new HashMap<>();
    saleParams.put("amount", amount);
    payment.sale(saleParams);

    // Makes the API Request with BluePay
    try {
      payment.process();
    } catch (Exception ex) {
      System.out.println("Exception: " + ex.toString());
    }
    
    // If transaction was successful reads the responses from BluePay
    if (payment.isSuccessful()) {
      System.out.println("Transaction Status: " + payment.getStatus());
      System.out.println("Transaction Message: " + payment.getMessage());
      System.out.println("Transaction ID: " + payment.getTransID());
      System.out.println("AVS Response: " + payment.getAVS());
      System.out.println("CVV2 Response: " + payment.getCVV2());
      System.out.println("Masked Payment Account: " + payment.getMaskedPaymentAccount());
      System.out.println("Card Type: " + payment.getCardType());    
      System.out.println("Authorization Code: " + payment.getAuthCode());
      return true;
    } else {
      System.out.println("Error: " + payment.getMessage());
      return false;
    }
  }
    
}