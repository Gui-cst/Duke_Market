module br.dev.guicosta.dukemarket.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    requires java.sql;

    opens br.dev.guicosta.dukemarket.javafx to javafx.fxml;
    opens br.dev.guicosta.dukemarket.javafx.controller to javafx.fxml;
    
    exports br.dev.guicosta.dukemarket.javafx;
    exports br.dev.guicosta.dukemarket.javafx.controller;
    exports br.dev.guicosta.dukemarket.javafx.DAO;
    exports br.dev.guicosta.dukemarket.javafx.model;
    exports connection;
    
    requires mysql.connector.java;
}
