package com.chaowei.view;

import com.chaowei.EmailManager;
import com.chaowei.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewFactory {
    EmailManager emailManager;
    private ArrayList<Stage> activesStages;
    private boolean mainViewInitialized = false;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activesStages = new ArrayList<Stage>();
    }

    public boolean isMainViewInitialized(){
        return mainViewInitialized;
    }

    // View options handling:
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

    public void showLoginWindow(){
        System.out.println("show login window called");
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        this.initializeStage(controller);
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public void showMainWindow(){
        System.out.println("main window called");
        BaseController controller = new MainWindowController(emailManager, this, "MainWindow.fxml");
        this.initializeStage(controller);
        mainViewInitialized = true;
    }

    private void initializeStage(BaseController baseController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activesStages.add(stage);
    }

    public void showComposeMessageWindow(){
        System.out.println("composeMessage window called");
        BaseController controller = new ComposeMessageController(emailManager, this, "ComposeMessageWindow.fxml");
        this.initializeStage(controller);
    }

    public void showOptionsWindow(){
        System.out.println("Options window called");
        BaseController controller = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
        this.initializeStage(controller);
    }

    public void showEmailDetailsWindow(){
        BaseController controller = new EmailDetailsController(emailManager, this, "EmailDetailsWindow.fxml");
        initializeStage(controller);
    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activesStages.remove(stageToClose);
    }


    public void updateStyles() {
        for(Stage stage : activesStages) {
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();

            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
        }
    }
}
