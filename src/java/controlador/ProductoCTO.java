/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//Librerias y clases importadas 
import java.io.IOException;                        //Libreria utilizada para excepciones IO
import java.util.List;                             //Libreria utilizada para manejar listas
import javax.servlet.ServletException;             //Libreria utilizada para excepciones en el Servlet
import javax.servlet.http.HttpServlet;             //Libreria utilizada para excepciones de tipo Http
import javax.servlet.http.HttpServletRequest;      //Libreria utilizada para excepciones  Request
import javax.servlet.http.HttpServletResponse;     //Libreria utilizada para excepciones Response
import modelo.dto.ProductoDTO;                     //Clase importada de tipo ProductoDTO

/**
 *
 * @author Usuario
 */
public class ProductoCTO extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Strings utilizados para la manipulación del switch
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        //Métodos utilizados para utilizar el Switch
        if (menu.equalsIgnoreCase("Producto")) {
            Facade obj = new Facade();
            switch (accion) {
                case "Listar":
                    List<ProductoDTO> lis = obj.listarProductos();   //Creación de una lista que va a ser igual a los productos obtenidos por el FACADE.
                    request.setAttribute("lista_productos", lis);    //Ejecucion de la petición teniendo como parámetros el keyvalor y una variable, en este caso la de la lista.
                    break;
                case "Agregar":
                    //Igualamiento de variables Java a la información ingresada en las cajas de texto
                    String nombre = request.getParameter("txt_nombre_pro");
                    String des = request.getParameter("txt_descripcion_pro");
                    int und = Integer.parseInt(request.getParameter("txt_unidades_pro"));
                    int val = Integer.parseInt(request.getParameter("txt_valor_pro"));
                    //Creacion de un objeto de tipo productoDTO al que se le asignan las variables Java previas
                    ProductoDTO nuevo = new ProductoDTO(nombre, des, und, val);
                    obj.crearProducto(nuevo);   //Llamamos el método de crear un producto del FACADE 
                    request.getRequestDispatcher("ProductoCTO?menu=Producto&accion=Listar").forward(request, response);   //Recargue de la página y listamos de una vez
                    break;
                case "Editar":
                    ProductoDTO edit = new ProductoDTO();   //Creación de un objeto de tipo ProductoDTO para asignarle un Id
                    edit.setId_pro(Integer.parseInt(request.getParameter("id")));   //Asignación del id al objeto previamente creado
                    edit = obj.verProducto(edit);   //Hacemos que el objeto creado sea iguall a lo que consulta el FACADE
                    request.setAttribute("producto", edit);  //se carga el objeto en las cajas de texto
                    request.getRequestDispatcher("ProductoCTO?menu=Producto&accion=Listar").forward(request, response);  //Recargamos la página y listamos de una vez
                    break;
                case "Actualizar":
                    //Asignación de datos ingresados en las cajas de texto a variables Java
                    int id1 = Integer.parseInt(request.getParameter("txt_id_pro"));
                    String nombre1 = request.getParameter("txt_nombre_pro");
                    String des1 = request.getParameter("txt_descripcion_pro");
                    int und1 = Integer.parseInt(request.getParameter("txt_unidades_pro"));
                    int val1 = Integer.parseInt(request.getParameter("txt_valor_pro"));
                    //Creación de un nuevo objeto de tipo ProductoDTO al que le igualanos la variables Java
                    ProductoDTO actual = new ProductoDTO(id1,nombre1,des1,und1,val1);
                    obj.actualizarProducto(actual);    //Llamamos al método actualizar del FACADE
                    request.getRequestDispatcher("ProductoCTO?menu=Producto&accion=Listar").forward(request, response);  //Recargamos la página y listamos de una vez
                    break;
                case "Eliminar":
                    ProductoDTO elim = new ProductoDTO();  //Creación de un nuevo objeto de tipo ProductoDTO al que le asignaremos un Id
                    elim.setId_pro(Integer.parseInt(request.getParameter("id")));  //Asignación del Id al objeto previamente creado.
                    obj.eliminarProducto(elim);   //Llamamos el método de eliminar del FACADE
                    request.getRequestDispatcher("ProductoCTO?menu=Producto&accion=Listar").forward(request, response);   //Recargamos la página y listamos de una vez
                    break;
            }
            request.getRequestDispatcher("ProductoVTA.jsp").forward(request, response);  //Fuera del switch-case recargamos la página llamando el archivo JSP
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
        processRequest(request, response);   //El doGet llama al método processrequest para procesar la petición
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
        processRequest(request, response);   //El doPost llama al método processrequest para procesar la petición
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
