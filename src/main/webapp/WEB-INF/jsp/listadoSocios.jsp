<%@ page import="org.iesvdm.entreno_jsp.model.Socio" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: josed
  Date: 25/12/2024
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <div>Listado de Socios</div>
        <div>
            <form method="get" action="GrabarSociosServlet">
                <input type="submit" value="Crear Socio">
            </form>
        </div>
        <div id="indices">
            <div>ID</div>
            <div>Nombre</div>
            <div>Estatura</div>
            <div>Edad</div>
            <div>Localidad</div>
            <div>Operación</div>
        </div>
        <div>
            <%
                List<Socio> listado = (List<Socio>) request.getAttribute("listado");
                for(Socio socio : listado)
                {
            %>
            <div id="<%=socio.getSocioID()%>">
                <div><%=socio.getSocioID()%></div>
                <div><%=socio.getNombre()%></div>
                <div><%=socio.getEstatura()%></div>
                <div><%=socio.getEdad()%></div>
                <div><%=socio.getLocalidad()%></div>
                <form method="post" action="BorrarSociosServlet">
                    <input type="hidden" name="id" value="<%=socio.getSocioID()%>">
                    <input type="submit" value="Borrar">
                </form>
                <form method="get" action="EditarSociosServlet">
                    <input type="hidden" name="id" value="<%=socio.getSocioID()%>">
                    <input type="submit" value="Editar">
                </form>
            </div>
        </div>
        <%
                }
        %>
    </div>

</body>
</html>
