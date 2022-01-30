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

/**
 *
 * @author LENOVO
 */
public class Criterio {
    private int id;
    private String descipcion;
    private int idConcurso;
    private ArrayList<Evaluacion> evaluaciones;
    private static int idInstancia = 0;

    public Criterio(String descipcion, int idConcurso) {
        this.id = ++idInstancia;
        this.descipcion = descipcion;
        this.idConcurso = idConcurso;
        this.evaluaciones = new ArrayList<>(); 
    }
    
    public Criterio(int id, String descipcion, int idConcurso) {
        this.id = id;
        this.descipcion = descipcion;
        this.idConcurso = idConcurso;
        this.evaluaciones = new ArrayList<>(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }
    
    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    @Override
    public String toString() {
        return id + "|" + descipcion + "|" + idConcurso;
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
        Criterio other = (Criterio) obj;
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
    
    public static ArrayList<Criterio> readFile(String nomFile){
        ArrayList<Criterio> listaCriterios = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(nomFile))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] tokens= linea.split("\\|");
                Criterio c = new Criterio(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[3]));
                listaCriterios.add(c);
            }
        } catch (FileNotFoundException fnfe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"No se ha encontrado el archivo. "+ fnfe.getMessage());
        } catch (IOException ioe) {
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error con el archivo. "+ ioe.getMessage());
        }
        return listaCriterios;
    }
    
    public static ArrayList<Criterio> nextCriterios(Scanner sc,  int numCriterios){
        ArrayList<Concurso> concursos = Concurso.readFile("concursos.txt");
        if(concursos.isEmpty())
            return null;
        ArrayList<Criterio> criterios = new ArrayList<>();
        String[] descripciones = new String[numCriterios];
        Concurso concursoPremio;
        String nombreConcurso;
        System.out.println("Ingrese los datos cada criterio.");
        for(int i = 0 ; i<numCriterios; i++){
            System.out.println("Criterio " + (i+1) +" de " + numCriterios);
            System.out.println("Ingrese la descripción del criterio:");
            descripciones[i] = sc.next();
        }
        System.out.println("Ingrese el nombre del concurso al que pertenece el grupo de citerios.");
        do{
            System.out.println("Ingrese el nombre de un concurso resgistrado:");
            nombreConcurso = sc.next();
            concursoPremio = Concurso.buscarConcurso(concursos, nombreConcurso);
            if(concursoPremio != null){
                Criterio c;
                for(int i = 0 ; i<numCriterios; i++){
                    c = new Criterio(descripciones[i],concursoPremio.getId());
                    criterios.add(c);
                }
            }
        } while(concursoPremio == null);
        return criterios;
    }
    
}
