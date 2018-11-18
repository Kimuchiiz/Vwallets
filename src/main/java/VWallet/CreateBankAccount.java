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
public class CreateBankAccount {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int menu = 0;

        while (menu != 4) {
            System.out.println("Menu : ");
            menu = input.nextInt();
            if (menu == 1) {
                input.nextLine();
                String name, pin;
                System.out.println("Name : ");
                name = input.nextLine();
                System.out.println("Pin : ");
                pin = input.nextLine();
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
                EntityManager em = emf.createEntityManager();
                BankAccount bankaccount = new BankAccount();
                bankaccount.genNumber();
                em.getTransaction().begin();
                bankaccount.setName(name);
                bankaccount.setPin(pin);
                em.persist(bankaccount);
                em.getTransaction().commit();
                em.close();
                emf.close();
            }
            if (menu == 2) {
                input.nextLine();
                String number;
                double balance;
                System.out.println("Bank Account Number : ");
                number = input.nextLine();
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
                EntityManager em = emf.createEntityManager();
                TypedQuery<BankAccount> query = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
                List<BankAccount> result = query.getResultList();
                for (BankAccount i : result) {
                    if (i.getNumber().equals(number)) {
                        System.out.println("Amount : ");
                        balance = input.nextDouble();
                        em.getTransaction().begin();
                        i.setBalance(balance);
                        em.persist(i);
                        em.getTransaction().commit();
                        em.close();
                        emf.close();
                    } else {
                        System.out.println("Invalid Number!!!");
                    }
                }
            }
        }
    }
}
