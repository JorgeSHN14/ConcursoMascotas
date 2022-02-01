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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class OpcionalController implements Initializable {

    @FXML
    private Label lbMessage;
    
    private String dato;
    
    private String fxmlDestino;
    
    private String fxmlOrigen;

    public void setLbMessage(String lbMessage) {
        this.lbMessage.setText(lbMessage);
    }
    
    public void setDato(String dato) {
        this.dato = dato;
    }    

    public void setFxmlDestino(String fxmlDestino) {
        this.fxmlDestino = fxmlDestino;
    }

    public void setFxmlOrigen(String fxmlOrigen) {
        this.fxmlOrigen = fxmlOrigen;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irA(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlDestino));
            
            Parent root = fxmlLoader.load();
            if(fxmlDestino.equals("mascota.fxml")){
                MascotaController mc = fxmlLoader.<MascotaController>getController();
                mc.setCbEmailDuenio(dato);
            }
            
            if(fxmlDestino.equals("inscripcion.fxml")){
                InscripcionController ic = fxmlLoader.<InscripcionController>getController();
                ic.setCbMascotas(dato);
            }
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void retroceder(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlOrigen));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
