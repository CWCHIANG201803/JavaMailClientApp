package com.chaowei.view;

import com.chaowei.EmailManager;
import com.chaowei.controller.BaseController;
import com.chaowei.controller.LoginWindowController;
import com.chaowei.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow(){
        System.out.println("show login window called");
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");

        initializeStage(controller);
    }

    private void initializeStage(BaseController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void showMainWindow() {
        System.out.println("main window called");
        BaseController controller = new MainWindowController(emailManager, this,"MainWindow.fxml");
        initializeStage(controller);

    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
    }

}
