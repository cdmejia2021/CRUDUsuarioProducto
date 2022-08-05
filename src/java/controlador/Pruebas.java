/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//Librerias y clases importadas
import java.util.List;               //Libreria importada para utilizar listas
import modelo.dto.ProductoDTO;       //Clase importada de tipo ProductoDTO
import modelo.dto.UsuarioDTO;        //Clas importada de tipo UsuarioDTO

/**
 *
 * @author Usuario
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    //main utilizado para pruebas 
    public static void main(String[] args) {
        Facade obj = new Facade();
        //Pruebas con los productos
        List<ProductoDTO> lst = null;
        lst = obj.listarProductos();
        if (lst != null) {
            for (ProductoDTO producto : lst) {
                System.out.println(producto);
            }
        } else {
            System.out.println("No hay Productos");
        }
        
        //Pruebas con los usuarios
        System.out.println("Ahora lista de usuarios");
        List<UsuarioDTO> lst1 = null;
        lst1 = obj.listarUsuarios();
        if (lst1 != null) {
            for (UsuarioDTO usuario : lst1) {
                System.out.println(usuario);
            }
        } else {
            System.out.println("No hay usuarios");
        }
        System.out.println("Objeto consultado: ");
        UsuarioDTO item = new UsuarioDTO();
        UsuarioDTO retorno = new UsuarioDTO();
        item.setId_us(1);
        retorno = obj.verUsuario(item);
        System.out.println(retorno);
        
        /*System.out.println("Eliminando usuario...");
        UsuarioDTO elim = new UsuarioDTO();
        elim.setId_us(4);
        obj.eliminarUsuario(elim);
        
        /*Date fecha = Date.valueOf("2000-10-03");
        boolean bool = false;
            
            UsuarioDTO objU = new UsuarioDTO("Cristian", "David", "Mejia", "Ramirez", "cedula", "mejia8935@gmail.com", "987654321", "M", fecha, bool);
            obj.crearUsuario(objU);
            Date fecha1 = Date.valueOf("1997-12-23");
            boolean bool1 = true;
             
            UsuarioDTO objU1 = new UsuarioDTO();
            objU1.setNombre1_us("Carla");
            objU1.setNombre2_us("");
            objU1.setApellido1_us("Marin");
            objU1.setApellido2_us("Espitia");
            objU1.setNIdentificacion_us("cedula");
            objU1.setCorreo_us("eltalcorreo@gmail.com");
            objU1.setClave_us("9876543210");
            objU1.setSexo_us("F");
            objU1.setFechaNac_us(fecha1);
            objU1.setEstado_us(bool1);
            objU1.setId_us(3);
            obj.actualizarUsuario(objU);*/
    }
}//fin de la clase
    
    
            

