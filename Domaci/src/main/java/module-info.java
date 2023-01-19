module com.example.domaci {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.domaci to javafx.fxml;
    exports com.example.domaci;
}