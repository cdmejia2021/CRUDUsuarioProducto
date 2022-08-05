<%-- 
    Document   : Principal
    Created on : 15/06/2020, 02:08:59 AM
    Author     : Usuario
--%>

<%@page import="modelo.dto.UsuarioDTO"%>
<%--Librerias--%>
<%--Validamos la sesión del cliente--%>
<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<%
    HttpSession sesion = request.getSession(); //Trae el objeto sesión
    UsuarioDTO usuRegistrado = (UsuarioDTO) session.getAttribute("login");
    if (sesion.getAttribute("login") == null) {  //Pregunta si el usuario no esta registrado
        response.sendRedirect("index.jsp");        
    } else {//El usuario registrado hace lo que esta dentro de este else(permite cargar)

%>
<!DOCTYPE html>
<html>
    <head>    <%--metas del html donde la página es responsive, importación de bootstrap, título de la página web y demás--%>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body class="demo" style="background-image: url(css/arbol.jpg)">
        <%--Barra de menú--%>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="btn btn-outline-light" style="margin-left: 10px;border: none" href="#">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <%--Parámetros pasados al Servlet "ProductoCTO" por medio de los Link--%>
                        <a class="btn btn-outline-light" style="margin-left: 10px;border: none" href="ProductoCTO?menu=Producto&accion=Listar" target="Frame" 
                           >Producto</a>                        
                    </li>
                    <li class="nav-item">
                        <%--Parámetros pasados al Servlet "UsuarioCTO" por medio de los link--%>
                        <a class="btn btn-outline-light" style="margin-left: 10px;border: none" href="UsuarioCTO?menu=Usuario&accion=Listar" target="Frame">Usuarios</a>
                    </li>
                </ul>
            </div>
            <div class="dropdown">
                <a href="#" class="btn btn-outline-light dropdown-toggle"
                   style="margin-right: 40px; border: none" data-toggle="dropdown">Mi cuenta</a>
                <div class="dropdown-menu text-center">
                    <a><%=usuRegistrado.toString()%></a>
                    <a><%=usuRegistrado.getCorreo_us()%></a>
                    <div class="dropdown-divider"></div>
                    <a href="LoginCTO?accion=Salir">Cerrar sesión</a>
                </div>         
            </div>
        </nav>
        <div style="height:1030px;">
            <iframe name="Frame" style="height: 100%;width: 100%;border: 2px"></iframe>
        </div>
        <%-- scripts de Bootstrap--%>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
<%}%>