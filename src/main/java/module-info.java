module appandcontrollers.maze {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens appandcontrollers.maze to javafx.fxml;
    exports appandcontrollers.maze;
    exports Labirint;
    opens Labirint to javafx.fxml;
}