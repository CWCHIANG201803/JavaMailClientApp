package com.chaowei.controller;

import com.chaowei.EmailManager;
import com.chaowei.view.ViewFactory;

public abstract class BaseController {
    protected EmailManager emailManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }



    public String getFxmlName() {
        return fxmlName;
    }
}
