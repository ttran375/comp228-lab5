module org.example.johsmith_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.johsmith_comp228lab5 to javafx.fxml;
    exports org.example.johsmith_comp228lab5;
}