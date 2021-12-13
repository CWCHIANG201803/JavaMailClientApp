package com.chaowei.view;

import com.chaowei.EmailManager;
import com.chaowei.controller.BaseController;
import com.chaowei.controller.LoginWindowController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ViewInitializerTest {
    @Mock
    private EmailManager emailManagerMock;

    @Mock
    private ViewFactory viewFactoryMock;

    private ViewInitializer viewInitializer;

    @BeforeEach
    public void setUp(){
        viewInitializer = new ViewInitializer(emailManagerMock);
    }

    @Test
    void testInitializeStage() {
        BaseController loginWindowController = new LoginWindowController(
                emailManagerMock,
                viewFactoryMock,
                "somePath.fxml"
                );

        assertThrows(IllegalStateException.class, ()-> {
            viewInitializer.initializeStage(loginWindowController);
        });
    }

}