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
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author LENOVO
 */
public class Duenio extends Persona{
    
    private String direccion;
    private ArrayList<Mascota> mascotas;

    public Duenio(String nombres, String apellidos, String telefono, String email,String direccion) {
        super(nombres, apellidos, telefono, email);
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
    }
    
    public Duenio(int id, String nombres, String apellidos, String telefono, String email,String direccion) {
        super(id, nombres, apellidos, telefono, email);
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + direccion;
    }


    public void saveFile(String nomFile){
        try(BufferedWriter bw= new BufferedWriter(new FileWriter(nomFile,true))){
            bw.write(this.toString());
        } catch (IOException ioe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error con el archivo. "+ ioe.getMessage());
        }
    }
    
    
    public static ArrayList<Duenio> readFile(String nomFile){
        ArrayList<Duenio> listaDuenios = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(nomFile))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] tokens= linea.split("\\|");
                Duenio d = new Duenio(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                listaDuenios.add(d);
            }
        } catch (FileNotFoundException fnfe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"No se ha encontrado el archivo. "+ fnfe.getMessage());
        } catch (IOException ioe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error con el archivo. "+ ioe.getMessage());
        }
        return listaDuenios;
    }
    
    public static Duenio buscarDuenio(ArrayList<Duenio> duenios, String emailDuenio){
        for(Duenio d:duenios){
            if(d.getEmail().equals(emailDuenio))
                return d;
        }
        return null;
    }
 
    public static Duenio nextDuenio(Scanner sc ){
        System.out.println("Ingrese los nombres del Dueño:");
        String nombres = sc.next();
        System.out.println(nombres);
        System.out.println("Ingrese los apellidos del Dueño:");
        String apellidos= sc.next();
        System.out.println(apellidos);
        System.out.println("Ingrese el telefono del Dueño:");
        String telefono=sc.next();
        System.out.println("Ingrese en e-mail del Dueño:");
        String email=sc.next();
        System.out.println("Ingrese la direccion del Dueño:");
        String direccion=sc.next();
        Duenio d = new Duenio(nombres,apellidos,telefono,email,direccion);  
        return d;
    }
    
}

