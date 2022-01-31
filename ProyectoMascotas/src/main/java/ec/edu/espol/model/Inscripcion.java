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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Alert;

/**
 *
 * @author LENOVO
 */
public class Inscripcion {
    private int id;
    private int idMascota;
    private int idConcurso;
    private double valor;
    private double descuento;
    private Date fechaInscripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private static int idInstancia = 0;

    public Inscripcion(int idMascota, int idConcurso, double valor) {
        this.id = ++idInstancia;
        this.idMascota = idMascota;
        this.idConcurso = idConcurso;
        this.valor = valor;
        BigDecimal bd = new BigDecimal(Math.random() * 11).setScale(2, RoundingMode.HALF_UP);
        this.descuento = bd.doubleValue();
        LocalDateTime ldt= LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date fechaInscripcion = Date.from(zdt.toInstant());
        
        this.fechaInscripcion = fechaInscripcion;
        this.evaluaciones = new ArrayList<>();
    }
    
    public Inscripcion(int id,int idMascota, int idConcurso, double valor, double descuento, Date fechaInscripcion) {
        this.id = ++idInstancia;
        this.idMascota = idMascota;
        this.idConcurso = idConcurso;
        this.valor = valor;
        this.descuento = descuento;
        this.fechaInscripcion = fechaInscripcion;
        this.evaluaciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public int getIdInstancia() {
        return idInstancia;
    }

    public void setIdInstancia(int idInstancia) {
        this.idInstancia = idInstancia;
    }

    @Override
    public String toString() {
        return id + "|" + idMascota + "|" + idConcurso + "|" + valor + "|" + descuento + "|" + fechaInscripcion;
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
        Inscripcion other = (Inscripcion) obj;
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
    
    public static ArrayList<Inscripcion> readFile(String nomFile){
        ArrayList<Inscripcion> listaInscripciones = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(nomFile))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] tokens= linea.split("\\|");
                Date fechaInsc = Concurso.strToDate(tokens[5].split(" "));
                Inscripcion i = new Inscripcion(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),Double.parseDouble(tokens[3]),Double.parseDouble(tokens[4]),fechaInsc);
                listaInscripciones.add(i);
            }
        } catch(FileNotFoundException fnfe){
            Alert al = new Alert(Alert.AlertType.ERROR,"No se pudo encontrar el archivo.");
        }    
        catch(IOException ioe){
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error");
        }
        return listaInscripciones;
    }
    
    public static ArrayList<Integer> idInscripciones(String nomFile){
        ArrayList<Inscripcion> inscripciones = readFile(nomFile);
        ArrayList<Integer> idIncripciones = new ArrayList<>();
        for(Inscripcion i:inscripciones){
            idIncripciones.add(i.id);
        }
        return idIncripciones;
    }
    
    public static Inscripcion buscarInscripcion(ArrayList<Inscripcion> inscripciones,Mascota m, Concurso c){
        for(Inscripcion i:inscripciones){
            if(m.getNombre().equals(m.getNombre()) && c.getNombre().equals(c.getNombre()))
                return i;
        }
        return null;
    }
    
    public static Inscripcion nextInscripcion(Mascota m, Concurso c){
        
        Inscripcion i = new Inscripcion(m.getId(),c.getId(),c.getCosto());
        return i;
    }
    
}
