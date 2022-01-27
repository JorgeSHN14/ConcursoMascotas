/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectomascotas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EvaluacionController implements Initializable {

    @FXML
    private ComboBox<?> juradobox;
    @FXML
    private ComboBox<?> inscripcionbox;
    @FXML
    private ComboBox<?> criteriobox;
    @FXML
    private TextField txtnota;
    @FXML
    private ImageView fotoMascota;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menuDueño(ActionEvent event) throws IOException {
        App.setRoot("dueño");
    }

    @FXML
    private void menuMascota(ActionEvent event) throws IOException {
        App.setRoot("mascota");
    }

    @FXML
    private void menuConcurso(ActionEvent event) throws IOException {
        App.setRoot("concurso");
    }

    @FXML
    private void menuPremio(ActionEvent event) throws IOException {
        App.setRoot("premio");
    }

    @FXML
    private void menuCriterio(ActionEvent event) throws IOException {
        App.setRoot("criterio");
    }

    @FXML
    private void menuInscripcion(ActionEvent event) throws IOException {
        App.setRoot("inscripcion");
    }

    @FXML
    private void menuJurado(ActionEvent event) throws IOException {
        App.setRoot("jurado");
    }

    @FXML
    private void menuEvaluacion(ActionEvent event) throws IOException {
        App.setRoot("evaluacion");
    }

    @FXML
    private void evaluar(ActionEvent event) {
    }
    
}
