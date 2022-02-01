/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.MiembroJurado;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class JuradoController implements Initializable {

    @FXML
    private TextField tfNombres;
    @FXML
    private TextField tfApellidos;
    @FXML
    private TextField tfPerfil;
    @FXML
    private TextField tfCelular;
    @FXML
    private TextField tfEmail;

    private static String nomFile = "miembroJurados.txt";
    @FXML
    private TextField tfDireccion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void retroceder(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("admin.fxml"));
            Parent root = fxmlLoader.load();
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void irAOpcional(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("opcional.fxml"));
            Parent root = fxmlLoader.load();
            OpcionalController oc = fxmlLoader.<OpcionalController>getController();
            
            oc.setLbMessage("Miembro del Jurado Registrado. ¿Desea registrar otro Miembro del Jurado?");
            oc.setFxmlDestino("jurado.fxml");
            oc.setFxmlOrigen("admin.fxml");
            
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void guardarMiembroJurado(MouseEvent event) {
        if(tfNombres != null && tfApellidos != null && tfCelular != null && tfEmail != null && tfDireccion!= null && tfPerfil!= null){
            ArrayList<MiembroJurado> miembroJurados = MiembroJurado.readFile(nomFile);
            if(MiembroJurado.buscarMiembroJurado(miembroJurados, tfEmail.getText())== null){
                MiembroJurado mj = MiembroJurado.nextMiembroJurado(tfNombres,tfApellidos,tfCelular,tfEmail,tfPerfil);
                mj.saveFile(nomFile);
                irAOpcional();
            }
            else{
                Alert al = new Alert(Alert.AlertType.INFORMATION,"Ya existe un Miembro del Jurado con el mismo e-mail");
                al.show();
            }
        }
        else{
            Alert al = new Alert(Alert.AlertType.ERROR,"Llene todos los campos");
            al.show();
        }
    }
    
}
