/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cocineros;
import Vista.frm_Cocineros;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiante
 */
public class ctrlCocineros implements MouseListener{
    //1-Llamar a las otras capas (Modelo, Vista)
    private frm_Cocineros vista;
    private Modelo.Cocineros modelo;
    
    
     //2-Crear el constructor de la clase

    public ctrlCocineros(frm_Cocineros Vista, Modelo.Cocineros Modelo ) {
        this.vista = Vista;
        this.modelo = Modelo;
 
        vista.jtbCocineros.addMouseListener(this);
        vista.btnAgregar.addMouseListener(this);
        
        //Para mostrar los datos
        modelo.Mostrar(vista.jtbCocineros);
        
       vista.btnEliminar.addMouseListener(this);
       vista.btnActualizar.addMouseListener(this);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    if (e.getSource() == vista.btnAgregar){
        
        //Para validar campos vacios
        boolean validacionesCorrectasC = true;
       if(vista.txtNombreCocinero.getText().isEmpty()||vista.txtEdadCocinero.getText().isEmpty()||vista.txtPesoCocinero.getText().isEmpty()||vista.txtCorreoCocinero.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Llene los campos");
                validacionesCorrectasC = false;
            }
        else {
           try {
                double peso = Double.parseDouble(vista.txtPesoCocinero.getText());
               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese solo numeros en el peso");
                validacionesCorrectasC = false;
            }
           
           
            try {
                int edadNumerica = Integer.parseInt(vista.txtEdadCocinero.getText());
                if(edadNumerica > 100 || edadNumerica < 18){
                    JOptionPane.showMessageDialog(vista, "Ingrese una edad valida");
                    validacionesCorrectasC = false;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese solo numeros en la edad");
                validacionesCorrectasC = false;
            }
           
            if(!vista.txtCorreoCocinero.getText().contains("@") || !vista.txtCorreoCocinero.getText().contains(".com")){
                JOptionPane.showMessageDialog(vista, "Ingrese un correo valido como este correoelctronico.com");
                validacionesCorrectasC = false;
            }

       }
   
       if(validacionesCorrectasC){
       modelo.setNombre_Cocinero(vista.txtNombreCocinero.getText());
       modelo.setEdad_Cocinero(Integer.parseInt( vista.txtEdadCocinero.getText()));
       modelo.setPeso_Cocinero(Double.parseDouble(vista.txtPesoCocinero.getText()));
       modelo.setCorreo_Cocinero(vista.txtCorreoCocinero.getText());
       modelo.Guardar();
       modelo.cargarDatosTabla(vista);
       modelo.Mostrar(vista.jtbCocineros);
   }

            
   
   
   modelo.Mostrar(vista.jtbCocineros);
   
   vista.txtNombreCocinero.setText("");
   vista.txtEdadCocinero.setText("");
   vista.txtPesoCocinero.setText("");
   vista.txtCorreoCocinero.setText("");
       }
   
   if(e.getSource()== vista.btnEliminar){
       modelo.Eliminar(vista.jtbCocineros);
       modelo.Mostrar(vista.jtbCocineros);
       
   }
   
   if (e.getSource()== vista.jtbCocineros){
     modelo.cargarDatosTabla(vista);
   }
   
   if (e.getSource()== vista.btnActualizar){ boolean validacionesCorrectasC = true;
       if(vista.txtNombreCocinero.getText().isEmpty()||vista.txtEdadCocinero.getText().isEmpty()||vista.txtPesoCocinero.getText().isEmpty()||vista.txtCorreoCocinero.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Llene los campos");
                validacionesCorrectasC = false;
            }
        else {
           try {
                double peso = Double.parseDouble(vista.txtPesoCocinero.getText());
               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese solo numeros en el peso");
                validacionesCorrectasC = false;
            }
           
           
            try {
                int edadNumerica = Integer.parseInt(vista.txtEdadCocinero.getText());
                if(edadNumerica > 100 || edadNumerica < 18){
                    JOptionPane.showMessageDialog(vista, "Ingrese una edad valida");
                    validacionesCorrectasC = false;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese solo numeros en la edad");
                validacionesCorrectasC = false;
            }
           
            if(!vista.txtCorreoCocinero.getText().contains("@") || !vista.txtCorreoCocinero.getText().contains(".com")){
                JOptionPane.showMessageDialog(vista, "Ingrese un correo valido como este correoelctronico.com");
                validacionesCorrectasC = false;
            }

       }
   
       if(validacionesCorrectasC){
       modelo.setNombre_Cocinero(vista.txtNombreCocinero.getText());
       modelo.setEdad_Cocinero(Integer.parseInt( vista.txtEdadCocinero.getText()));
       modelo.setPeso_Cocinero(Double.parseDouble(vista.txtPesoCocinero.getText()));
       modelo.setCorreo_Cocinero(vista.txtCorreoCocinero.getText());
       modelo.Guardar();
       modelo.cargarDatosTabla(vista);
       modelo.Mostrar(vista.jtbCocineros);
   }
  
            
   }
   
   
   
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
 

}
