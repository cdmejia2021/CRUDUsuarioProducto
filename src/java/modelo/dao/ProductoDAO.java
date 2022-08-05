/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

//Librerias y Clases Importadas
import conexiones.ConexionMsql;         //Clase importada para la conexión con la BD
import java.sql.PreparedStatement;      //Libreria importada para preparar el estamento (sentencias SQL)
import java.sql.ResultSet;              //Libreria utilizada para guardar los datos obtenidos de los registros de las tabla de la BD
import java.sql.SQLException;           //Libreria utilizada para el manejo de excepciones SQL
import java.util.ArrayList;             //Libreria utilizada para el manejo de ArrayList
import java.util.List;                  //Libreria utilizada para el manejo de listas
import java.util.logging.Level;         //Libreria utilizada para el manejo de excepciones mediante archivos
import java.util.logging.Logger;        //Libreria utilizada para el manejo de excepciones mediante archivos    
import modelo.dto.ProductoDTO;          //Libreria utilizada para el manejo de clases de tipo ProductoDTO

/**
 *
 * @author Usuario
 */
public class ProductoDAO {

    //Querys equivalentes a las sentencias SQL
    private static final String SQL_INSERT = "INSERT INTO tb_producto"
            + "(nombre_producto, descripcion_producto, unidades, valor) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM tb_producto WHERE id_producto = ?";
    private static final String SQL_UPDATE = "UPDATE tb_producto SET nombre_producto = ?, descripcion_producto = ?, unidades = ?, valor = ? WHERE id_producto = ?";
    private static final String SQL_READ = "SELECT * from tb_producto WHERE id_producto = ?";
    private static final String SQL_READALL = "SELECT * from tb_producto";

    //Variable de la conexión a la BD
    private static final ConexionMsql con = ConexionMsql.getInstance();

    //método para crear
    public boolean create(ProductoDTO c) {
        try {
            PreparedStatement ps;                               //Preparamos el estamento
            ps = con.getCnn().prepareStatement(SQL_INSERT);     //Hacemos que ese estamento sea igual a un query definido arriba teniendo en cuenta la conexión
            //Ingresamos los datos obtenidos
            ps.setString(1, c.getNombre_pro());
            ps.setString(2, c.getDescripcion_pro());
            ps.setInt(3, c.getUnidades_pro());
            ps.setInt(4, c.getValor_pro());
            if (ps.executeUpdate() > 0) {   //Verificamos si la sentencia se ejecuto correctamente
                return true;                //Retornamos true si eso sucede
            }
        } catch (SQLException ex) {              //Si falla algo atrapamos la excepción
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);      //Se gurada la excepción en un archivo de tipo Log
        } finally {
            con.cerrarConexion();               //cerramos la conexión con la BD
        }
        return false;   //Retornamos falso en caso, de que algo falle
    }

    //método para listar
    public List<ProductoDTO> readAll() {
        List<ProductoDTO> lst = null;       //Creamos una lista para almacenar objetos de tipo ProductoDTO igualandola por el momento a Null
        PreparedStatement psnt;             //Declaramos el estamento
        try {
            psnt = con.getCnn().prepareStatement(SQL_READALL);   //Hacemos que el estamento sea igual a un query establecido previamente
            ResultSet rs = psnt.executeQuery();     //Ejecutamos el estamento y guardamos las consultas y/o registros en un objeto de tipo ResultSet
            lst = new ArrayList<>();                //Hacemos que nuestra lista sea igual ahora a un nuevo ArrayList
            while (rs.next()) {                     //Mientras existan registro se crearan objetos que almacenen los datos
                //Vamos creando objetos que siempre almacenaran los datos obtenidos de los registros de la BD
                ProductoDTO obj = new ProductoDTO(rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getInt("unidades"),
                        rs.getInt("valor"));
                lst.add(obj);     //Cada objeto con parámetros completos es añadido a la lista
            }
        } catch (SQLException ex) {                                                     //Atrapamos la excepción
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);  //Guardamos la excepción en un archivo de tipo log
        } finally {
            con.cerrarConexion();    //Cerramos la conexión con la BD
        }
        return lst;                  //Retornamos la lista obtenida en los procesos previos
    }

    //método para borrar
    public boolean delete(ProductoDTO item) {
        PreparedStatement ps;                               //Declaramos el estamento
        try {
            ps = con.getCnn().prepareStatement(SQL_DELETE); //Hacemos que el estamento sea igual a un query previamente declarado arriba
            ps.setInt(1, item.getId_pro());                 //El estamento ahora almacena un Id obtenido de un registro en la base de datos
            if (ps.executeUpdate() > 0) {                   //Verificamos si se esta ejecutando el query
                return true;                                //Retornamos verdadero o true si se ejecuta
            }
        } catch (SQLException ex) {                         //Atrapamos la excepción
            throw new Error(ex.getMessage());               //lanzamos una excepción si algo falla
        } finally {
            con.cerrarConexion();                           //cerramos la conexión con la BD
        }
        return false;                                       //Retornamos falso o false si algo falla
    }

    public boolean update(ProductoDTO item) {
        PreparedStatement ps;                               //Declaramos el estamento
        try {  
            ps = con.getCnn().prepareStatement(SQL_UPDATE); //Hacemos que el estamento sea igual a un query
            //Asignamos valores al estamento
            ps.setString(1, item.getNombre_pro());        
            ps.setString(2, item.getDescripcion_pro());
            ps.setInt(3, item.getUnidades_pro());
            ps.setLong(4, item.getValor_pro());
            ps.setInt(5, item.getId_pro());    //El id va de últimas en este caso porque de el depende la consulta
            if (ps.executeUpdate() > 0) {       //Verificamos si se esta ejcutando el query
                return true;                    //Devolvemos true o verdadero si lo esta haciendo
            }       
        } catch (SQLException ex) {             //Atrapamos la excepción 
            System.out.println("Error al Actualizar "+ex.getMessage()); //Imprimimos el mensaje de error en consola
        } finally {
            con.cerrarConexion();               //cerramos la conexión con la BD
        }
        return false;                           //retornamos false o falso si algo falla en el proceso
    }

    public ProductoDTO read(ProductoDTO filter) {
        ProductoDTO objRes = null;                          //creamos un objeto de tipo ProductoDTO donde almacenaremos la consulta y lo igualamos a null
        PreparedStatement psnt;                             //preparamos el estamento
        try {
            psnt = con.getCnn().prepareStatement(SQL_READ); //Hacemos que el estamento sea igual a una sentencia SQL (Query)
            psnt.setInt(1, filter.getId_pro());             //Le pasamos al estamento el id que proviene de un objeto de tipo DTO externo
            ResultSet rs = psnt.executeQuery();             //Se ejecuta el query y los datos obtenidos de almacenan en un resultset
            while (rs.next()) {                             //Mientras exista algun registro se creara un objeto para almacenar
                //Los datos del resultset se le pasan al objeto de tipo ProductoDTO creado
                objRes = new ProductoDTO(                   
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getInt("unidades"),
                        rs.getInt("valor"));
            }
        } catch (SQLException ex) {                         //Atrapamos la excepción
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);  //Se guarda la excepción en un archivo log
        } finally {
            con.cerrarConexion();                           //cerramos la conexión con la BD
        }
        return objRes;                                      //Retornamos el objeto sea nulo o no
    }
}//fin clase
