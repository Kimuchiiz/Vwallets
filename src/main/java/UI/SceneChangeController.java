/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Account;
import models.BankAccount;
import models.CreditCard;

/**
 *
 * @author Xclos
 */
public abstract class SceneChangeController {

    private double xOffset = 0;
    private double yOffset = 0;

    public void loginScene(Stage stage) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLLogin.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        changeScene(stage, Loader);
    }

    public void signupScene(Stage stage) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLRegistration.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        changeScene(stage, Loader);
    }

    public void walletScene(Stage stage, Account account) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLWallet.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLWalletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLWalletController display = Loader.getController();
        display.setAccount(account);
        changeScene(stage, Loader);
    }

    public void transactionScene(Stage stage, Account account) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLTransaction.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLTransactionController display = Loader.getController();
        display.setAccount(account);
        changeScene(stage, Loader);
    }

    public void activityScene(Stage stage, Account account) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLActivity.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLActivityController display = Loader.getController();
        display.setAccount(account);
        changeScene(stage, Loader);
    }

    public void optionScene(Stage stage, Account account) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLAccount.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLAccountController display = Loader.getController();
        display.setAccount(account);
        changeScene(stage, Loader);
    }

    public void addBankAccountScene(Stage stage, Account account, String previous) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLAddBankAccount.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAddBankAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLAddBankAccountController display = Loader.getController();
        display.setAccount(account);
        display.setStage(stage);
        display.setPrevious(previous);
        
        popupScene(stage,Loader);
    }
    
    public void addCreditCardScene(Stage stage, Account account, String previous) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLAddCreditCard.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAddCreditCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLAddCreditCardController display = Loader.getController();
        display.setAccount(account);
        display.setStage(stage);
        display.setPrevious(previous);
        
        popupScene(stage,Loader);
    }
    
    public void selectBankAccountScene(Stage stage, Account account,String option){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLSelectBankAccount.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLSelectBankAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLSelectBankAccountController display = Loader.getController();
        display.setAccount(account);
        display.setOption(option);
        changeScene(stage, Loader);
    }
    
    public void selectCreditCardScene(Stage stage, Account account){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLSelectCreditCard.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLSelectCreditCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLSelectCreditCardController display = Loader.getController();
        display.setAccount(account);
        
        changeScene(stage, Loader);
    }
    
    public void addOrWithdrawScene(Stage stage, Account account, BankAccount bankaccount, String option){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLAddorWithdraw.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAddorWithdrawController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLAddorWithdrawController display = Loader.getController();
        display.setAccount(account);
        display.setBankaccount(bankaccount);
        display.setLabel(option);
        
        changeScene(stage, Loader);
    }
    
    public void enterPasswordScene(Stage stage, Account account, BankAccount bankaccount, String amount, String option, Account account2) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLEnterPassword.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLEnterPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLEnterPasswordController display = Loader.getController();
        display.setAccount(account);
        display.setStage(stage);
        display.setOption(option);
        display.setBankAccount(bankaccount);
        display.setAmount(amount);
        display.setAccount2(account2);
        
        popupScene(stage,Loader);
    }

     public void refillCreditScene(Stage stage, Account account, CreditCard creditcard){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLRefillCredit.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLRefillCreditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLRefillCreditController display = Loader.getController();
        display.setAccount(account);
        display.autofill(creditcard);
        
        changeScene(stage, Loader);
    }
     
    public void transferScene(Stage stage, Account account){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLTransfer.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLTransferController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLTransferController display = Loader.getController();
        display.setAccount(account);
        
        changeScene(stage, Loader);
    }
    
    public void confirmScene(Stage stage, Account account, Account account2, String amount, BankAccount bankaccount, String option){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLConfirm.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLConfirmController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLConfirmController display = Loader.getController();
        display.setAccount(account);
        display.setAccount2(account2);
        display.setBankaccount(bankaccount);
        display.setOption(option);
        display.setAmount(amount);
        display.setLabel();
        
        changeScene(stage, Loader);
    }
    
    public void webcamScene(Stage stage, Account account){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLWebCamQRCodeScanner.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLWebCamQRCodeScannerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLWebCamQRCodeScannerController display = Loader.getController();
        display.setAccount(account);
        display.setStage(stage);
        
        popupScene(stage, Loader);
    }
    
    public void genQRCodeScene(Stage stage, Account account, BankAccount bankaccount){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLGenQRCode.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLGenQRCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLGenQRCodeController display = Loader.getController();
        display.setAccount(account);
        display.setBankaccount(bankaccount);
        display.setLabel();
        
        changeScene(stage, Loader);
    }
    
    public void changeScene(Stage stage, FXMLLoader Loader) {
        Parent root = Loader.getRoot();
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/CSS.css");
        stage.setScene(scene);
        stage.show();
        root.requestFocus();     //unselect first node
    }

    public void popupScene(Stage stage, FXMLLoader Loader) {
        final Stage popup = new Stage();
        popup.setTitle("PopUp");
        popup.initStyle(StageStyle.UNDECORATED);
        Parent root = Loader.getRoot();
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                popup.setX(event.getScreenX() - xOffset);
                popup.setY(event.getScreenY() - yOffset);
            }
        });      
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(stage);
        Scene popupScene = new Scene(root);
        popupScene.getStylesheets().add("/styles/CSS.css");
        popup.setScene(popupScene);
        popup.show();
        root.requestFocus();     //unselect first node
    }
}
