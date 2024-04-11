module org.example.comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.example.comp228lab5 to javafx.fxml;
    exports org.example.comp228lab5;
}