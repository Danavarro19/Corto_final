/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    public JTable resutlados;
    
    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar; 
    
    private static final int ANCHOC =130, ALTOC =30;    
    DefaultTableModel tm;

    public Consulta() {
        super("Peliculas");
    }
    

}
 











    

