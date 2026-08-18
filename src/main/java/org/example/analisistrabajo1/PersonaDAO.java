package org.example.analisistrabajo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    // AGREGAR PERSONA
    public void agregarPersona(Persona persona) {

        String sql = "INSERT INTO Personas (nombre, direccion) VALUES (?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getDireccion());

            ps.executeUpdate();

            System.out.println("Persona agregada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // CONSULTAR TODAS LAS PERSONAS
    public List<Persona> obtenerPersonas() {

        List<Persona> personas = new ArrayList<>();

        String sql = "SELECT * FROM Personas";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                Persona persona = new Persona(id, nombre, direccion);

                personas.add(persona);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personas;
    }


    // MODIFICAR PERSONA
    public void modificarPersona(Persona persona) {

        String sql = "UPDATE Personas SET nombre = ?, direccion = ? WHERE id = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getDireccion());
            ps.setInt(3, persona.getId());

            ps.executeUpdate();

            System.out.println("Persona modificada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ELIMINAR PERSONA
    public void eliminarPersona(int id) {

        String sql = "DELETE FROM Personas WHERE id = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Persona eliminada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}