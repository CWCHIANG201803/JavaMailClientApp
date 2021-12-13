package com.chaowei.view;

import com.chaowei.EmailManager;
import com.chaowei.controller.BaseController;
import com.chaowei.controller.LoginWindowController;
import com.chaowei.controller.MainWindowController;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ViewFactoryTest {
    @Mock
    private ViewFactory viewFactoryMock;

    @Mock
    private ViewInitializer viewInitializerMock;

    @Mock
    private EmailManager emailManagerMock;

    @Mock
    private BaseController controllerMock;

    @Mock
    private Stage stageMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showMainWindow_isMainViewInitialized_True() {
        ViewFactory viewFactory = new ViewFactory(emailManagerMock, viewInitializerMock);
        viewFactory.showMainWindow();
        assertTrue(viewFactory.isMainViewInitialized());
    }

    @Test
    void showLoginWindow_callOnce() {
        controllerMock = Mockito.mock(LoginWindowController.class);
        viewFactoryMock = Mockito.mock(ViewFactory.class);

        viewFactoryMock.showLoginWindow();
        Mockito.verify(viewFactoryMock, Mockito.atMostOnce()).showLoginWindow();
        Mockito.verify(viewInitializerMock, Mockito.atMostOnce()).initializeStage(controllerMock);
    }

    @Test
    void getColorTheme() {
    }

    @Test
    void setColorTheme() {
    }

    @Test
    void getFontSize() {
    }

    @Test
    void setFontSize() {
    }

    @Test
    void showMainWindow_callOnce() {
        controllerMock = Mockito.mock(MainWindowController.class);
        viewFactoryMock = Mockito.mock(ViewFactory.class);

        viewFactoryMock.showMainWindow();

        Mockito.verify(viewFactoryMock, Mockito.atMostOnce()).showMainWindow();
        Mockito.verify(viewInitializerMock, Mockito.atMostOnce()).initializeStage(controllerMock);
    }




    @Test
    void showComposeMessageWindow() {
    }

    @Test
    void showOptionsWindow() {
    }

    @Test
    void showEmailDetailsWindow() {
    }

    @Test
    void closeStage() {
    }

    @Test
    void updateStyles() {
    }
}