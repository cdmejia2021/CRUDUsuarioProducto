/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

//Librerias y clases importadas
import java.sql.Connection;       //Libreria de tipo sql para establecer la conexión con la BD
import java.sql.DriverManager;    //Libreria para llamar al Driver
import java.sql.SQLException;     //Libreria para el manejo de excepciones SQL
import java.util.logging.Level;   //Libreria usada para el manejo de exepciones, la cual utiliza archivos
import java.util.logging.Logger;  //Libreria usada para el manejo de excepciones, la cual utiliza archivos


/**
 *
 * @author Usuario
 */
public class ConexionMsql {
    
    //Variables declaradas
    public static ConexionMsql instance;
    private Connection cnn;
    
    //Estableciendo conexión con la BD
    private ConexionMsql(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_tallerjavaweb", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(ConexionMsql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionMsql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Verificando que solo exista una instancia de la clase (Patrón Singleton)
    public static synchronized ConexionMsql getInstance(){
        if(instance==null){
            instance = new ConexionMsql();
        }
        return instance;
    }
    
    //Get para obtener la conexión
    public Connection getCnn(){
        return cnn;
    }
    
    //Método para cerrar la conexión con la BD
    public void cerrarConexion(){
        instance=null;
    }
}//fin clase
