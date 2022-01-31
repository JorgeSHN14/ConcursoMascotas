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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author LENOVO
 */
public class Mascota{
    private int id;
    private int idDuenio;
    private String nombre;
    private String raza;
    private Date fechaNac;
    private String tipo;
    private ArrayList<Inscripcion> inscripciones;
    private static int idInstancia = 0;

    public Mascota(int idDuenio, String nombre, String tipo, String raza, Date fechaNac) {
        this.id = ++idInstancia;
        this.idDuenio = idDuenio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaNac = fechaNac;
        this.inscripciones = new ArrayList<>();
    }
    
    public Mascota(int id, int idDuenio, String nombre, String tipo, String raza, Date fechaNac) {
        this.id = id;
        this.idDuenio=idDuenio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaNac = fechaNac;
        this.inscripciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date FechaNac) {
        this.fechaNac = FechaNac;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public int getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(int idDuenio) {
        this.idDuenio = idDuenio;
    }

    @Override
    public String toString() {
        return id + "|" + idDuenio + "|" + nombre + "|" + tipo + "|" +raza + "|" + fechaNac;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Mascota other = (Mascota) obj;
        return this.id != other.id;
    }
    
    public void saveFile(String nomFile){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomFile,true))){
            bw.write(this.toString());
            bw.newLine();
        } catch (IOException ioe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error con el archivo. "+ ioe.getMessage());
        }
    }
    
    public static Date strToDate(String[] fechaStr){
        int year = Integer.parseInt(fechaStr[5]);
        int day = Integer.parseInt(fechaStr[2]);
        int month;
        switch (fechaStr[1]){
            case "Jan":
                month = 0;break;
            case "Feb":
                month = 1;break;
            case "Mar":
                month = 2;break;
            case "Apr":
                month = 3;break;
            case "May":
                month = 4;break;
            case "Jun":
                month = 5;break;
            case "Jul":
                month = 6;break;
            case "Aug":
                month = 7;break;
            case "Sep":
                month = 8; break;
            case "Oct":
                month = 9;break;
            case "Nov":
                month = 10;break;
            default:
                //December va como default para que al crear la fecha no genere el error de que el mes puede llegar a no definirse
                month = 11;break;
                }
            Date fecha = new Date(year-1900,month,day);
            return fecha;      
    }
    
    public static ArrayList<Mascota> readFile(String nomFile){
        ArrayList<Mascota> listaMascotas = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(nomFile))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] tokens= linea.split("\\|");
                String[] fechaNacStr = tokens[5].split(" ");
                Date fechaNac = strToDate(fechaNacStr);
                Mascota m = new Mascota(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],tokens[4],fechaNac);
                listaMascotas.add(m);
            }
        } catch(FileNotFoundException fnfe){
            Alert al = new Alert(Alert.AlertType.ERROR,"No se pudo encontrar el archivo.");
        }    
        catch(IOException ioe){
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error");
        }
        return listaMascotas;
    }
    
    public static Mascota buscarMascota(ArrayList<Mascota> mascotas, String nomMascota){
        for(Mascota m:mascotas){
            if(m.getNombre().equals(nomMascota))
                return m;
        }
        return null;
    }
    
    public static ArrayList<String> nomMascotas(String nomFile){
        ArrayList<Mascota> mascotas = readFile(nomFile);
        ArrayList<String> nombresMascotas = new ArrayList<String>();
        for(Mascota m:mascotas){
            nombresMascotas.add(m.nombre);
        }
        return nombresMascotas;
    }
    
    public static Mascota nextMascota(int idDuenio, TextField tfNombre, TextField tfTipo, TextField tfRaza, Date fechaNac){
        Mascota m = null;
        String nombre = tfNombre.getText();
        String tipo = tfTipo.getText();
        String raza = tfRaza.getText();
        Duenio duenioMascota;
        String emailDuenio;
        m = new Mascota(idDuenio ,nombre,tipo,raza,fechaNac);
        
        return m;
    }
}
