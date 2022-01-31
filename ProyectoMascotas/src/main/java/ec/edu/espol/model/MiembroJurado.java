/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author LENOVO
 */
public class MiembroJurado extends Persona{
    private String perfil;
    private ArrayList<Evaluacion> evaluaciones;

    public MiembroJurado(String nombres, String apellidos, String telefono, String email, String perfil) {
        super(nombres, apellidos, telefono, email);
        this.perfil = perfil;
        evaluaciones = new ArrayList<>();
    }

    public MiembroJurado(int id, String nombres, String apellidos, String telefono, String email, String perfil) {
        super(id, nombres, apellidos, telefono, email);
        this.perfil = perfil;
        evaluaciones = new ArrayList<>();
    }
    
    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + perfil;
    }
    
    public void saveFile(String nomFile){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomFile,true))){
            bw.write(this.toString());
            bw.newLine();
        } catch (IOException ioe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error con el archivo. "+ ioe.getMessage());
        }
    }
    
    public static ArrayList<MiembroJurado> readFile(String nomFile){
        ArrayList<MiembroJurado> listaMiembroJurados = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(nomFile))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] tokens= linea.split("\\|");
                MiembroJurado mj = new MiembroJurado(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                listaMiembroJurados.add(mj);
            }
        } catch (FileNotFoundException fnfe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"No se ha encontrado el archivo. "+ fnfe.getMessage());
        } catch (IOException ioe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error con el archivo. "+ ioe.getMessage());
        }
        return listaMiembroJurados;
    }
    public static MiembroJurado nextMiembroJurado(TextField tfNombres, TextField tfApellidos, TextField tfCelular, TextField tfEmail, TextField tfPerfil){
        MiembroJurado miembroJurado = new MiembroJurado(tfNombres.getText(),tfApellidos.getText(),tfCelular.getText(),tfEmail.getText(),tfPerfil.getText());
        return miembroJurado;
    }
    
    public static MiembroJurado buscarMiembroJurado(ArrayList<MiembroJurado> miembroJurados, String emailMiembroJurado){
        for(MiembroJurado mj:miembroJurados){
            if(mj.getEmail().equals(emailMiembroJurado))
                return mj;
        }
        return null;
    }
}
