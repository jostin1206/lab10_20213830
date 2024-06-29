package com.example.lab10.daos;

import com.example.lab10.beans.Jugador;

import java.sql.*;
import java.util.ArrayList;

public class JugadorDao {
    public ArrayList<Jugador> listar(){

        ArrayList<Jugador> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/lab7";
        String username = "root";
        String password = "root";

        String sql = "select * from jugador";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                //Employee employee = new Employee();
                Jugador jugador = new Jugador();

                jugador.setIdJugador(rs.getInt(1));
                jugador.setNombre(rs.getString(2));
                jugador.setEdad(rs.getInt(3));
                jugador.setPosicion(rs.getString(4));
                jugador.setClub(rs.getString(5));
                jugador.setSn_idSeleccion(rs.getInt(6));

                lista.add(jugador);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void crear( String nombre, int edad, String posicion, String club, int sn_idSeleccion){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/lab7";
        String username = "root";
        String password = "root";

        String sql = "insert into jugador (nombre,edad,posicion,club,sn_idSeleccion) values (?, ?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            //pstmt.setInt(1,employeeId);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.setString(3,posicion);
            pstmt.setString(4,club);
            pstmt.setInt(5,sn_idSeleccion);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Jugador buscarPorId(int id){

        //Employee employee = null;
        Jugador jugador = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/lab7";
        String username = "root";
        String password = "root";

        String sql = "select * from jugador where idJugador = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {

                    jugador = new Jugador();

                    jugador.setIdJugador(rs.getInt(1));
                    jugador.setNombre(rs.getString(2));
                    jugador.setEdad(rs.getInt(3));
                    jugador.setPosicion(rs.getString(4));
                    jugador.setClub(rs.getString(5));
                    jugador.setSn_idSeleccion(rs.getInt(6));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return jugador;
    }

    public void actualizar(Jugador jugador){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/lab7";
        String username = "root";
        String password = "root";

        /*String sql = "update employees set first_name = ?" +
                ", last_name = ?" +
                ", email = ?" +
                ", phone_number = ?" +
                ", job_id = ?"+
                ", hire_date = ?" +
                ", salary = ?" +
                ", commission_pct = ?" +
                " WHERE employee_id = ?";*/

        String sql = "update jugador set nombre = ?" +
                ", edad = ?" +
                ", posicion = ?" +
                ", club = ?" +
                ", sn_idSeleccion = ?"+
                " WHERE idJugador = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1, jugador.getNombre());
            pstmt.setInt(2,jugador.getEdad());
            pstmt.setString(3, jugador.getPosicion());
            pstmt.setString(4,jugador.getClub());
            pstmt.setInt(5,jugador.getSn_idSeleccion());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void borrar(int idJugador) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/lab7";
        String username = "root";
        String password = "root";

        String sql = "delete from jugador where idJugador = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,idJugador);
            pstmt.executeUpdate();

        }
    }


}
