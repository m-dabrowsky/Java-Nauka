module loginInterface {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires mysql.connector.java;
    requires java.sql;

    opens sample;
}