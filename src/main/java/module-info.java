module com.example.researcharticlehub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.researcharticlehub to javafx.fxml;
    exports com.example.researcharticlehub;
}