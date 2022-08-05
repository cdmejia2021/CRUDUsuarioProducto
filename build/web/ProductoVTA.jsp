<%-- 
    Document   : ProductoVTA
    Created on : 15/06/2020, 01:52:01 AM
    Author     : Usuario
--%>

<%--Librerias--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("login") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body class="demo" style="background-image: url(css/arbol.jpg)">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1 style="color: white">Productos</h1>
                </div>
            </div>
            <div class="row">
                <div class="card col-sm-4">
                    <div class="card-body">
                        <form action="ProductoCTO?menu=Producto" method="POST">
                            <div class="form-group">
                                <label>Nombre Producto</label>
                                <input class="form-control" type="text" name="txt_nombre_pro" placeholder="Ingrese el nombre del producto" required value="${producto.getNombre_pro()}">
                                <input type="hidden" value="${producto.getId_pro()}" name="txt_id_pro">
                            </div>
                            <div class="form-group">
                                <label>Descripcion Producto</label>
                                <input class="form-control" type="text" name="txt_descripcion_pro" placeholder="Ingrese la descripción del producto" value="${producto.getDescripcion_pro()}">
                            </div>
                            <div class="form-group">
                                <label>Unidades Producto</label>
                                <input class="form-control" type="text" name="txt_unidades_pro" placeholder="Ingrese las unidades del producto" required value="${producto.getUnidades_pro()}">
                            </div>
                            <div class="form-group">
                                <label>Valor Producto</label>
                                <input class="form-control" type="text" name="txt_valor_pro" placeholder="Ingrese el valor del producto" required value="${producto.getValor_pro()}">
                            </div>
                            <c:if test="${producto.getValor_pro()==null}">
                                <input class="btn btn-success" type="submit" name="accion" value="Agregar">
                            </c:if>
                            <c:if test="${producto.getValor_pro()!=null}">    
                                <input class="btn btn-warning" type="submit" name="accion" value="Actualizar">
                            </c:if>    
                        </form> 
                    </div>
                </div>
                <div class="card col-sm-6 col-md-6 col-lg-8">
                    <table class="table table-hover table-responsive table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>N° Id</th>
                                <th>Nombre</th>
                                <th>Descripcion</th>
                                <th>Unidades</th>
                                <th>Valor</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="objP" items="${lista_productos}">
                                <tr>
                                    <td>${objP.getId_pro()}</td>
                                    <td>${objP.getNombre_pro()}</td>
                                    <td>${objP.getDescripcion_pro()}</td>
                                    <td>${objP.getUnidades_pro()}</td>
                                    <td>${objP.getValor_pro()}</td>
                                    <td>
                                        <a class="btn btn-warning"
                                           href="ProductoCTO?menu=Producto&accion=Editar&id=${objP.getId_pro()}">Editar</a>
                                        <a class="btn btn-danger" 
                                           href="ProductoCTO?menu=Producto&accion=Eliminar&id=${objP.getId_pro()}">Eliminar</a>
                                    </td>

                                </tr>
                            </c:forEach>    
                        </tbody>
                    </table>
                </div>
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
            </div>
        </div>
        <footer>
            <div class="row">
                <div class="col-sm-4 d-flex flex-column align-items-start">
                    <span class="oi oi-rss"></span>
                    <a href="http://www.facebook.com">Facebook</a>
                    <a href="http://www.twitter.com">Twitter</a>
                    <a href="http://www.instagram.com">Instagram</a>
                    <a href="http://www.google.com">Google+</a>
                </div>
                <div class="col-sm-4 d-flex flex-column align-items-center">
                    <address>
                        <h3>Contactanos</h3>
                        <p>
                            <span class="oi oi-home footer-address-icon">
                            </span>
                            Calle 72 #72-64 Bogota, Colombia
                        </p>
                        <p>
                            <span class="oi oi-phone footer-address-icon">
                            </span>
                            +5713124521209
                        </p>
                        <p>
                            <span class="oi oi-inbox footer-address-icon">
                            </span>
                            guia-hoteles@contacto.com
                        </p>
                    </address>
                </div>
                <div class="col-sm-4 d-flex flex-column align-items-end">
                    <a href="http://www.facebook.com">Nosotros</a>
                    <a href="http://www.twitter.com">Precios</a>
                    <a href="http://www.instagram.com">Terminos y condiciones</a>
                    <a href="http://www.google.com">Contacto</a>
                </div>
            </div>
        </footer>                        
    </body>
</html>
