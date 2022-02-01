/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Concurso;
import static ec.edu.espol.model.Concurso.readFile;
import ec.edu.espol.proyectomascotas.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class ConcursoController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private DatePicker dpFechaConcurso;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaCierre;
    @FXML
    private TextField tfCosto;
    @FXML
    private TextField tfTematica;
    
    private static String nomFile = "concursos.txt";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void irAOpcional(String nomMascota){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("opcional.fxml"));
            Parent root = fxmlLoader.load();
            OpcionalController oc = fxmlLoader.<OpcionalController>getController();
            
            oc.setLbMessage("Concurso Registrado ¿Desea registrar otro concurso?");
            oc.setFxmlDestino("concurso.fxml");
            oc.setFxmlOrigen("admin.fxml");
            
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    @FXML
    private void guardarConcurso(MouseEvent event) {
        LocalDate ldConc = dpFechaConcurso.getValue();
        LocalDate ldInicio = dpFechaInicio.getValue();
        LocalDate ldCierre = dpFechaCierre.getValue();
                
        if(tfNombre != null && ldConc != null && ldInicio != null && ldCierre != null && tfTematica!= null && tfCosto != null){
            
            Date dtConc = new Date(ldConc.getYear()-1900,ldConc.getMonthValue()-1,ldConc.getDayOfMonth());
            Date dtInicio = new Date(ldInicio.getYear()-1900,ldInicio.getMonthValue()-1,ldInicio.getDayOfMonth());
            Date dtCierre = new Date(ldCierre.getYear()-1900,ldCierre.getMonthValue()-1,ldCierre.getDayOfMonth());
            
            ArrayList<Concurso> concursos = readFile(nomFile);
            
            if((Concurso.buscarConcurso(concursos,tfNombre.getText()) == null)){
                if(dtConc.compareTo(dtCierre)>0 && dtConc.compareTo(dtInicio)>0 && dtCierre.compareTo(dtInicio)>0){
                    try{
                        Double costo = Double.parseDouble(tfCosto.getText());
                        Concurso c = Concurso.nextConcurso(tfNombre, dtConc, dtInicio, dtCierre, tfTematica, costo);
                        c.saveFile(nomFile);
                    }catch (NumberFormatException nfe) {
                        Alert al = new Alert(AlertType.ERROR,"Debe introducir un número decimal correcto en costo");
                        al.show();
                    }
                }
                else{
                    Alert al = new Alert(AlertType.ERROR,"La fecha del concurso debe ser posterior a la fecha de inicio y cierre de incripciones, a su vez la fecha de cierre de inscripciones debe ser posterior a la fecha de inicio de inscripciones");
                    al.show();
                }
            }
            else{
                Alert al = new Alert(AlertType.INFORMATION,"Ya existe una mascota registrada con ese nombre");
                al.show();
            }
        }
        else{
            Alert al = new Alert(AlertType.ERROR,"Llene todos los campos");
            al.show();
        }
    }
    
}
