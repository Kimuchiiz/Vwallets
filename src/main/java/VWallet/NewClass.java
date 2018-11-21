/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VWallet;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import models.Account;
import models.BankAccount;
import models.TransactionLog;

/**
 *
 * @author Xclos
 */
public class NewClass {
    public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    /*BankAccount account1 = new BankAccount("Time Kunakornkul","1234");
    BankAccount account2 = new BankAccount("Time Kunakornkul","1234");
    account2.deposit(500);
    account2.withdraw(50.231);

    System.out.println("BankAccount " + account2.getNumber());
    System.out.println("Has a balance of " + account2.getBalance());*/
    
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
//        EntityManager em = emf.createEntityManager();
//        Account account = new Account();
//        BankAccount bankaccount = new BankAccount();
//        bankaccount.genNumber();
//        TransactionLog transactionlog = new TransactionLog();
//
//        /*TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
//        List<Account> result = query.getResultList();
//        for (Account i : result) {
//            if (i.getUser_username().equals(username) ) return false;
//            else if(i.getEmail().equals(email)) return false;
//        }*/
//        em.getTransaction().begin();
//        account.setUsername("test");
//        account.setPassword("pass1234");
//        bankaccount.setName("Time K");
//        bankaccount.setPin("1234");
//        account.addBankaccount(bankaccount);
//        bankaccount.addAccount(account);
//        //em.persist(transactionlog);
//        em.persist(account);
//        em.persist(bankaccount);
//        //em.persist(bankaccount2);
//        em.getTransaction().commit();
//        TypedQuery<BankAccount> query = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
//        List<BankAccount> result = query.getResultList();
//        BankAccount t = result.get(0);
//        em.getTransaction().begin();
//        Account account3 = new Account();
//        account3.setUsername("test2");
//        account3.setPassword("pass1234");
//        account3.addBankaccount(bankaccount);
//        bankaccount.addAccount(account3);
//        transactionlog.setType("withdraw");
//        account3.addTransactionlog(transactionlog);
//        transactionlog.setAccount(account3);
//        em.persist(account3);
//        account.remove();
//        em.remove(account);
//        //em.remove(t);
//        /*List<BankAccount> ba = account.getBankAccount();
//        for (BankAccount k : ba){
//        if(k.getNumber() == t.getNumber())
//            account.removeBankAccount(k);
//        }*/
//        em.getTransaction().commit();     
//        BankAccount bankaccount2 = new BankAccount();
//        em.getTransaction().begin();
//        bankaccount2.genNumber();
//        bankaccount2.setPin("1112");
//        bankaccount2.setName("Pizza Company");
//        em.persist(bankaccount2);
//        //em.remove(account3);
//        //t.setBalance(1000);
//        //em.persist(t);
//        //em.remove(transactionlog);
//        em.getTransaction().commit();
//        em.close();
//        emf.close();
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        Account t = result.get(0);
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();     
        em.close();
        emf.close();
}
}
