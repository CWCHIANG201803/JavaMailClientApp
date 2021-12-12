package com.chaowei.controller;

import com.chaowei.EmailManager;
import com.chaowei.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends BaseController{
    @FXML
    private Button loginButton;

    @FXML
    private TextField emailAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }


    @FXML
    void loginButtonAction() {
        System.out.println("click");
        viewFactory.showMainWindow();
        Stage stage = (Stage)errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
