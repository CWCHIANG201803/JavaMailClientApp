module com.chaowei.java_mail_client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires javafx.web;

    opens com.chaowei to javafx.fxml;
    exports com.chaowei;
    opens com.chaowei.view to javafx.fxml;
    exports com.chaowei.view;
    exports com.chaowei.controller;
    opens com.chaowei.controller to javafx.fxml;
}