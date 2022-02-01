/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Criterio;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CriterioController implements Initializable {
    
    @FXML
    private ComboBox<String> cbConcursos;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private TextField tfNumCriterios;
    @FXML
    private Button btRegistrar;
    @FXML
    private Button btEmpezar;
    
    private static Concurso c;
    private static int numRestantes;
    private static ArrayList<Criterio> criterios = new ArrayList();
    private static String nomFile = "criterios.txt";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbConcursos.setItems(FXCollections.observableArrayList((ArrayList) Concurso.nomConcursos("concursos.txt")));
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
    private void guardarCriterios(MouseEvent event) {
        
            if(tfDescripcion.getText() != null){
                String nomConcurso = cbConcursos.getValue();
                ArrayList<Concurso> concursos = Concurso.readFile("concursos.txt");
                Concurso c = Concurso.buscarConcurso(concursos, nomConcurso);
                Criterio cr = Criterio.nextCriterio(tfDescripcion, c.getId());
                criterios.add(cr);
                numRestantes-=1;
                Alert al = new Alert(AlertType.INFORMATION,"Criterio Registrado");
                al.show();
                if(numRestantes==0){
                    for(Criterio ctr: criterios){
                        ctr.saveFile(nomFile);
                    }
                    criterios.clear();
                    tfNumCriterios.setDisable(false);
                    cbConcursos.setDisable(false);
                    btEmpezar.setDisable(false);
                    tfDescripcion.setDisable(true);
                    btRegistrar.setDisable(true);
                    tfNumCriterios.clear();
                }
                tfDescripcion.clear();
            }
            else{
                Alert al = new Alert(AlertType.ERROR,"Llene todos los campos");
                al.show();
            }
    }

    @FXML
    private void empezarRegistros(MouseEvent event) {
        if(cbConcursos.getValue() != null && tfNumCriterios != null){
            String nomConcurso = cbConcursos.getValue();
            ArrayList<Concurso> concursos = Concurso.readFile("concursos.txt");
            Concurso c = Concurso.buscarConcurso(concursos, nomConcurso);
            if(c != null){
                try{
                    this.c = c;
                    int numCriterios = Integer.parseInt(tfNumCriterios.getText());
                    numRestantes = numCriterios;

                    tfNumCriterios.setDisable(true);
                    cbConcursos.setDisable(true);
                    btEmpezar.setDisable(true);
                    tfDescripcion.setDisable(false);
                    btRegistrar.setDisable(false);
                
                
                } catch(NumberFormatException nfe){
                    Alert al = new Alert(AlertType.ERROR,"El número de criterios debe ser entero");
                    al.show();
                }
            }
            else{
                Alert al = new Alert(AlertType.ERROR,"El concurso ingresado no está registrado");
                al.show();
            }
        }
            
        else{
            Alert al = new Alert(AlertType.ERROR,"Llene todos los campos");
            al.show();
        }
    }
}
