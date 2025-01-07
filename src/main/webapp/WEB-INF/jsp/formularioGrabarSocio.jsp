<%--
  Created by IntelliJ IDEA.
  User: josed
  Date: 25/12/2024
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
  <div>INTRODUZCA LOS DATOS DEL NUEVO SOCIO</div>
  <form method="post" action="GrabarSociosServlet">
    <div>Nombre <input type="text" name="nombre"></div>
    <div>Estatura <input type="text" name="estatura"></div>
    <div>Edad <input type="text" name="edad"></div>
    <div>Localidad <input type="text" name="localidad"></div>
    <input type="submit" value="Crear Socio">
  </form>
</body>
</html>
