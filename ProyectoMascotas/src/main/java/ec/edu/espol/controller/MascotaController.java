/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Duenio;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.proyectomascotas.App;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MascotaController implements Initializable {

    @FXML
    private Label lblnombreFoto;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfTipo;
    @FXML
    private TextField tfRaza;
    @FXML
    private DatePicker dpNacimiento;
    @FXML
    private ComboBox<String> cbEmailDuenio;
    
    private File file = null;
    private static String nomFile = "mascotas.txt";

    public void setCbEmailDuenio(String cbEmailDuenio) {
        this.cbEmailDuenio.setValue(cbEmailDuenio);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        cbEmailDuenio.setItems(FXCollections.observableArrayList((ArrayList) Duenio.emailDuenios("dueños.txt")));
    }
    
    private void irAOpcional(String nomMascota){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("opcional.fxml"));
            Parent root = fxmlLoader.load();
            OpcionalController oc = fxmlLoader.<OpcionalController>getController();
            
            oc.setDato(nomMascota);
            oc.setLbMessage("Mascota Registrada ¿Desea registrar esta mascota a un concurso?");
            oc.setFxmlDestino("inscripcion.fxml");
            oc.setFxmlOrigen("mascota.fxml");
            
            
            App.getScene().setRoot(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void cargarImagen(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(null);
        if (file != null)
            lblnombreFoto.setText(file.getName());
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

    @FXML
    private void guardarMascota(MouseEvent event) {
        
        LocalDate ldNac = dpNacimiento.getValue();
        
        if(tfNombre != null && tfTipo != null && tfRaza != null && ldNac != null && cbEmailDuenio.getValue()!= null && file != null){
            ArrayList<Duenio> duenios = Duenio.readFile("dueños.txt");
            ArrayList<Mascota> mascotas = Mascota.readFile(nomFile);
            
            Duenio d = Duenio.buscarDuenio(duenios, (String)cbEmailDuenio.getValue());
            
            Date dtNac = new Date(ldNac.getYear()-1900,ldNac.getMonthValue()-1,ldNac.getDayOfMonth());
            if(d!=null){
                if((Mascota.buscarMascota(mascotas,tfNombre.getText()) == null)){
                    Mascota c = Mascota.nextMascota(d.getId(), tfNombre, tfTipo, tfRaza, dtNac);
                    c.saveFile(nomFile);
                    File fileN = new File("src/main/resources/imgMascotas/" + c.getId() + ".png");
                    try {
                        Files.copy(file.toPath(),fileN.toPath());
                    } catch (IOException ex) {}
                    irAOpcional(tfNombre.getText());
                }
                else{
                    Alert al = new Alert(AlertType.INFORMATION,"Ya existe una mascota registrada con ese nombre");
                    al.show();
                }
            }
            else{
                    Alert al = new Alert(Alert.AlertType.INFORMATION,"El dueño que ingresó no está registrado");
                    al.show();
            }
        }
        else{
            Alert al = new Alert(AlertType.ERROR,"Llene todos los campos");
            al.show();
        }
    }

}
