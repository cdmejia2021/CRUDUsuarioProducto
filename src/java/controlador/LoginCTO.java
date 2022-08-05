/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Usuario
 */
public class LoginCTO extends HttpServlet {

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
        String accion = request.getParameter("accion");
        Facade objF = new Facade();
        switch (accion) {
            case "Validalogin":
                String usuario = request.getParameter("txt_usuario");
                String clave = objF.asegurarClave(request.getParameter("txt_clave"));
                clave = objF.cifrarAES_CBC(clave);
                System.out.println("clave con SHA-256"+clave);
                UsuarioDTO objDto = new UsuarioDTO();
                objDto.setCorreo_us(usuario);
                objDto.setClave_us(clave);
                objDto = objF.ValCredenciales(objDto);  //Se guarda en el mismo objeto que se va a enviar pero eso no importa
                if (objDto != null) {
                    System.out.println("Usuario y contraseña válidos");
                    //Se crea la sesión (Usuario existe)
                    HttpSession objLogin = request.getSession();  //Se crea un numero de sesión unico
                    objLogin.setAttribute("login", objDto);//para amarrar la sesión con los datos del usuario
                    request.getRequestDispatcher("Principal.jsp").forward(request, response);//Redireccionamos a la página

                } else {
                    System.out.println("Usuario o contraseña invalidos");
                    request.getRequestDispatcher("ErrorSesion.jsp").forward(request, response);
                }
                break;
            case "Salir":
                HttpSession sesion = request.getSession();
                sesion.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
