/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectomascotas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MascotaController implements Initializable {

    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txttipo;
    @FXML
    private TextField txtraza;
    @FXML
    private HBox fechaNacimiento;
    @FXML
    private ComboBox<?> emailDueño;
    @FXML
    private Label lblnombreFoto;

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
    private void guardar(ActionEvent event) {
    }
    
    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void cargarImagen(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            lblnombreFoto.setText(file.getName());
            File fileN = new File("src/main/resources/ec/edu/espol/img/"+file.getName());
            Files.copy(file.toPath(),fileN.toPath());
            System.out.println("Imagen guardada");
        }
    }
}
