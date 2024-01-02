module com.example.trains {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trains to javafx.fxml;
    exports com.example.trains;
}