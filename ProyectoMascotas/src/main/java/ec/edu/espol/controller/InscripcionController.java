/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.proyectomascotas.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InscripcionController implements Initializable {

    @FXML
    private ComboBox<String> cbMascotas;
    @FXML
    private ComboBox<String> cbConcursos;
    
    private static String nomFile = "inscripciones.txt";
    
    /**
     * Initializes the controller class.
     */
    
    public void setCbMascotas(String cbMascotas) {
        this.cbMascotas.setValue(cbMascotas);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbMascotas.setItems(FXCollections.observableArrayList((ArrayList) Mascota.nomMascotas("mascotas.txt")));
        cbConcursos.setItems(FXCollections.observableArrayList((ArrayList) Concurso.nomConcursos("concursos.txt")));
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
    
    private void irAOpcional(double precio){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("opcional.fxml"));
            Parent root = fxmlLoader.load();
            OpcionalController oc = fxmlLoader.<OpcionalController>getController();
            
            oc.setLbMessage("Inscripción Registrada. El precio es de"+precio+"¿Desea realizar otra inscripción?");
            oc.setFxmlDestino("inscripcion.fxml");
            oc.setFxmlOrigen("menu.fxml");
            
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void guardarInscripcion(MouseEvent event) {
        
        if(cbMascotas.getValue() != null && cbConcursos.getValue() != null){
            String nomMascota = cbMascotas.getValue();
            String nomConcurso = cbConcursos.getValue();
            ArrayList<Mascota> mascotas = Mascota.readFile("mascotas.txt");
            Mascota m = Mascota.buscarMascota(mascotas, nomMascota);
            ArrayList<Concurso> concursos = Concurso.readFile("concursos.txt");
            Concurso c = Concurso.buscarConcurso(concursos, nomConcurso);
            if(m != null && c != null){
                ArrayList<Inscripcion> Inscripciones = new ArrayList<>();
                if(Inscripcion.buscarInscripcion(Inscripciones, m, c) == null){
                    Inscripcion i = Inscripcion.nextInscripcion(m, c);
                    i.saveFile(nomFile);
                    irAOpcional(i.getId()*i.getDescuento());
                }
                else{
                    Alert al = new Alert(Alert.AlertType.INFORMATION,"Ya existe un registro de su mascota en el concurso seleccionado");
                    al.show();
                }
            }
            else{
                if(m == null){
                    Alert al = new Alert(Alert.AlertType.INFORMATION,"La mascota que ingresó no está registrada");
                    al.show();
                }
                if(c == null){
                    Alert al = new Alert(Alert.AlertType.INFORMATION,"El concurso que ingresó no existe");
                    al.show();
                }
            }
        }
        else{
            Alert al = new Alert(Alert.AlertType.ERROR,"Llene todos los campos");
            al.show();
        }
    }
    
}
