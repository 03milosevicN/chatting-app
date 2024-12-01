module chatting.app {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.Model to javafx.graphics;
    exports org.example.View to javafx.graphics;
    exports org.example.ViewModel to javafx.graphics;
    exports org.example.Core to javafx.graphics;
}