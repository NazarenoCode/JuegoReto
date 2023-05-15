module com.example.retojuegooficial {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires mongo.java.driver;

    opens com.example.retojuegooficial to javafx.fxml;
    exports com.example.retojuegooficial;
}