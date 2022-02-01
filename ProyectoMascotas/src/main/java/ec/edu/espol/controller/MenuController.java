/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.proyectomascotas.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class MenuController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mostrarConcursos(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("inscripcion.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void inscribirDuenio(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("duenio.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void inscribirMascota(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mascota.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void adminConcursos(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("admin.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
