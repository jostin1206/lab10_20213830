<%@ page import="com.example.lab10.beans.Jugador" %><%--
  Created by IntelliJ IDEA.
  User: josti
  Date: 28/06/2024
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="java.util.ArrayList<com.example.lab10.beans.Jugador>" />
<html>
<head>
    <title>Jugadores</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <h1 class="float-start link-dark">Lista de Jugadores</h1>
        <div class="float-end">

            <a class="btn btn-primary mt-1" href="<%=request.getContextPath() %>/JugadorServlet?action=new">Nuevo Jugador</a>
        </div>
    </div>
    <hr/>

    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>#</th>
            <th>Nombre</th>
            <th>Edad</th>
            <th>Posicion</th>
            <th>Club</th>
            <th>Seleccion</th>
            <th></th>

        </tr>
        <% for (Jugador jugador : lista) { %>
        <tr>
            <td><%=jugador.getIdJugador()%>
            </td>
            <td><%=jugador.getNombre()%>
            </td>
            <td><%=jugador.getEdad()%>
            </td>
            <td><%=jugador.getPosicion()%>
            </td>
            <td><%=jugador.getClub()%>
            </td>
            <td><%=jugador.getSn_idSeleccion()%>
            </td>

            <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/JugadorServlet?action=del&id=<%= jugador.getIdJugador() %>">Borrar</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
