package com.chaowei;

import com.chaowei.view.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/MainWindow.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}