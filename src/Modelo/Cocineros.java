/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Cocineros {

      String UUID_Cocinero;
    String Nombre_Cocinero; 
    int Edad_Cocinero;
    double Peso_Cocinero;
    
    String Correo_Cocinero; 
    
    public String getUUID_Cocinero() {
        return UUID_Cocinero;
    }

    public void setUUID_Cocinero(String UUID_Cocinero) {
        this.UUID_Cocinero = UUID_Cocinero;
    }

    public String getNombre_Cocinero() {
        return Nombre_Cocinero;
    }

    public void setNombre_Cocinero(String Nombre_Cocinero) {
        this.Nombre_Cocinero = Nombre_Cocinero;
    }

    public double getPeso_Cocinero() {
        return Peso_Cocinero;
    }

    public void setPeso_Cocinero(double Peso_Cocinero) {
        this.Peso_Cocinero = Peso_Cocinero;
    }

    public int getEdad_Cocinero() {
        return Edad_Cocinero;
    }

    public void setEdad_Cocinero(int Edad_Cocinero) {
        this.Edad_Cocinero = Edad_Cocinero;
    }

    public String getCorreo_Cocinero() {
        return Correo_Cocinero;
    }

    //1-Parametros
    public void setCorreo_Cocinero(String Correo_Cocinero) {
        this.Correo_Cocinero = Correo_Cocinero;
    }
    
    
      public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addCocinero = conexion.prepareStatement("INSERT INTO tbCocinero (UUID_Cocinero, Nombre_Cocinero, Edad_Cocinero, Peso_Cocinero, Correo_Cocinero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addCocinero.setString(1, UUID.randomUUID().toString());
            addCocinero.setString(2, getNombre_Cocinero());
            addCocinero.setInt(3, getEdad_Cocinero());
            addCocinero.setDouble(4, getPeso_Cocinero());
            addCocinero.setString(5, getCorreo_Cocinero());
 
            
            
            //Ejecutar la consulta
            addCocinero.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
      public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelococineros = new DefaultTableModel();
        modelococineros.setColumnIdentifiers(new Object[]{"UUID_Cocinero", "Nombre_Cocinero",  "Edad_Cocinero", "Peso_Cocinero", "Correo_Cocinero"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbCocinero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelococineros.addRow(new Object[]{rs.getString("UUID_Cocinero"), 
                    rs.getString("Nombre_Cocinero"),
                     rs.getInt("Edad_Cocinero"),
                    rs.getDouble("Peso_Cocinero"), 
                    rs.getString("Correo_Cocinero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelococineros);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
       public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbCocinero where UUID_Cocinero = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
       
       public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbCocinero set Nombre_Cocinero= ?, Edad_Cocinero = ?,  Peso_Cocinero = ?, Correo_Cocinero = ? where UUID_Cocinero = ?");
                updateUser.setString(1, getNombre_Cocinero());
                updateUser.setInt(2, getEdad_Cocinero());
                updateUser.setDouble(3, getPeso_Cocinero());
                updateUser.setString(4, getCorreo_Cocinero());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no funciona actualizar");
        }
    }
       public void cargarDatosTabla(Vista.frm_Cocineros vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbCocineros.getSelectedRow();
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbCocineros.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbCocineros.getValueAt(filaSeleccionada, 1).toString();
            String EdadTB = vista.jtbCocineros.getValueAt(filaSeleccionada, 2).toString();
            String PesoTB = vista.jtbCocineros.getValueAt(filaSeleccionada, 3).toString();
            String CorreoTB = vista.jtbCocineros.getValueAt(filaSeleccionada, 4).toString();
            // Establece los valores en los campos de texto
            vista.txtNombreCocinero.setText(NombreDeTB);
            vista.txtEdadCocinero.setText(EdadTB);
            vista.txtPesoCocinero.setText(PesoTB);
            vista.txtCorreoCocinero.setText(CorreoTB);
        }
    }
   
    
    
}
