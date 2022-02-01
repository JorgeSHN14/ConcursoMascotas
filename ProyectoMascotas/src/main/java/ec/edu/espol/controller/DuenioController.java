/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Duenio;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.proyectomascotas.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DuenioController implements Initializable {

    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCelular;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNombres;
    @FXML
    private TextField tfApellidos;
    
    
    public TextField getTfNombre() {
        return tfNombres;
    }

    public TextField getTfApellido() {
        return tfApellidos;
    }

    public TextField getTfDireccion() {
        return tfDireccion;
    }
    
    public TextField getTfCelular() {
        return tfCelular;
    }

    public TextField getTfEmail() {
        return tfEmail;
    }
    
    private static String nomFile = "dueños.txt";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    
    private void irAOpcional(String emailDuenio){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("opcional.fxml"));
            Parent root = fxmlLoader.load();
            OpcionalController oc = fxmlLoader.<OpcionalController>getController();
            
            oc.setDato(emailDuenio);
            oc.setLbMessage("Dueño registrado. ¿Desea registrar una mascota para este dueño?");
            oc.setFxmlDestino("mascota.fxml");
            oc.setFxmlOrigen("duenio.fxml");
            
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void guardarDuenio(MouseEvent event) {
        if(tfNombres != null && tfApellidos != null && tfCelular != null && tfEmail != null && tfDireccion!= null){
            ArrayList<Duenio> duenios = Duenio.readFile(nomFile);
            if(Duenio.buscarDuenio(duenios, tfEmail.getText())== null){
                Duenio d = Duenio.nextDuenio(tfNombres,tfApellidos,tfCelular,tfEmail,tfDireccion);
                d.saveFile(nomFile);
                irAOpcional(d.getEmail());
            }
            else{
                Alert al = new Alert(AlertType.INFORMATION,"Ya existe un dueño con el mismo e-mail");
                al.show();
            }
        }
        else{
            Alert al = new Alert(AlertType.ERROR,"Llene todos los campos");
            al.show();
        }
    }
    
}
