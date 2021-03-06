package com.chaowei.view;

import com.chaowei.EmailManager;
import com.chaowei.controller.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewFactory {
    EmailManager emailManager;
    private final ArrayList<Stage> activesStages;
    private ViewInitializer viewInitializer;
    private boolean mainViewInitialized = false;
    private BaseController controller = null;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activesStages = new ArrayList<Stage>();
        viewInitializer = new ViewInitializer(emailManager);
    }

    public ViewFactory(EmailManager emailManager, ViewInitializer viewInitializer) {
        this(emailManager);
        this.viewInitializer = viewInitializer;
    }

    public ViewFactory(EmailManager emailManager, ViewInitializer viewInitializer, BaseController controller){
        this(emailManager, viewInitializer);
        this.controller = controller;
    }

    public boolean isMainViewInitialized(){
        return mainViewInitialized;
    }

    // View options handling:
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

    public void showLoginWindow(){
        this.controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        Stage stage = viewInitializer.initializeStage(controller);
        activesStages.add(stage);
    }

    public void showMainWindow(){
        this.controller = new MainWindowController(emailManager, this, "MainWindow.fxml");
        Stage stage = viewInitializer.initializeStage(controller);
        activesStages.add(stage);
        mainViewInitialized = true;
    }

    public void showComposeMessageWindow(){
        this.controller = new ComposeMessageController(emailManager, this, "ComposeMessageWindow.fxml");
        Stage stage = viewInitializer.initializeStage(controller);
        activesStages.add(stage);
    }

    public void showOptionsWindow(){
        this.controller = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
        Stage stage = viewInitializer.initializeStage(controller);
        activesStages.add(stage);
    }

    public void showEmailDetailsWindow(){
        this.controller = new EmailDetailsController(emailManager, this, "EmailDetailsWindow.fxml");
        Stage stage = viewInitializer.initializeStage(controller);
        activesStages.add(stage);
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

    public void closeStage(Stage stageToClose){
        activesStages.remove(stageToClose);
        stageToClose.close();
    }


    public void updateStyles() {
        for(Stage stage : activesStages) {
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            viewInitializer.applyCurrentStylesToScene(scene);
        }
    }
}
