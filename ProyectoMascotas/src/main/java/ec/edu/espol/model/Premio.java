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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author LENOVO
 */
public class Premio {
    private int id;
    private int lugar;
    private String descripcion;
    private int idConcurso;
    private static int idInstancia = 0;

    public Premio(int lugar, String descripcion, int idConcurso) {
        this.id = ++idInstancia;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.idConcurso = idConcurso;
    }
    
    public Premio(int id, int lugar, String descripcion, int idConcurso) {
        this.id = id;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.idConcurso = idConcurso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public int getIdInstancia() {
        return idInstancia;
    }

    public void setIdInstancia(int idInstancia) {
        this.idInstancia = idInstancia;
    }

    @Override
    public String toString() {
        return id + "|" + lugar + "|" + descripcion + "|" + idConcurso;
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
        final Premio other = (Premio) obj;
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
    
    public static Premio buscarPremio(ArrayList<Premio> premios, int lugar, int idConcurso){
        for(Premio p: premios){
            if(p.getLugar()== lugar && p.getIdConcurso()==idConcurso)
                return p;
        }
        return null;
    }
    
    public static ArrayList<Premio> readFile(String nomFile){
        ArrayList<Premio> listaPremios = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(nomFile))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] tokens= linea.split("\\|");
                Premio p = new Premio(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],Integer.parseInt(tokens[3]));
                listaPremios.add(p);
            }
        } catch(FileNotFoundException fnfe){
            Alert al = new Alert(Alert.AlertType.ERROR,"No se pudo encontrar el archivo.");
        }    
        catch(IOException ioe){
            Alert al = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error");
        }
        return listaPremios;
    }
    
    public static Premio nextPremio(int lugar, TextField descripcion,int idConcurso){
        Premio p = new Premio(lugar,descripcion.getText(),idConcurso);
        
        return p;
    }
    
}
