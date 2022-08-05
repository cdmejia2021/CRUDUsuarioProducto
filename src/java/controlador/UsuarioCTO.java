/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//librerias y clases importadas
import java.io.IOException;                //libreria para controlar excepciones de tipo IO
import java.sql.Date;                      //libreria para manejar de tipos SQL
import java.util.List;                     //libreria para manipular listas
import javax.servlet.ServletException;     //liberia para manipular excepciones de tipo Servlet
import javax.servlet.http.HttpServlet;     //libreria de excepciones http
import javax.servlet.http.HttpServletRequest;  //libreria de excepciones request
import javax.servlet.http.HttpServletResponse; //libreria de excepciones respones
import modelo.dto.UsuarioDTO;              //clase importada de tipo UsuarioDTO

/**
 *
 * @author Usuario
 */
public class UsuarioCTO extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //string utilizados para el switch case
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        //utilización del switch siempre y cuando el primer parámetro sea usuario
        if (menu.equalsIgnoreCase("Usuario")) {
            Facade obj = new Facade();            //creación el facade para ejecutar las peticiones del CTO
            switch (accion) {   //switch para verificar que operación desea realizar el usuario
                case "Listar":     
                    List<UsuarioDTO> lis = obj.listarUsuarios();    //creación e igualación de la lista de tipo producto DTO a la consulta que realiza el Facade    
                    request.setAttribute("lista_usuarios", lis);    //Ejecución de la petición por medio de un keyvalor y una variable pasada, en este caso la lista
                    break;
                case "Agregar": 
                    //Información ingresada en las cajas de texto asignadas a variables de tipo Java
                    String nombre1 = request.getParameter("txt_nombre1_us");   
                    String nombre2 = request.getParameter("txt_nombre2_us");
                    String apellido1 = request.getParameter("txt_apellido1_us");
                    String apellido2 = request.getParameter("txt_apellido2_us");
                    String nIdentificacion = request.getParameter("txt_nIdentificacion_us");
                    String correo = request.getParameter("txt_correo_us");
                    String clave = obj.asegurarClave(request.getParameter("txt_clave_us"));
                    clave = obj.cifrarAES_CBC(clave);
                    String sexo = request.getParameter("txt_sexo_us");
                    Date fecha = Date.valueOf(request.getParameter("txt_fecha_us"));
                    boolean estado = Boolean.parseBoolean(request.getParameter("txt_estado_us"));
                    //creación de un objeto de tipo UsuarioDTO que almacena las variables de tipo Java
                    UsuarioDTO nuevo = new UsuarioDTO(nombre1, nombre2, apellido1, apellido2, nIdentificacion, correo, clave, sexo, fecha, estado); 
                    obj.crearUsuario(nuevo);   //el objeto de tipo Facade crea un usuario
                    request.getRequestDispatcher("UsuarioCTO?menu=Usuario&accion=Listar").forward(request, response);  //Recargamos la página y llamamos de una vez a listar otra vez
                    break;
                case "Editar":
                    UsuarioDTO edit = new UsuarioDTO();    //Creamos un objeto de tipo UsuarioDTO para alamacenar un Id
                    edit.setId_us(Integer.parseInt(request.getParameter("id")));  //Asignamos el Id al objeto DTO
                    edit = obj.verUsuario(edit);  //La consulta que realiza el usuario la igualamos al objeto DTO creado previamente
                    request.setAttribute("usuario", edit);  //hacemos que las cajas de texto muestren los datos obtenidos de objeto 
                    request.getRequestDispatcher("UsuarioCTO?menu=Usuario&accion=Listar").forward(request, response);  //recargamos la página y listamos de una vez
                    break;
                case "Actualizar":
                    //Asignamos los valores ingresados a las cajas de texto a variables de tipo Java
                    int idA = Integer.parseInt(request.getParameter("txt_id_us"));
                    String nombre1A = request.getParameter("txt_nombre1_us");
                    String nombre2A = request.getParameter("txt_nombre2_us");
                    String apellido1A = request.getParameter("txt_apellido1_us");
                    String apellido2A = request.getParameter("txt_apellido2_us");
                    String nIdentificacionA = request.getParameter("txt_nIdentificacion_us");
                    String correoA = request.getParameter("txt_correo_us");
                    String claveA = obj.asegurarClave(request.getParameter("txt_clave_us"));
                    claveA = obj.cifrarAES_CBC(claveA);
                    String sexoA = request.getParameter("txt_sexo_us");
                    Date fechaA = Date.valueOf(request.getParameter("txt_fecha_us"));
                    boolean estadoA = Boolean.parseBoolean(request.getParameter("txt_estado_us"));
                    //Asignamos las variables a un nuevo objeto de tipo UsuarioDTO
                    UsuarioDTO actual = new UsuarioDTO(idA, nombre1A, nombre2A, apellido1A, apellido2A, nIdentificacionA, correoA, claveA, sexoA, fechaA, estadoA);
                    obj.actualizarUsuario(actual);    //llamamos el método de actualizar para el Facade
                    request.getRequestDispatcher("UsuarioCTO?menu=Usuario&accion=Listar").forward(request, response);  //recargamos la págima y  listamos nuevamente
                    break;
                case "Eliminar":
                    UsuarioDTO elim = new UsuarioDTO();   //creamos un objeto de tipo UsuarioDTO para asignarle un id
                    elim.setId_us(Integer.parseInt(request.getParameter("id")));  //le asignamos el id al objeto DTO
                    obj.eliminarUsuario(elim);  //llamamos el método del facade que elimina usuarios
                    request.getRequestDispatcher("UsuarioCTO?menu=Usuario&accion=Listar").forward(request, response);   //recargamos la página y listamos de una vez
                    break;
            }
            request.getRequestDispatcher("UsuarioVTA.jsp").forward(request, response);   //Fuera del switch-case estamos llamando la vista web 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);   //método get que llama al processrequest
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);   //método post que llama al processrequest
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
