/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author LENOVO
 */
public class Evaluacion {
    private int id;
    private int idInscripcion;
    private int idMiembroJurado;
    private double nota;
    private int idCriterio;
    private static int idInstancia = 0;

    public Evaluacion(int id, int idInscripcion, int idMiembroJurado, double nota, int idCriterio) {
        this.id = ++idInstancia;
        this.idInscripcion = idInscripcion;
        this.idMiembroJurado = idMiembroJurado;
        this.nota = nota;
        this.idCriterio = idCriterio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdMiembroJurado() {
        return idMiembroJurado;
    }

    public void setIdMiembroJurado(int idMiembroJurado) {
        this.idMiembroJurado = idMiembroJurado;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }
    
    @Override
    
    public String toString() {
        return id +"|"+ idInscripcion + "|" + idMiembroJurado + "|" + nota + "|" + idCriterio;
    }

    public void saveFile(String nomfile){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomfile,true)))
        {
            bw.write(this.toString());
            bw.newLine();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Evaluacion> readFile(String nomfile){
        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(nomfile))){
            int id;
            int idInscripcion;
            int idMiembroJurado;
            double nota;
            int idCriterio;
            evaluaciones = new ArrayList<>();
            Evaluacion e;
            String[] evaluacionStrArr;
            String evaluacionStr;
            while((evaluacionStr = br.readLine()) != null){
                evaluacionStrArr = evaluacionStr.split("\\|");
                id = Integer.parseInt(evaluacionStrArr[0]);
                idInscripcion = Integer.parseInt(evaluacionStrArr[1]);
                idMiembroJurado = Integer.parseInt(evaluacionStrArr[2]);
                nota = Double.parseDouble(evaluacionStrArr[3]);
                idCriterio = Integer.parseInt(evaluacionStrArr[4]);
                e = new Evaluacion(id, idInscripcion,idMiembroJurado,nota,idCriterio);
                evaluaciones.add(e);
            }
        } catch(FileNotFoundException fnfe){
            Alert al = new Alert(AlertType.ERROR,"No se pudo encontrar el archivo.");
        }    
        catch(IOException ioe){
            Alert al = new Alert(AlertType.ERROR,"Ha ocurrido un error");
        }
        
        return evaluaciones;
    }
    
    public static Evaluacion nextEvaluacion (Scanner sc,int idmj){
        sc.useDelimiter("\n");
        //int id, int idinscripcion, int idMiembroJurado, int idCriterio, double nota
        System.out.println("Su id es:");
        int id = Criterio.readFile("evaluaciones.txt").size() + 1;     
        System.out.println(id);
        System.out.println("Ingrese ID de la Inscripción: ");
        int insc = sc.nextInt();
        System.out.println("Ingrese el ID del Criterio a evaluar: ");
        int crit = sc.nextInt();
        System.out.println("Ingrese la Nota: ");
        double nota = sc.nextDouble();
        Evaluacion evalu = new Evaluacion(id,insc,idmj,nota,crit);
        return evalu;  
           
    }
}

