package com.chaowei.controller;

import com.chaowei.EmailManager;
import com.chaowei.view.ViewFactory;

public abstract class BaseController {
    private EmailManager emailManager;
    private ViewFactory viewFactory;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    private String fxmlName;

    public String getFxmlName() {
        return fxmlName;
    }
}
