/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VWallet;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import models.Account;
import models.ActivityHistory;
import models.BankAccount;
import models.CreditCard;

/**
 *
 * @author Xclos
 */
public class VWallet {

    public static boolean setRegister(String username, String password, String name) { //func register ถ้าสมัครได้ return true ไม่ได้ return false
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        Account account = new Account();

        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if (i.getUsername().equals(username)) {
                em.close();
                emf.close();
                return false;
            }
        }
        em.getTransaction().begin();
        account.setUsername(username);
        account.setPassword(password);
        account.setName(name);
        em.persist(account);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return true;
    }

    public static Account isLogin(String username, String password) { // ใช้ login return obj ของ user ที่ login
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if (i.getPassword().equals(password) && i.getUsername().equals(username)) {
                em.close();
                emf.close();
                return i; //ไม่ close em ,emf ?
            }
        }
        em.close();
        emf.close();
        return null;
    }

    public static boolean editAccount(Account account, String password, String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if (i.getUsername().equals(account.getUsername())) {
                if (i.getPassword().equals(password)) {
                    em.getTransaction().begin();
                    i.setName(name);
                    em.persist(i);
                    em.getTransaction().commit();
                    em.close();
                    emf.close();
                    return true;
                }
                em.close();
                emf.close();
                return false;
            }
        }
        em.close();
        emf.close();
        return false;
    }

    public static boolean changePassword(Account account, String curpassword, String newpassword) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if (i.getUsername().equals(account.getUsername())) {
                if (i.getPassword().equals(curpassword)) {
                    em.getTransaction().begin();
                    i.setPassword(newpassword);
                    em.persist(i);
                    em.getTransaction().commit();
                    em.close();
                    emf.close();
                    return true;
                }
                em.close();
                emf.close();
                return false;
            }
        }
        em.close();
        emf.close();
        return false;
    }

    public static int addBankAccount(Account account, String number, String pin) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        Account acc = null;
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                acc = i;
                break;
            }
        }
        TypedQuery<BankAccount> bankquery = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
        List<BankAccount> bankresult = bankquery.getResultList();
        for (BankAccount i : bankresult) {
            if (i.getNumber().equals(number)) {
                if (i.getPin().equals(pin)) {
                    if (!acc.getBankaccount().contains(i)) {
                        em.getTransaction().begin();
                        acc.addBankaccount(i);
                        i.addAccount(acc);
                        em.persist(i);
                        em.persist(acc);
                        em.getTransaction().commit();
                        em.close();
                        emf.close();
                        return 0;
                    } else {
                        System.out.println("Already Add This Bank Account!!!");
                        em.close();
                        emf.close();
                        return 1;
                    }
                } else {
                    System.out.println("Invalid Pin!!!");
                    em.close();
                    emf.close();
                    return 2;
                }
            }
        }
        System.out.println("No Bank Account!!!");
        em.close();
        emf.close();
        return 3;
    }

    public static void removeBankAccount(Account account, BankAccount bankaccount) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        Account acc = null;
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                acc = i;
                break;
            }
        }
        TypedQuery<BankAccount> bankquery = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
        List<BankAccount> bankresult = bankquery.getResultList();
        for (BankAccount i : bankresult) {
            if (i.getNumber().equals(bankaccount.getNumber())) {
                em.getTransaction().begin();
                acc.removeBankaccount(i);
                i.removeAccount(acc);
                em.persist(acc);
                em.persist(i);
                em.getTransaction().commit();
                em.close();
                emf.close();
            }
        }
    }

    public static boolean addCreditCard(Account account, String firstname, String lastname, String address1, String address2, String city, String state, String zip, String country, String phone, String cardNumber, String expdate) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        Account acc = null;
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                acc = i;
                break;
            }
        }
        for (CreditCard i : acc.getCreditcard()) {
            if (i.getCardNumber().equals(cardNumber)) {
                return false;
            }
        }
        em.getTransaction().begin();
        acc.addCreditcard(new CreditCard(firstname, lastname, address1, address2, city, state, zip, country, phone, cardNumber, expdate));
        em.persist(acc);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return true;
    }

    public static void removeCreditCard(Account account, CreditCard creditcard) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        Account acc = null;
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                acc = i;
                break;
            }
        }
        TypedQuery<CreditCard> creditcardquery = em.createQuery("SELECT a from CreditCard a", CreditCard.class);
        List<CreditCard> creditcardresult = creditcardquery.getResultList();
        CreditCard cc = null;
        for (CreditCard i : creditcardresult) {
            if (i.getCardNumber().equals(creditcard.getCardNumber())) {
                cc = i;
                break;
            }
        }
        em.getTransaction().begin();
        acc.removeCreditcard(cc);
        em.remove(cc);
        em.persist(acc);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static int withdraw(Account account, BankAccount bankaccount, String withdrawAmount, String password) {
        Double amount = Double.parseDouble(withdrawAmount);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        Account acc = null;
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                if (i.getPassword().equals(password)) {
                    acc = i;
                    break;
                } else {
                    System.out.print("Wrong Password!");
                    em.close();
                    emf.close();
                    return 1;
                }
            }
        }
        TypedQuery<BankAccount> bankquery = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
        List<BankAccount> bankresult = bankquery.getResultList();
        BankAccount bacc = null;
        for (BankAccount i : bankresult) {
            if (i.getNumber().equals(bankaccount.getNumber())) {
                bacc = i;
                break;
            }
        }
        if (acc.getBalance() >= amount) {
            em.getTransaction().begin();
            acc.withdraw(bacc, amount);
            ActivityHistory acchis = new ActivityHistory();
            acchis.setAccount(acc);
            acchis.setAmount(amount);
            acchis.setFromname(acc.getName());
            acchis.setFromuser(acc.getUsername());
            acchis.setToname(bacc.getName());
            acchis.setTouser(bacc.getNumber());
            acchis.setType("Withdraw");
            acc.addActivityHistory(acchis);
            em.persist(acc);
            em.persist(bacc);
            em.persist(acchis);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return 0;
        } else {
            System.out.println("Insufficient Fund!");
            em.close();
            emf.close();
            return 2;
        }
    }

    public static int addBalance(Account account, BankAccount bankaccount, String addAmount, String password) {
        Double amount = Double.parseDouble(addAmount);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        Account acc = null;
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                if (i.getPassword().equals(password)) {
                    acc = i;
                    break;
                } else {
                    System.out.print("Wrong Password!");
                    em.close();
                    emf.close();
                    return 1;
                }
            }
        }
        TypedQuery<BankAccount> bankquery = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
        List<BankAccount> bankresult = bankquery.getResultList();
        BankAccount bacc = null;
        for (BankAccount i : bankresult) {
            if (i.getNumber().equals(bankaccount.getNumber())) {
                bacc = i;
                break;
            }
        }
        if (bacc.getBalance() >= amount) {
            em.getTransaction().begin();
            acc.chargeBank(bacc, amount);
            ActivityHistory acchis = new ActivityHistory();
            acchis.setAccount(acc);
            acchis.setAmount(amount);
            acchis.setFromname(acc.getName());
            acchis.setFromuser(acc.getUsername());
            acchis.setToname(bacc.getName());
            acchis.setTouser(bacc.getNumber());
            acchis.setType("Add Balance");
            acc.addActivityHistory(acchis);
            em.persist(acc);
            em.persist(bacc);
            em.persist(acchis);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return 0;
        } else {
            System.out.println("Insufficient Fund!");
            em.close();
            emf.close();
            return 2;
        }
    }

    public static void addbalanceCC(Account account, String addAmount ,String cardNumber ,String firstname ,String lastname) {
        Double amount = Double.parseDouble(addAmount);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                em.getTransaction().begin();
                i.addBalance(amount);
                ActivityHistory acchis = new ActivityHistory();
                acchis.setAccount(i);
                acchis.setAmount(amount);
                acchis.setFromname(firstname + " " + lastname);
                acchis.setFromuser(cardNumber);
                acchis.setToname(i.getName());
                acchis.setTouser(i.getUsername());
                acchis.setType("Refill via CreditCard");
                i.addActivityHistory(acchis);
                em.persist(i);
                em.persist(acchis);
                em.getTransaction().commit();
                em.close();
                emf.close();
            }
        }
    }

    public static Account refreshAccount(Account account) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                em.close();
                emf.close();
                return i;
            }
        }
        em.close();
        emf.close();
        return null;
    }

    public static void main(String[] args) {

    }
}
