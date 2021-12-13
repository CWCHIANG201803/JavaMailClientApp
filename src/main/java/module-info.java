module com.chaowei.javamailclientapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires javafx.web;
    requires java.mail;
    requires java.desktop;
    requires java.activation;

    opens com.chaowei;
    opens com.chaowei.view;
    opens com.chaowei.controller;
    opens com.chaowei.model;
    exports com.chaowei;
    exports com.chaowei.view;
    exports com.chaowei.controller;
}