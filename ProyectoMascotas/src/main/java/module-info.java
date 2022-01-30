module ec.edu.espol.proyectomascotas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectomascotas to javafx.fxml;
    exports ec.edu.espol.proyectomascotas;
    
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
    opens ec.edu.espol.controller to javafx.fxml;
    exports ec.edu.espol.controller;
}
