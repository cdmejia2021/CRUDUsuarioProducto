/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//Librerias y clases importadas
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;           //libreria para manipular listas
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import modelo.dao.ProductoDAO;   //importación para utilizar los objetos de tipo Producto DAO
import modelo.dao.UsuarioDAO;    //importación para utilizar los objetos de tipo Usuario DAO
import modelo.dto.ProductoDTO;   //importación para utilizar los objetos de tipo producto DTO
import modelo.dto.UsuarioDTO;    //importación para utilizar los objetos de tipo Usuario DTO

/**
 *
 * @author Usuario
 */
public class Facade {

    private String claveSecreta="A9D2G3M6J7L0P3J1";
    private String vectorInicial="A1S2D3F4G6M3L0F7";
    //creo el producto desde el facade
    public boolean crearProducto(ProductoDTO objP) {
        boolean rta = false;   //creamos e igualamos un booleano para validar si creamos un producto
        if (objP != null) {   //verificamos si el objeto de tipo ProductoDTO es nulo o no
            ProductoDAO dao = new ProductoDAO();    //creamos un objeto de tipo dao para crear el objeto
            rta = dao.create(objP);    //hacemos que el booleano sea igual al objeto nuevo y retornamos dicho objeto
        }
        return rta;   //retornamos la respuesta independiente si es false o no
    }

    //consulto varios productos desde el facade
    public List<ProductoDTO> listarProductos() {
        List<ProductoDTO> lst = null;   //creamos una lista de tipo nulo para guardar los objetos
        ProductoDAO dao = new ProductoDAO();   //creamos un objeto de tipo dao para realizar la consulta de todos los objetos de la BD
        lst = dao.readAll();    //la lista la hacemos igual a la consulta que realiza el DAO
        return lst;    //retornamos la lista con los objetos ya consultados 
    }

    //consultar un producto desde el facade
    public ProductoDTO verProducto(ProductoDTO item) {
        ProductoDTO obj;      //creamos un objeto de tipo DTO
        ProductoDAO dao = new ProductoDAO();    //creamos un objeto de tipo dao que realiza la lectura
        obj = dao.read(item);    //hacemos que el objeto DTO sea igual al que obtiene el DAO
        return obj;    //retornamos el objeto que hemos consultado y que esta guardado en el DAO
    }

    //actualizamos el producto desde el facade 
    public boolean actualizarProducto(ProductoDTO objP) {
        boolean rta = false;  //creamos la variable booleana que controlara si se actualiza o no un producto, adicional hacemos esta igual a false
        if (objP != null) {    //verificamos si el objeto es nulo o no 
            ProductoDAO dao = new ProductoDAO();   //creamos un objeto de tipo DAO donde ejecutaremos el query de la actualización
            rta = dao.update(objP);           //hacemos que la variable sea igual a lo que obtiene el DAO
        }
        return rta;   //retornamos la variable independiente si se guardo o no el objeto
    }

    //eliminamos el producto desde el facade 
    public boolean eliminarProducto(ProductoDTO elim) {
        ProductoDAO dao = new ProductoDAO();     //creamos un objeto de tipo dao para eliminar 
        return dao.delete(elim);       //retornamos el objeto pero nulo, independiente si se encuentra o no
    }

    //creamos el usuario desde el facade
    public boolean crearUsuario(UsuarioDTO objU) {
        boolean rpta = false;  //validamos si se puede crear el usuario por medio de booleanos
        if (objU != null) {  //verificamos si el objeto de tipo UsuarioDTO no se nulo
            UsuarioDAO dao = new UsuarioDAO();  //si es asi creamos un objeto de tipo UsuarioDAO
            dao.create(objU);    //para crear el usuario pasamos como parametro el objeto de tipo DTO que sabemos no es nulo por el if
            System.out.println("Se creo el objeto");
        }
        return rpta;   //finalmente retornamos false si no se pudo crear el objeto, en caso contario sera true
    }

    public boolean eliminarUsuario(UsuarioDTO elim) {
        UsuarioDAO dao = new UsuarioDAO();  //creamos un objeto de tipo dao para eliminar
        return dao.delete(elim);   //retornamos el objeto ya eliminado
    }

    public boolean actualizarUsuario(UsuarioDTO objU) {
        boolean rpta = false;     //creamos e inicializamos un booleano para verificar si actualizamos o no el usuario
        if (objU != null) {   //verificamos que el objeto de tipo UsuarioDTO no sea nulo
            UsuarioDAO dao = new UsuarioDAO();  //Si no es nulo creamos un objeto de tipo UsuarioDAO para realizar las operaciones
            dao.update(objU);   //llamamos al método update para realizar la actualización del objeto
        }
        return rpta;   //retornamos false si no se pudo realizar la actualización y true en caso contrario
    }

    public UsuarioDTO verUsuario(UsuarioDTO item) {
        UsuarioDTO obj;    //creamos un objeto de tipo UsuarioDTO donde almacenaremos el objeto a consultar de la BD
        UsuarioDAO dao = new UsuarioDAO();  //creamos un objeto de tipo UsuarioDAO para que realice las operaciones necesarioas
        obj = dao.read(item);  //usamos el método read para consultar el objeto que deseamos
        return obj;  //retornamos finalmente el objeto sin importar si es o no nulo
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioDTO> lst = null;   //creamos una lista de tipo UsuarioDTO para almacenar los objetos que provienen de los registros de la BD y la igualamos a nulo
        UsuarioDAO dao = new UsuarioDAO(); //Creamos un objeto de tipo UsuarioDAO para que realice las operaciones
        lst = dao.readAll();   //hacemos que la ista sea igual a toda la cantidad de registros que obtiene el DAO
        return lst;    //retornamos la lista
    }

    public UsuarioDTO ValCredenciales(UsuarioDTO usuarioLeer) {
        UsuarioDAO objDao = new UsuarioDAO();
        UsuarioDTO objDto;
        objDto = objDao.Login(usuarioLeer);
        return objDto;
    }

    public String asegurarClave(String clave) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-512");
            System.out.println("clave en bytes sin Cifrar: " + clave.getBytes("UTF-8"));
            
            sha256.update(clave.getBytes("UTF-8"));
            //System.out.println("Clave en bytes cifrado: " + sha256.digest());
            
            //clave = String.format("%064x", new BigInteger(1, sha256.digest()));
            //System.out.println("clave BigInteger: " + clave);
            //System.out.println("longitud: " + clave.length());

            clave = Base64.getEncoder().encodeToString(sha256.digest());
            System.out.println("clave string b64: " + clave);
            System.out.println("longitud " + clave.length());
            
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("sha1" + ex.getMessage());
        } catch (UnsupportedEncodingException ex){
            System.out.println("sha2" + ex.getMessage());
        }
        return clave;
    }
    
    public String cifrarAES_CBC(String texto){
        String textoCifrado = "";
        Cipher objAES;
        try {
            objAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keyIni = new SecretKeySpec(this.claveSecreta.getBytes("UTF-8"), "AES");
            IvParameterSpec ivp = new IvParameterSpec(this.vectorInicial.getBytes("UTF-8"));
            //Inicializar el objeto Cipher en modo cifrado
            objAES.init(Cipher.ENCRYPT_MODE, keyIni, ivp);
            byte[] byteCifrado = objAES.doFinal(texto.getBytes("UTF-8"));
            textoCifrado = Base64.getEncoder().encodeToString(byteCifrado);
            System.out.println("CifradoAES: "+textoCifrado);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) { 
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return textoCifrado;
    }
}
