/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Movie {
    
    private int idMovie; 
    private String nombre;
    private String director;
    private String paisProcedencia; 
    private String clasificacion;
    private int annioEstreno;
    private int enExhibicion; 

    public Movie(String nombre, String director, String paisProcedencia, String clasificacion, int annioEstreno, int enExhibicion) {
        this.nombre = nombre;
        this.director = director;
        this.paisProcedencia = paisProcedencia;
        this.clasificacion = clasificacion;
        this.annioEstreno = annioEstreno;
        this.enExhibicion = enExhibicion;
    }
    
    public int getIdMovie() {return idMovie;}

    public String getNombre() {return nombre;}

    public String getDirector() {return director;}

    public String getPaisProcedencia() {return paisProcedencia;}
    
    public String getClasificacion() {return clasificacion;}

    public int getAnnioEstreno() {return annioEstreno;}
    
    public int EnExhibicion() {return enExhibicion;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setClasificacion(String clasificacion) {this.clasificacion = clasificacion;}

    public void setEnExhibicion(int enExhibicion) {this.enExhibicion = enExhibicion;}
    
    

    
  

    
    
   
    
    
    
    
           
    
    
    
}
