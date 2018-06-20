/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Movie;

/**
 *
 * @author LN710Q
 */
public class movieDao implements Metodos<Movie>{
    
     private static final String SQL_INSERT="INSERT INTO movie (nombre,director,pais,clasificacion,anio,en_proyeccion)VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE movie SET en_proyeccion=? WHERE nombre=?";
    private static final String SQL_DELETE="DELETE FROM movies WHERE nombre=?";
    private static final String SQL_READ="SELECT * FROM movies WHERE nombre=?";
    private static final String SQL_READALL="SELECT * FROM movies";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Movie g) {
        PreparedStatement ps;
        try{
            ps =con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2,g.getDirector());
            ps.setString(3,g.getPaisProcedencia());
            ps.setString(4,g.getClasificacion());
            ps.setInt(5,g.getAnnioEstreno());
            ps.setInt(6,1);
            if(ps.executeUpdate()>0)
                return true;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        
        }finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            
            if(ps.executeUpdate()>0)
                return true;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            con.cerrarConexion();
        }
        return false; 
    }

    @Override
    public boolean update(Movie c) {
        PreparedStatement ps;
        
        try{
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            if (ps.executeUpdate()>0)
                return true;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            con.cerrarConexion();
        }
        return false;

    }

    @Override
    public Movie read(Object key) {
        Movie m =null;
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps= con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs= ps.executeQuery();
            
            while(rs.next()){
                m=new Movie(rs.getString(1),rs.getString(2),
                rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6));
            }
            rs.close();
        
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        
        }finally{
            con.cerrarConexion();
        }    
        return m;
    
    }

    @Override
    public ArrayList<Movie> readAll() {
        ArrayList<Movie> all= new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s=con.getCnx().prepareStatement(SQL_READALL);
            rs=s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Movie(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6)));
            }
            rs.close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            con.cerrarConexion();
        }
        
        return all;
    }
    
    
    
    
}
