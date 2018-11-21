/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Account;

/**
 * FXML Controller class
 *
 * @author Xclos
 */
public class FXMLAddCreditCardController extends SceneChangeController implements Initializable {

    private Account account;
    private String previous;
    private Stage stage;

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    @FXML
    private Label wrongformat;

    @FXML
    private TextField firstname, lastname, address1, address2, city, state, zip, country, phone, cardNumber, expdate;

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (firstname.getText().isEmpty()) {
            wrongformat.setText("Please fill the firstname");
        } else if (!firstname.getText().matches("[A-Z][a-z]*")) {
            wrongformat.setText("Firstname must be alphabet and their first alphabet must be Uppercase");

        } else if (lastname.getText().isEmpty()) {
            wrongformat.setText("Please fill the lastname");
        } else if (!lastname.getText().matches("[A-Z][a-z]*")) {
            wrongformat.setText("Lastname must be alphabet and their first alphabet must be Uppercase");

        } else if (address1.getText().isEmpty()) {
            wrongformat.setText("Please fill the address");
        } else if (!address2.getText().matches("[a-zA-Z0-9]*")) {
            wrongformat.setText("Address must be a-z A-Z 0-9");

//        } else if (address2.getText().isEmpty()) {
//            wrongformat.setText("Please fill the address");
        } else if (!address2.getText().matches("[a-zA-Z0-9]*")) {
            wrongformat.setText("Address must be a-z A-Z 0-9");

        } else if (city.getText().isEmpty()) {
            wrongformat.setText("Please fill the city");
        } else if (!city.getText().matches("[a-zA-Z]*")) {
            wrongformat.setText("City must be a-z A-Z");

        } else if (state.getText().isEmpty()) {
            wrongformat.setText("Please fill the state");
        } else if (!state.getText().matches("[a-zA-Z]*")) {
            wrongformat.setText("State must be a-z A-Z");

        } else if (zip.getText().isEmpty()) {
            wrongformat.setText("Please fill the zip code");
        } else if (!zip.getText().matches("[0-9]*")) {
            wrongformat.setText("Zip code must be 0-9");
        } else if (zip.getText().length() != 5) {
            wrongformat.setText("Zip code must have 5 digit");

        } else if (country.getText().isEmpty()) {
            wrongformat.setText("Please fill the country");
        } else if (!country.getText().matches("[a-zA-Z]*")) {
            wrongformat.setText("Country must be a-z A-Z");

        } else if (phone.getText().isEmpty()) {
            wrongformat.setText("Please fill the phone number");
        } else if (!phone.getText().matches("[0-9]*")) {
            wrongformat.setText("Phone number must be 0-9");
        } else if (phone.getText().length() != 10) {
            wrongformat.setText("Phone number must have 10 digit");

//        } else if (email.getText().isEmpty()) {
//            wrongformat.setText("Please fill the city");
//        } else if (!email.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
//            wrongformat.setText("Email must have @ and . Ex. abc@hotmail.com");
        } else if (cardNumber.getText().isEmpty()) {
            wrongformat.setText("Please fill the card number");
        } else if (!cardNumber.getText().matches("[0-9]*")) {
            wrongformat.setText("Card number must be 0-9");
//        } else if (cardNumber.getText().length() != 13) {
//            wrongformat.setText("Card number must have 13 digit");

        } else if (expdate.getText().isEmpty()) {
            wrongformat.setText("Please fill the expiration date");
        } else if (!expdate.getText().matches("[0-9]{4}")) {
            wrongformat.setText("Expiration date format must be MMYY (example : 1218)");
        } else if (expdate.getText().startsWith("00") || parseInt(expdate.getText().substring(0, 2)) > 12) {
            wrongformat.setText("Invalid Month");

        } else {
            wrongformat.setText("");

            if (VWallet.VWallet.addCreditCard(account, firstname.getText(), lastname.getText(), address1.getText(), address2.getText(), city.getText(), state.getText(), zip.getText(), country.getText(), phone.getText(), cardNumber.getText(), expdate.getText())) {
                ((Node) event.getSource()).getScene().getWindow().hide();
                if (previous.equals("wallet")) {
                    walletScene(stage, account);
                } else {
                    selectBankAccountScene(stage, account, previous);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error alert");
                alert.setHeaderText("Already Add This Credit Card");
                alert.setContentText("");

                alert.showAndWait();
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
        }
    }

    @FXML
    private void closePopupAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
