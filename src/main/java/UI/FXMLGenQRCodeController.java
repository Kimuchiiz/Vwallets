/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Account;
import models.BankAccount;

/**
 * FXML Controller class
 *
 * @author Xclos
 */
public class FXMLGenQRCodeController extends SceneChangeController implements Initializable {

    private Account account;
    private BankAccount bankaccount;

    @FXML
    private TextField amount;
    @FXML
    private Label amountLabel;
    @FXML
    private Button walletBtn;
    @FXML
    private Button transactionBtn;
    @FXML
    private Button activityBtn;
    @FXML
    private Button optionBtn;
    @FXML
    private Button signoutBtn;
    @FXML
    private Label numberLabel;
    @FXML
    private Label nameLabel;

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    public void setBankaccount(BankAccount bankaccount) {
        this.bankaccount = bankaccount;
    }

    public void setLabel() {
        numberLabel.setText(bankaccount.getNumber());
        nameLabel.setText(bankaccount.getName());
    }

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (amount.getText().isEmpty()) {
            amountLabel.setText("Please fill the amount");
        } else if (amount.getText().matches("[0]*([\\.][0]*)?")) {
            amountLabel.setText("Amount Can't be 0");
        } else {
            amountLabel.setText("");
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("QRCode");

            //Show save file dialog
            File file = fileChooser.showSaveDialog((Stage) ((Node) event.getSource()).getScene().getWindow());

            if (file != null) {
                try {
                    generateQRCodeImage(bankaccount.getNumber() + " " + amount.getText(), 350, 350, file.getAbsolutePath());
                } catch (WriterException e) {
                    System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
                }
            }
        }
    }

    private static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    @FXML
    private void transactionButtonAction(ActionEvent event) {
        transactionScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void walletButtonAction(ActionEvent event) {
        walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void activityButtonAction(ActionEvent event) {
        activityScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void optionButtonAction(ActionEvent event) {
        optionScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void signoutButtonAction(ActionEvent event) {
        loginScene((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*([\\.]\\d{0,2})?")) {
                    amount.setText(oldValue);
                }
            }
        });
    }
}
