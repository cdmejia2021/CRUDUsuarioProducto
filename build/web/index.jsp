<%-- 
    Document   : index
    Created on : 14/06/2020, 11:59:05 PM
    Author     : Usuario
--%>

<%--Librerias--%>
<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<!DOCTYPE html>
<html>
    <head><%--metas donde esta que la página que sea responsive, titulo de la página web y demás --%>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Taller Java Web</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <%--body de la página--%>
    <body class="demo" style="background-image: url(css/jrt.jpg)">
        <div class="modal-dialog text-center">
            <div class="col-lg-8">
                <div class="modal-content">
                    <div class="col-12">
                        <img src="Img/avatar.jpg" height="128px" width="200px"/>
                    </div>
                    <form action="LoginCTO?accion=Validalogin" method="POST">   <%--Sin el action no sirve--%>
                        <div class="form-group text-center">
                            <p>
                                <strong>
                                    Bienvenidos al sistema de login
                                </strong>
                            </p>
                        </div>
                        <div class="form-group text-center">
                            <label>Usuario:</label>
                            <input type="text" name="txt_usuario" placeholder="ejemplo@gmail.com" class="form-control" required>  <%--Ejemplos de como debe ingresar con placeholder--%>
                        </div>
                        <div class="form-group text-center">
                            <label>Clave:</label>
                            <input type="password" name="txt_clave" placeholder="password" class="form-control" required>
                        </div>
                        <input type="submit" name="btnIngresar" value="Ingresar" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
        <a href="Principal.jsp">Página Principal</a>
        <%--scripts importados de Bootstrap--%>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
