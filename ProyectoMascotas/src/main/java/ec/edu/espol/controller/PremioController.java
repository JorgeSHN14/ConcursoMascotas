/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Premio;
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
public class PremioController implements Initializable {

    @FXML
    private TextField tfNumPremios;
    @FXML
    private ComboBox<String> cbConcursos;
    @FXML
    private Button btEmpezar;
    @FXML
    private TextField tfLugar;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private Button btRegistrar;
    
    private static Concurso c;
    private static int numRestantes;
    private static ArrayList<Premio> premios = new ArrayList();
    private static String nomFile = "premios.txt";
    private static ArrayList<Integer> posiciones = new ArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbConcursos.setItems(FXCollections.observableArrayList((ArrayList) Concurso.nomConcursos("concursos.txt")));
    }
    
    @FXML
    private void empezarRegistros(MouseEvent event) {
        premios.clear();
        if(cbConcursos.getValue() != null && tfNumPremios != null){
            String nomConcurso = cbConcursos.getValue();
            ArrayList<Concurso> concursos = Concurso.readFile("concursos.txt");
            Concurso c = Concurso.buscarConcurso(concursos, nomConcurso);
            if(c != null){
                try{
                    this.c = c;
                    int numCriterios = Integer.parseInt(tfNumPremios.getText());
                    numRestantes = numCriterios;

                    tfNumPremios.setDisable(true);
                    cbConcursos.setDisable(true);
                    btEmpezar.setDisable(true);
                    tfLugar.setDisable(false);
                    tfDescripcion.setDisable(false);
                    btRegistrar.setDisable(false);
                
                
                } catch(NumberFormatException nfe){
                    Alert al = new Alert(Alert.AlertType.ERROR,"El número de criterios debe ser entero");
                    al.show();
                }
            }
            else{
                Alert al = new Alert(Alert.AlertType.ERROR,"El concurso ingresado no está registrado");
                al.show();
            }
        }
            
        else{
            Alert al = new Alert(Alert.AlertType.ERROR,"Llene todos los campos");
            al.show();
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
    private void guardarCriterios(MouseEvent event) {
        if(tfDescripcion.getText() != null){
            try{
                int lugar = Integer.parseInt(tfLugar.getText());
                String nomConcurso = cbConcursos.getValue();
                ArrayList<Concurso> concursos = Concurso.readFile("concursos.txt");
                Concurso c = Concurso.buscarConcurso(concursos, nomConcurso);
                ArrayList<Premio> premios = Premio.readFile(nomFile);
                Premio p = Premio.buscarPremio(premios, lugar, c.getId());
                if(p == null && posiciones.indexOf(lugar)==-1){
                    posiciones.add(lugar);
                    Premio pre = Premio.nextPremio(lugar, tfDescripcion, c.getId());
                    System.out.println(pre);
                    this.premios.add(pre);
                    numRestantes-=1;
                    Alert al = new Alert(AlertType.INFORMATION,"Premio Registrado");
                    al.show();
                    if(numRestantes==0){
                        for(Premio premio: this.premios){
                            premio.saveFile(nomFile);
                        }
                        posiciones.clear();
                        this.premios.clear();
                        tfNumPremios.setDisable(false);
                        cbConcursos.setDisable(false);
                        btEmpezar.setDisable(false);
                        tfDescripcion.setDisable(true);
                        btRegistrar.setDisable(true);
                        tfLugar.setDisable(true);
                        tfNumPremios.clear();
                    }
                    tfDescripcion.clear();
                    tfLugar.clear();
                }
                else{
                    Alert al = new Alert(AlertType.ERROR,"Ya existe premio registrada para ese lugar en el concurso seleccionado");
                    al.show();
                }
            }catch(NumberFormatException nfe){
                Alert al = new Alert(AlertType.ERROR,"El número de lugar debe ser entero");
                al.show();
            }
        }
        else{
            Alert al = new Alert(AlertType.ERROR,"Llene todos los campos");
            al.show();
        }
    }
}
