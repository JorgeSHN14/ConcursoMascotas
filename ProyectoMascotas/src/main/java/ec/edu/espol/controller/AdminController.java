/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controller;

import ec.edu.espol.proyectomascotas.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irAConcurso(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("concurso.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void irAPremio(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("premio.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void irACriterio(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("criterio.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void irAMiembroJurado(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("jurado.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void irAEvaluacion(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("evaluacion.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void retroceder(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
