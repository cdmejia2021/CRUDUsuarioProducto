<%-- 
    Document   : UsuarioVTA
    Created on : 3/07/2020, 02:38:42 PM
    Author     : Usuario
--%>
<%--Librerias--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    <%--libreria importada para utilizar estructuras de control, bucles y demás--%>
<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<%
    HttpSession sesion = request.getSession();
    if(sesion.getAttribute("login")==null){
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head><%--metas donde se declara la página responsive, título de la página y demás--%>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15">
        <meta name="viewport" content="width-device-width, initial-scale=1, shrink-to-fit=no" >
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <%--body de la página--%>
    <body style="background-image: url(css/arbol.jpg)">
        <div class="container">
            <div class="row">
                <div class="col-sm-12, card-body">
                    <h1 style="color: white">Usuarios</h1>
                </div>
            </div>
            <%--Contenedor del formulario--%>
            <div class="card">
                <div class="card-body">
                    <%--Método Post utilizado para pasar parámetros al servlet "UsuarioCTO"--%>
                    <form action="UsuarioCTO?menu=Usuario" method="POST">
                        <div class="form-row">
                            <div class="form-group col-sm-6">
                                <label>Nombre 1</label>
                                <input class="form-control" type="text" name="txt_nombre1_us" placeholder="Ingrese su primer nombre" required value="${usuario.getNombre1_us()}">
                                <input type="hidden" value="${usuario.getId_us()}" name="txt_id_us">
                            </div>
                            <div class="form-group col-sm-6">
                                <label>Apellido 1</label>
                                <input class="form-control" type="text" name="txt_apellido1_us" placeholder="Ingrese su primer apellido" required value="${usuario.getApellido1_us()}">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-sm-6">
                                <label>Nombre 2</label>
                                <input class="form-control" type="text" name="txt_nombre2_us" placeholder="Ingrese su segundo nombre" value="${usuario.getNombre2_us()}">
                            </div>    
                            <div class="form group col-sm-6">
                                <label>Apellido 2</label>
                                <input class="form-control" type="text" name="txt_apellido2_us" placeholder="Ingrese su segundo apellido" value="${usuario.getApellido2_us()}">
                            </div>    
                        </div>
                        <div class="form-row">
                            <div class="form-group col-sm-3">
                                <label>Tipo de identificación</label>
                                <input class="form-control" type="text" name="txt_nIdentificacion_us" placeholder="Ingrese el tipo de su identificación" requires value="${usuario.getNIdentificacion_us()}">
                            </div>
                            <div class="form-group col-sm-9">
                                <label>Correo</label>
                                <input class="form-control" type="email" name="txt_correo_us" placeholder="Ingrese su correo" required value="${usuario.getCorreo_us()}">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-sm-9">
                                <label>Clave</label>
                                <input class="form-control" type="password" name="txt_clave_us" placeholder="Ingrese su clave" required value="${usuario.getClave_us()}">
                            </div>
                            <div class="form-check col-sm-3">
                                <label>Sexo</label>
                                <input class="form-control" type="text" name="txt_sexo_us" placeholder="Ingrese el sexo (M, F o I)" required value="${usuario.getSexo_us()}">
                            </div>
                        </div>  
                        <div class="form-row">
                            <div class="form-group col-sm-9">
                                <label>Fecha Nacimiento</label>
                                <input class="form-control" type="date" name="txt_fecha_us" min="1920-07-03" max="2018-01-01" required value="${usuario.getFechaNac_us()}">
                            </div>
                            <div class="form-group col-sm-3">
                                <label>Estado</label>
                                <input class="form-control" type="text" name="txt_estado_us" placeholder="Ingrese el estado (true o false)" required value="${usuario.isEstado_us()}">
                            </div>
                        </div>
                        <input class="btn btn-success" type="submit" name="accion" value="Agregar">
                        <input class="btn btn-warning" type="submit" name="accion" value="Actualizar">
                    </form>
                </div>
            </div>
            <%--Contenedor de la tabla--%>
            <div class="card">
                <table class="table table-hover table-responsive table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>N° Id</th>
                            <th>Nombre 1</th>
                            <th>Nombre 2</th>
                            <th>Apellido 1</th>
                            <th>Apellido 2</th>
                            <th>Tipo de identificación</th>
                            <th>Correo</th>
                            <th>Clave</th>
                            <th>Sexo</th>
                            <th>Fecha</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%--Estructura de control utilizada para recorrer muchos objetos e imprimirlos en la tabla, con la variable auxilair objP y el keyvalor lista_usuarios--%>    
                        <c:forEach var="objP" items="${lista_usuarios}"> 
                            <tr>
                                <td>${objP.getId_us()}</td>
                                <td>${objP.getNombre1_us()}</td>
                                <td>${objP.getNombre2_us()}</td>
                                <td>${objP.getApellido1_us()}</td>
                                <td>${objP.getApellido2_us()}</td>
                                <td>${objP.getNIdentificacion_us()}</td>
                                <td>${objP.getCorreo_us()}</td>
                                <td>${objP.getClave_us()}</td>
                                <td>${objP.getSexo_us()}</td>
                                <td>${objP.getFechaNac_us()}</td>
                                <td>${objP.isEstado_us()}</td>
                                <td>
                                    <%--Botones utilizados para editar y eliminar objetos--%>
                                    <%--Parámetros pasados al Servlet "UsuarioCTO" donde esta la acción de Editar--%>
                                    <a class="btn btn-warning" href="UsuarioCTO?menu=Usuario&accion=Editar&id=${objP.getId_us()}">Editar</a>
                                    <%--Parámetros pasados al Servlet "UsuarioCTO" donde esta la acción de Eliminar--%>
                                    <a class="btn btn-danger" href="UsuarioCTO?menu=Usuario&accion=Eliminar&id=${objP.getId_us()}">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <%--scripts importados de Bootstrap--%>
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        </div>
    </body>
</html>
