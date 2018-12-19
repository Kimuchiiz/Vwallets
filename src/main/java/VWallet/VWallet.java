/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VWallet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import models.Account;
import models.ActivityHistory;
import models.BankAccount;
import models.CreditCard;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Xclos
 */
public class VWallet {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    static String toHexadecimal(String source) {

        return toHexadecimal(source.getBytes());
    }

    static String toHexadecimal(byte[] source) {

        StringBuilder sb = new StringBuilder();

        for (byte b : source) {

            String toAppend = String.format("%2X", b).replace(" ", "0"); // %X Hexadecimal
            sb.append(toAppend);
        }

        return sb.toString();
    }

    static String toMD5Hash(String source) {

        String result = "";

        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5HashBytes = md5.digest(source.getBytes());

            result = toHexadecimal(md5HashBytes);
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        return result;
    }

    public static int setRegister(String username, String password, String name, String email) { //func register ถ้าสมัครได้ return true ไม่ได้ return false
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        Account account = new Account();

        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if (i.getUsername().equals(username)) {
                em.close();
                emf.close();
                return 1;
            }
            if (i.getEmail().equals(email)) {
                em.close();
                emf.close();
                return 2;
            }
        }
        em.getTransaction().begin();
        account.setUsername(username);
        account.setPassword(toMD5Hash(password));
        account.setName(name);
        account.setEmail(email);
        em.persist(account);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return 0;
    }

    public static Account isLogin(String username, String password) { // ใช้ login return obj ของ user ที่ login
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if (i.getPassword().equals(toMD5Hash(password)) && i.getUsername().equals(username)) {
                em.close();
                emf.close();
                return i; //ไม่ close em ,emf ?
            }
        }
        em.close();
        emf.close();
        return null;
    }

    public static void editAccount(Account account, String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if (i.getUsername().equals(account.getUsername())) {
                em.getTransaction().begin();
                i.setName(name);
                em.persist(i);
                em.getTransaction().commit();
                break;
            }
        }
        em.close();
        emf.close();
    }

    public static boolean changePassword(Account account, String curpassword, String newpassword) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if (i.getUsername().equals(account.getUsername())) {
                if (i.getPassword().equals(toMD5Hash(curpassword))) {
                    em.getTransaction().begin();
                    i.setPassword(toMD5Hash(newpassword));
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
    
    public static int changeEmail(Account account, String password, String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> result = query.getResultList();
        for (Account i : result) {
            if(i.getEmail().equals(email)){
                em.close();
                emf.close();
                return 2;
            }
        }
        for (Account i : result) {
            if (i.getUsername().equals(account.getUsername())) {
                if (i.getPassword().equals(toMD5Hash(password))) {
                    em.getTransaction().begin();
                    i.setEmail(email);
                    em.persist(i);
                    em.getTransaction().commit();
                    em.close();
                    emf.close();
                    return 0;
                }
                em.close();
                emf.close();
                return 1;
            }
        }
        em.close();
        emf.close();
        return 3;
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
                if (i.getPin().equals(toMD5Hash(pin))) {
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
                if (i.getPassword().equals(toMD5Hash(password))) {
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
            acchis.addAccount(acc);
            acchis.setAmount(amount);
            acchis.setFromname(acc.getName());
            acchis.setFromuser(acc.getUsername());
            acchis.setToname(bacc.getName());
            acchis.setTouser(bacc.getNumber() + " (Bank)");
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
                if (i.getPassword().equals(toMD5Hash(password))) {
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
            acchis.addAccount(acc);
            acchis.setAmount(amount);
            acchis.setFromname(acc.getName());
            acchis.setFromuser(acc.getUsername());
            acchis.setToname(bacc.getName());
            acchis.setTouser(bacc.getNumber() + " (Bank)");
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

    public static void addbalanceCC(Account account, String addAmount, String cardNumber, String firstname, String lastname) {
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
                acchis.addAccount(i);
                acchis.setAmount(amount);
                acchis.setFromname(firstname + " " + lastname);
                acchis.setFromuser(cardNumber + " (CreditCard)");
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

    public static Account checkUsername(Account account, String username) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        for (Account i : accountresult) {
            if (i.getUsername().equals(username) && !i.getUsername().equals(account.getUsername())) {
                em.close();
                emf.close();
                return i;
            }
        }
        em.close();
        emf.close();
        return null;
    }

    public static BankAccount getBankaccount(String bankaccnum) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<BankAccount> bankaccountquery = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
        List<BankAccount> bankaccountresult = bankaccountquery.getResultList();
        for (BankAccount i : bankaccountresult) {
            if (i.getNumber().equals(bankaccnum)) {
                em.close();
                emf.close();
                return i;
            }
        }
        em.close();
        emf.close();
        return null;
    }

    public static int transfer(Account account, Account account2, String transferAmount, String password) {
        Double amount = Double.parseDouble(transferAmount);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        Account acc = null;
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                if (i.getPassword().equals(toMD5Hash(password))) {
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
        for (Account acc2 : accountresult) {
            if (acc2.getUsername().equals(account2.getUsername())) {
                if (acc.getBalance() >= amount) {
                    em.getTransaction().begin();
                    acc.transfer(acc2, amount);
                    ActivityHistory acchis = new ActivityHistory();
                    acchis.addAccount(acc);
                    acchis.addAccount(acc2);
                    acchis.setAmount(amount);
                    acchis.setFromname(acc.getName());
                    acchis.setFromuser(acc.getUsername());
                    acchis.setToname(acc2.getName());
                    acchis.setTouser(acc2.getUsername());
                    acchis.setType("Transfer");
                    acc.addActivityHistory(acchis);
                    acc2.addActivityHistory(acchis);
                    em.persist(acc);
                    em.persist(acc2);
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
        }
        return 3;
    }

    public static int payment(Account account, Account account2, String paymentAmount, String password, BankAccount bankaccount) {
        Double amount = Double.parseDouble(paymentAmount);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class);
        List<Account> accountresult = accountquery.getResultList();
        Account acc = null;
        for (Account i : accountresult) {
            if (i.getUsername().equals(account.getUsername())) {
                if (i.getPassword().equals(toMD5Hash(password))) {
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
        for (Account acc2 : accountresult) {
            if (acc2.getUsername().equals(account2.getUsername())) {
                TypedQuery<BankAccount> bankaccountquery = em.createQuery("SELECT a from BankAccount a", BankAccount.class);
                List<BankAccount> bankaccountresult = bankaccountquery.getResultList();
                for (BankAccount i : bankaccountresult) {
                    if (i.getNumber().equals(bankaccount.getNumber())) {
                        if (acc.getBalance() >= amount) {
                            em.getTransaction().begin();
                            acc.withdraw(i, amount);
                            ActivityHistory acchis = new ActivityHistory();
                            acchis.addAccount(acc);
                            acchis.addAccount(acc2);
                            acchis.setAmount(amount);
                            acchis.setFromname(acc.getName());
                            acchis.setFromuser(acc.getUsername());
                            acchis.setToname(i.getName());
                            acchis.setTouser(i.getNumber() + " (Bank)");
                            acchis.setType("Payment");
                            acc.addActivityHistory(acchis);
                            acc2.addActivityHistory(acchis);
                            em.persist(acc);
                            em.persist(acc2);
                            em.persist(i);
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
                }
            }
        }
        return 3;
    }

    public static Account refreshAccount(Account account) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class
        );
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

    public static boolean resetPassword(String email) throws MessagingException {
        String random = RandomStringUtils.randomAlphanumeric(6);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/AccountDB.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> accountquery = em.createQuery("SELECT a from Account a", Account.class
        );
        List<Account> accountresult = accountquery.getResultList();
        for (Account i : accountresult) {
            if (i.getEmail().equals(email)) {
                em.getTransaction().begin();
                i.setPassword(toMD5Hash(random));
                em.persist(i);
                em.getTransaction().commit();
                em.close();
                emf.close();
                generateAndSendEmail(email, i.getUsername(), random);
                return true;
            }
        }
        em.close();
        emf.close();
        return false;

    }

    public static void generateAndSendEmail(String email, String username, String random) throws AddressException, MessagingException {

        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("59010649@kmitl.ac.th"));
        generateMailMessage.setSubject("Password reset");
        String emailBody = "Your Password has been reset to <br><br>" + random + "<br><br> In case you forgotten, your username is: " + username + "<br> Please Change Your Password after login <br><br> Regards, <br>VWallet";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "VWallet.kmitl@gmail.com", "VWallet2018");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    public static void main(String[] args) {

    }
}
