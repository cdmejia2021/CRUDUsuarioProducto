/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

//Librerias y clases Importadas
import conexiones.ConexionMsql;         //Importamos la clase que obtiene la conexión de la BD
import java.sql.PreparedStatement;      //Importamos la libreria que ejecuta los estamentos SQL
import java.sql.ResultSet;              //Importamos la libreria que guarda los datos obtenidos de los registros de las tablas de la BD
import java.sql.SQLException;           //Libreria usada para el manejo excepciones de tipo SQL
import java.util.ArrayList;             //Libreria utilizada para el manejo de ArrayList
import java.util.List;                  //Libreria utilizada para el manejo de Listas
import java.util.logging.Level;         //Libreria encargada de manejar excepciones con archivos 
import java.util.logging.Logger;        //Libreria encargada de manejar excepciones con archivos
import modelo.dto.UsuarioDTO;           //Clase importada para el manejo de objetos de tipo UsuarioDTO

/**
 *
 * @author Usuario
 */
public class UsuarioDAO {

    //querys para ejecutar las sentencias SQL
    private static final String SQL_INSERT = "INSERT INTO tb_usuario "
            + "(nombre1, nombre2, apellido1, apellido2, n_identificacion, correo, clave, sexo, fecha_nac, estado) VALUES "
            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM tb_usuario WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE tb_usuario SET nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, n_identificacion = ?, correo = ?, clave = ?, sexo = ?, fecha_nac = ?, estado = ? WHERE id = ?";
    private static final String SQL_READ = "SELECT * FROM tb_usuario WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT * FROM tb_usuario";
    private static final String SQL_LOGIN = "SELECT * FROM tb_usuario WHERE correo = ? and clave = ?";

    //variable utilizada para la conexión a la BD
    private static final ConexionMsql con = ConexionMsql.getInstance();

    //creamos un objeto y lo retornamos
    public boolean create(UsuarioDTO c) {
        try {
            PreparedStatement ps;   //preparamos el estamento
            ps = con.getCnn().prepareStatement(SQL_INSERT);  //le indicamos al estamento lo que debe realizar
            //pasamos todas los datos de las variables para crear el objeto al estamento
            ps.setString(1, c.getNombre1_us());
            ps.setString(2, c.getNombre2_us());
            ps.setString(3, c.getApellido1_us());
            ps.setString(4, c.getApellido2_us());
            ps.setString(5, c.getNIdentificacion_us());
            ps.setString(6, c.getCorreo_us());
            ps.setString(7, c.getClave_us());
            ps.setString(8, c.getSexo_us());
            ps.setDate(9, (java.sql.Date) c.getFechaNac_us());
            ps.setBoolean(10, c.isEstado_us());
            if (ps.executeUpdate() > 0) {  //validamos si lo que se hace es correcto, de ser asi devolvemos verdadero o true
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);  //si hay un error en el proceso lo atrapamos y lo enviamos a un archivo
        } finally {
            con.cerrarConexion();  //cerramos la conexión con la BD
        }
        return false;  //devolvemos falso si algo falla en el proceso
    }

    //borramos el objeto utilizando su id 
    public boolean delete(UsuarioDTO item) {
        try {
            PreparedStatement ps;    //preparamos el estamento
            ps = con.getCnn().prepareStatement(SQL_DELETE);   //le damos las instrucciones al estamento
            ps.setInt(1, item.getId_us());   //pasamos el id del objeto que deseamos borrar
            if (ps.executeUpdate() > 0) {   //verificamos si realiza la acción devolviendo true
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);  //atrapamos el error si no llega a hacer todo completo
        } finally {
            con.cerrarConexion();  //cerramos la conexión con la BD
        }
        return false;   //devolvemos falso si algo falla en el proceso
    }

    //actualizamos el objeto por medio de su id
    public boolean update(UsuarioDTO item) {
        try {
            PreparedStatement ps;  //preparamos el estamento
            ps = con.getCnn().prepareStatement(SQL_UPDATE);  //le damos las instrucciones de lo que debe hacer
            //ingresamos los datos
            ps.setString(1, item.getNombre1_us());
            ps.setString(2, item.getNombre2_us());
            ps.setString(3, item.getApellido1_us());
            ps.setString(4, item.getApellido2_us());
            ps.setString(5, item.getNIdentificacion_us());
            ps.setString(6, item.getCorreo_us());
            ps.setString(7, item.getClave_us());
            ps.setString(8, item.getSexo_us());
            ps.setDate(9, (java.sql.Date) item.getFechaNac_us());
            ps.setBoolean(10, item.isEstado_us());
            ps.setInt(11, item.getId_us());
            if (ps.executeUpdate() > 0) {  //verificamos si realizo la acción del query , retornamos verdadero si lo hace
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);  //si algo sucede atrapamos el error
        } finally {
            con.cerrarConexion();   //cerramos la conexión con la BD
        }
        return false;
        //si algo no funciona retornamos falso
    }

    //consultamos un solo producto
    public UsuarioDTO read(UsuarioDTO filter) {
        UsuarioDTO objRes = null;     //creamos un objeto de tipo DTO
        PreparedStatement psnt;  //preparamos el estamento
        try {
            psnt = con.getCnn().prepareStatement(SQL_READ);   //le damos instrucciones al estamento
            psnt.setInt(1, filter.getId_us());  //pasamos el id del objeto que queremos buscar al estamento
            ResultSet rs = psnt.executeQuery();   //en el result set guardamos todos los datos del registro que obtuvimos en la BD
            while (rs.next()) {   //Siempre y cuando halla datos se ejecuta el while y el next
                objRes = new UsuarioDTO(rs.getInt("id"), rs.getString("nombre1"), rs.getString("nombre2"),
                        rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("n_identificacion"), rs.getString("correo"),
                        rs.getString("clave"), rs.getString("sexo"), rs.getDate("fecha_nac"),
                        rs.getBoolean("estado"));
                //Pasamos todos los datos del registro a un objeto
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);  //atrapa el error en caso de que algo de lo anterior no funcione
        } finally {
            con.cerrarConexion();   //cerramos la conexión con la BD
        }
        return objRes;   //retornamos el objeto consultado sea nulo o no
    }

    //consultamos todos los productos de la tabla (listamos)
    public List<UsuarioDTO> readAll() {
        List<UsuarioDTO> lst = null;  //creamos una lista de tipo UsuarioDTO para almacenar todos los objetos correspondientes y esta la hacemos igual a nulo
        PreparedStatement psnt;  //preparamos el estamento
        try {
            psnt = con.getCnn().prepareStatement(SQL_READ_ALL);  //le damos instrucciones al estamento mediante el query
            ResultSet rs = psnt.executeQuery();   //en el resultset guardamos todos los datos de los registros de las tablas
            lst = new ArrayList();   //hacemos que la lista ya no sea nula sino que se cree y sea de tipo Arraylist
            while (rs.next()) {      //mientras que siempre haya un registro siguiente siempre ejecutaremos
                UsuarioDTO obj = new UsuarioDTO(rs.getInt("id"), rs.getString("nombre1"), rs.getString("nombre2"),
                        rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("n_identificacion"),
                        rs.getString("correo"), rs.getString("clave"), rs.getString("sexo"), rs.getDate("fecha_nac"),
                        rs.getBoolean("estado"));
                //guardamos los datos de cada registro en un objeto
                lst.add(obj);  //cada objeto creado es añadido a la lista hasta que ya no haya mas registros (la cantidad de objetos es igual a la de registros)
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);  //atrapamos el error por si algún proceso anterior no se logra realizar
        } finally {
            con.cerrarConexion();  //cerramos la conexión con la BD
        }
        return lst;   //retornamos la lista de objetos sea nula o no
    }

    public UsuarioDTO Login(UsuarioDTO filter) {
        UsuarioDTO objRes = null;     //creamos un objeto de tipo DTO
        PreparedStatement psnt;  //preparamos el estamento
        try {
            psnt = con.getCnn().prepareStatement(SQL_LOGIN);   //le damos instrucciones al estamento
            psnt.setString(1, filter.getCorreo_us());
            psnt.setString(2, filter.getClave_us());//pasamos el id del objeto que queremos buscar al estamento
            ResultSet rs = psnt.executeQuery();   //en el result set guardamos todos los datos del registro que obtuvimos en la BD
            while (rs.next()) {   //Siempre y cuando halla datos se ejecuta el while y el next
                objRes = new UsuarioDTO(rs.getInt("id"), rs.getString("nombre1"), rs.getString("nombre2"),
                        rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("n_identificacion"), rs.getString("correo"),
                        rs.getString("clave"), rs.getString("sexo"), rs.getDate("fecha_nac"),
                        rs.getBoolean("estado"));
                //Pasamos todos los datos del registro a un objeto
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);  //atrapa el error en caso de que algo de lo anterior no funcione
        } finally {
            con.cerrarConexion();   //cerramos la conexión con la BD
        }
        return objRes;   //retornamos el objeto consultado sea nulo o no
    }

}//fin clase

