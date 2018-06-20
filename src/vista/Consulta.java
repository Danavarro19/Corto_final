/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.MovieDao;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Movie;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    
    public JLabel lblNombre, lblDirector, lblPais, lblClasificacion, 
            lblAnio, lblEnexhib;
    public JTextField nombre, director, pais, annio;
    ButtonGroup enProyeccion= new ButtonGroup();
    public JComboBox clasficacion;
    public JRadioButton si, no;
    public JTable resultados;
    
    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar; 
    
    private static final int ANCHOC =130, ALTOC =30;    
    DefaultTableModel tm;

    public Consulta() {
        super("Peliculas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container =getContentPane();
        container.add(lblNombre);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblClasificacion);
        container.add(lblAnio);
        container.add(lblEnexhib);
        container.add(nombre);
        container.add(director);
        container.add(pais);
        container.add(annio);
        container.add(clasficacion);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600,600);
        eventos();
    
    }
    
    public final void agregarLabels(){
        lblNombre=new JLabel("Nombre");
        lblDirector=new JLabel("Director");
        lblPais=new JLabel("Pais");
        lblClasificacion=new JLabel("Clasificacion");
        lblAnio=new JLabel("Año");
        lblEnexhib=new JLabel("En exhibicion");
        lblNombre.setBounds(10,10,ANCHOC,ALTOC);
        lblDirector.setBounds(10,60,ANCHOC,ALTOC);
        lblPais.setBounds(10,100,ANCHOC,ALTOC);
        lblClasificacion.setBounds(10,140,ANCHOC,ALTOC);
        lblAnio.setBounds(10,180,ANCHOC,ALTOC);
    }
    
    public final void formulario(){
    
        nombre=new JTextField();
        director=new JTextField();
        pais=new JTextField();
        annio=new JTextField();

        clasficacion=new JComboBox();
        si=new JRadioButton("si",true);
        no=new JRadioButton("no");
        resultados=new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table= new JPanel();
        
        clasficacion.addItem("G");
        clasficacion.addItem("PG");
        clasficacion.addItem("14A");
        clasficacion.addItem("18A");
        clasficacion.addItem("R");
        clasficacion.addItem("A");
        
        enProyeccion= new ButtonGroup();
        enProyeccion.add(si);
        enProyeccion.add(no);
        
        nombre.setBounds(140,10,ANCHOC,ALTOC);
        director.setBounds(140,60,ANCHOC,ALTOC);
        pais.setBounds(140,100,ANCHOC,ALTOC);
        annio.setBounds(140,140,ANCHOC,ALTOC);
        si.setBounds(140,180,50,ALTOC);
        no.setBounds(210,180,50,ALTOC);
        
        buscar.setBounds(300,10,ANCHOC,ALTOC);
        insertar.setBounds(10,210,ANCHOC,ALTOC);
        actualizar.setBounds(150,210,ANCHOC,ALTOC);
        eliminar.setBounds(300,210,ANCHOC,ALTOC);
        limpiar.setBounds(450,10,ANCHOC,ALTOC);
        resultados= new JTable();
        table.setBounds(10,250,500,200);
        table.add(new JScrollPane(resultados));
        
    }
    
    public void llenarTabla(){
        tm= new DefaultTableModel(){
            public Class<?> getColumnsClass(int column){
                switch(column){
                    case 0:
                    case 1:
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;              
                }
            }
        };
        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificacion");
        tm.addColumn("Año");
        tm.addColumn("En exhibicion");
        
        MovieDao md = new MovieDao();
        ArrayList<Movie> movies=md.readAll();
        
        for (Movie m: movies){
            tm.addRow(new Object[] {m.getNombre(),m.getDirector(),m.getPaisProcedencia(),
            m.getClasificacion(),m.getAnnioEstreno(),m.EnExhibicion()});
        }
        resultados.setModel(tm);
    }
    
    public void eventos(){
    
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao md= new MovieDao();
                Movie m= new Movie(nombre.getText(),director.getText(),pais.getText(),
                clasficacion.getSelectedItem().toString(),Integer.parseInt(annio.getText()),
                1);
                
                if (no.isSelected())
                    m.setEnExhibicion(0);
                
                if (md.create(m)){
                    JOptionPane.showMessageDialog(null, "¨Pelicula registrada con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear la pelicula");
                }
                
            }
        });
        
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao md= new MovieDao();
                Movie m= new Movie(nombre.getText(),director.getText(),pais.getText(),
                clasficacion.getSelectedItem().toString(),Integer.parseInt(annio.getText()),
                1);
                if (no.isSelected())
                    m.setEnExhibicion(0);
                
                if(md.update(m)){
                
                    JOptionPane.showMessageDialog(null, "Pelicula modifcada con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de actualizar la pelicula");
                }
            
            }
            
            
        });
        
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao md = new MovieDao();
                if(md.delete(nombre.getText())){
                
                    JOptionPane.showMessageDialog(null, "Pelicula Eliminada con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al eliminar la pelicula");
                    
                }
            }
        });
        
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao md= new MovieDao();
                Movie m=md.read(nombre.getText());
                 if(m==null){
                     JOptionPane.showMessageDialog(null, "La pelicula no se ha encontrado");
                 }else{
                     nombre.setText(m.getNombre());
                     director.setText(m.getDirector());
                     pais.setText(m.getPaisProcedencia());
                     annio.setText(Integer.toString(m.getAnnioEstreno()));
                     clasficacion.setSelectedItem(m.getClasificacion());
                     
                     if(m.EnExhibicion()==1)
                         si.setSelected(true);
                     else
                         no.setSelected(true);
                 
                 }
            }
        });
        
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    
    }
    
    public void limpiarCampos(){
    
        nombre.setText("");
        director.setText("");
        clasficacion.setSelectedItem("PG");
        pais.setText("");
        annio.setText("");
        
    }
    
    
    

}
 











    

