/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import VWallet.VWallet;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import models.Account;

/**
 *
 * @author USER
 */
public class FXMLLoginController extends SceneChangeController implements Initializable {
    
    @FXML
    private Button signinBtn, signupBtn;

    @FXML
    private Label wrongformat;

    @FXML
    private TextField username;

    @FXML
    private PasswordField psw;

    @FXML
    private void signinButtonAction(ActionEvent event){
        //"[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+" for email
        if (!username.getText().matches("[a-zA-Z0-9]*") || username.getText().isEmpty()) {
            wrongformat.setText("username must be a-z A-Z 0-9");
            psw.clear();
        } else if (username.getText().length() < 4 || username.getText().length() > 12) {
            wrongformat.setText("username must have 4-12 digit");
            psw.clear();
        } else if (psw.getText().isEmpty()) {
            wrongformat.setText("Please fill the password");
        } else if (!psw.getText().matches("[a-zA-Z0-9]*")) {
            wrongformat.setText("password must be a-z A-Z 0-9");
            psw.clear();
        } else {
            wrongformat.setText("");

            Account account = VWallet.isLogin(username.getText(), psw.getText());
            if (account != null) {
                walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
            }
            else{
                wrongformat.setText("Username and Password not match");
                psw.clear();
                
            }
             
        }
    }

    @FXML
    private void signupButtonAction(ActionEvent event) {
        signupScene((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ///////////////////////////////////// Limit  input in TextField ////////////////////////////////
        username.setOnKeyTyped(event -> {
            int maxCharacters = 12;
            if (username.getText().length() >= maxCharacters) {
                event.consume();
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////////////// Username Format ///////////////////////////////////
        UnaryOperator<TextFormatter.Change> usernameFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Za-z0-9]*")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> usernameformatter = new TextFormatter<>(usernameFilter);
        username.setTextFormatter(usernameformatter);

        /////////////////////////////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////////////// Password Format ///////////////////////////////////
        UnaryOperator<TextFormatter.Change> pswFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Za-z0-9]*")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> pswformatterr = new TextFormatter<>(pswFilter);
        psw.setTextFormatter(pswformatterr);

        /////////////////////////////////////////////////////////////////////////////////////////////////
    }
    
    public void resetPassword(ActionEvent event){
        enterEmailScene((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
