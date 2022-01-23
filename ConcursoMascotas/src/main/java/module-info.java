module ec.edu.espol.concursomascotas {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.concursomascotas to javafx.fxml;
    exports ec.edu.espol.concursomascotas;
    opens ec.edu.espol.controller to javafx.fxml;
    exports ec.edu.espol.controller;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
}
