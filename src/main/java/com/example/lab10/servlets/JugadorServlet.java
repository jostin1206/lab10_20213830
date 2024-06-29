package com.example.lab10.servlets;

import com.example.lab10.beans.Jugador;
import com.example.lab10.daos.JugadorDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "JugadorServlet", value = "/JugadorServlet")
public class JugadorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        JugadorDao jugadorDao = new JugadorDao();
        switch (action){
            case "lista":
                //saca del modelo
                ArrayList<Jugador> list = jugadorDao.listar();

                //mandar la lista a la vista -> employee/lista.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("jugador/lista.jsp");
                rd.forward(request,response);

                break;
            case "new":

                request.getRequestDispatcher("jugador/form_new.jsp").forward(request,response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                //Job job = jobDao.buscarPorId(id);
                Jugador jugador = jugadorDao.buscarPorId(id);
                if(jugador != null){
                    request.setAttribute("jugador",jugador);
                    request.getRequestDispatcher("jugador/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/JugadorServlet");
                }
                break;
            case "del":
                //String idd = request.getParameter("id");
                int idd = Integer.parseInt(request.getParameter("id"));
                //Job jobb = jobDao.buscarPorId(idd);
                //Employee emp = employeeDao.buscarPorId(idd);
                Jugador jug = jugadorDao.buscarPorId(idd);

                if(jug != null){
                    try {
                        //jobDao.borrar(idd);
                        //employeeDao.borrar(idd);
                        jugadorDao.borrar(idd);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/JugadorServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        JugadorDao jugadorDao = new JugadorDao();
        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear"://voy a crear un nuevo empleado

                String nombre = request.getParameter("nombre");
                String edad = request.getParameter("edad");
                String posicion = request.getParameter("posicion");
                String club = request.getParameter("club");
                String sn_idSeleccion = request.getParameter("sn_idSeleccion");

                jugadorDao.crear(nombre,Integer.parseInt(edad),posicion,club,Integer.parseInt(sn_idSeleccion));
                response.sendRedirect(request.getContextPath() + "/JugadorServlet");
                //}else{
                //request.getRequestDispatcher("employee/form_new.jsp").forward(request,response);
                //}

                break;
            case "e": // voy a actualizar
                // Obtengo los parámetros de la solicitud

                int idJugador2 = Integer.parseInt((request.getParameter("idJugador")));
                String nombre2 = request.getParameter("nombre");
                int edad2 = Integer.parseInt(request.getParameter("edad"));
                String club2 = request.getParameter("club");
                String posicion2 = request.getParameter("posicion");
                int sn_idSeleccion2 = Integer.parseInt((request.getParameter("sn_idSeleccion")));

                boolean isAllValid2 = true;
                // Realiza las validaciones necesarias
                if (nombre2.length() > 25) {
                    isAllValid2 = false;
                }
                if (club2.length() > 20) {
                    isAllValid2 = false;
                }
                if (posicion2.length() > 25) {
                    isAllValid2 = false;
                }

                if (isAllValid2) {
                    // Creo un objeto jugador
                    Jugador jugador = new Jugador();
                    jugador.setIdJugador(idJugador2);
                    jugador.setNombre(nombre2);
                    jugador.setEdad(edad2);
                    jugador.setClub(club2);
                    jugador.setPosicion(posicion2);
                    jugador.setSn_idSeleccion(sn_idSeleccion2);
                    // Llamo al método actualizar del DAO
                    jugadorDao.actualizar(jugador);
                    // Redirijo a la lista de empleados
                    response.sendRedirect(request.getContextPath() + "/JugadorServlet");
                } else {
                    // Si los datos no son válidos, reenvío al formulario de edición
                    request.setAttribute("jugador", JugadorDao.buscarPorId(Integer.parseInt(request.getParameter("id"))));
                    request.getRequestDispatcher("jugador/form_edit.jsp").forward(request, response);
                }
                break;
        }
    }
}
