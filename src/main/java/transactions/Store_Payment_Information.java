/**
* BluePay Java Sample code.
*
* This code sample runs a $0.00 AUTH transaction
* against a customer using test payment information.
* This stores the customer's payment information securely in
* BluePay to be used for further transactions.
*/

package transactions;
import bluepay.*;
import java.util.HashMap; 

public class Store_Payment_Information {

  public static void main(String[] args) {

    String ACCOUNT_ID = "Merchant's Account ID Here";
    String SECRET_KEY = "Merchant's Secret Key Here";
    String MODE = "TEST";

    BluePay payment = new BluePay(
        ACCOUNT_ID,
        SECRET_KEY,
        MODE
    );

    // Set Customer Information  
    HashMap<String, String> customerParams = new HashMap<>();
    customerParams.put("firstName", "Bob");
    customerParams.put("lastName", "Tester");
    customerParams.put("address1", "123 Test St.");
    customerParams.put("address2", "Apt #500");
    customerParams.put("city", "Testville");
    customerParams.put("state", "IL");
    customerParams.put("zip", "54321");
    customerParams.put("country", "USA");
    customerParams.put("phone", "123-123-12345");
    customerParams.put("email", "test@bluepay.com");
    payment.setCustomerInformation(customerParams);

    // Set Credit Card Information
    HashMap<String, String> ccParams = new HashMap<>();
    ccParams.put("cardNumber", "4111111111111111");
    ccParams.put("expirationDate", "1225");
    ccParams.put("cvv2", "123");
    payment.setCCInformation(ccParams);

    // Sets a Card Authorization at $0.00
    HashMap<String, String> authParams = new HashMap<>();
    authParams.put("amount", "0.00");
    payment.auth(authParams);

    // Makes the API Request with BluePay
    try {
      payment.process();
    } catch (Exception ex) {
      System.out.println("Exception: " + ex.toString());
      System.exit(1);
    }

    // If transaction was successful reads the responses from BluePay
    if (payment.isSuccessful()) {
      System.out.println("Transaction Status: " + payment.getStatus());
      System.out.println("Transaction ID: " + payment.getTransID());
      System.out.println("Transaction Message: " + payment.getMessage());
      System.out.println("AVS Result: " + payment.getAVS());
      System.out.println("CVV2: " + payment.getCVV2());
      System.out.println("Masked Payment Account: " + payment.getMaskedPaymentAccount());
      System.out.println("Card Type: " + payment.getCardType());    
      System.out.println("Authorization Code: " + payment.getAuthCode());
    } else {
      System.out.println("Error: " + payment.getMessage());
    }
  }
}