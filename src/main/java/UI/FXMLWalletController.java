/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLWalletController implements Initializable {
    
    @FXML
    private GridPane accountGrid;
    
    @FXML
    private void transactionButtonAction(ActionEvent event) throws IOException {
        Parent transactionParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLTransaction.fxml"));
        Scene transactionScene = new Scene(transactionParent);
        transactionScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(transactionScene);
        window.show();
    }   
    
    @FXML
    private void walletButtonAction(ActionEvent event) throws IOException {
        Parent walletParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWallet.fxml"));
        Scene walletScene = new Scene(walletParent);
        walletScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(walletScene);
        window.show();
    }
    
    @FXML
    private void activityButtonAction(ActionEvent event) throws IOException {
        Parent activityParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLActivity.fxml"));
        Scene activityScene = new Scene(activityParent);
        activityScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(activityScene);
        window.show();
    }

    @FXML
    private void optionButtonAction(ActionEvent event) throws IOException {
        Parent optionParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLAccount.fxml"));
        Scene optionScene = new Scene(optionParent);
        optionScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(optionScene);
        window.show();
    }

    @FXML
    private void addAccountButtonAction(ActionEvent event) throws IOException {
        Parent addAccountParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLAddAccount.fxml"));
        Scene addAccountScene = new Scene(addAccountParent);
        addAccountScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addAccountScene);
        window.show();
    }
    
    @FXML
    private void signoutButtonAction(ActionEvent event) throws IOException {
        Parent signoutParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLLogin.fxml"));
        Scene signoutScene = new Scene(signoutParent);
        signoutScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signoutScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void createAccount(ActionEvent event) throws IOException{
        Parent gridParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWallet.fxml"));
        Button acc1Btn = new Button();
        GridPane.setConstraints(acc1Btn,1,0);
        accountGrid.getChildren().addAll(acc1Btn);
        
        
        Scene gridScene = new Scene(gridParent);
        gridScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gridScene);
        window.show();
    }
}
