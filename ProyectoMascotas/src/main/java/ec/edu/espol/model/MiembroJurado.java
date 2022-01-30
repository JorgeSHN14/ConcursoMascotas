/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;

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
    
    public static MiembroJurado nextMiembroJurado(Scanner sc){
        MiembroJurado miembroJurado;
        System.out.println("Ingrese los nombres del miembro del jurado:");
        String nombres = sc.next();
        System.out.println("Ingrese los apellidos del miembro del jurado:");
        String apellidos = sc.next();
        System.out.println("Ingrese el telefono del miembro del jurado:");
        String telefono = sc.next();
        System.out.println("Ingrese los email del miembro del jurado:");
        String email = sc.next();
        System.out.println("Ingrese el perfil del miembro del jurado:");
        String perfil = sc.next();
        miembroJurado = new MiembroJurado(nombres, apellidos, telefono, email, perfil);
        return miembroJurado;
    }
    
}
