package org.example.analisistrabajo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefonoDAO {

    // AGREGAR TELEFONO
    public void agregarTelefono(Telefono telefono) {

        String sql = "INSERT INTO Telefonos (personaId, telefono) VALUES (?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, telefono.getPersonaId());
            ps.setString(2, telefono.getTelefono());

            ps.executeUpdate();

            System.out.println("Teléfono agregado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // CONSULTAR TODOS LOS TELEFONOS
    public List<Telefono> obtenerTelefonos() {

        List<Telefono> telefonos = new ArrayList<>();

        String sql = "SELECT * FROM Telefonos";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                int personaId = rs.getInt("personaId");
                String telefono = rs.getString("telefono");

                Telefono t = new Telefono(id, personaId, telefono);

                telefonos.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telefonos;
    }


    // CONSULTAR TELEFONOS DE UNA PERSONA
    public List<Telefono> obtenerTelefonosPorPersona(int personaId) {

        List<Telefono> telefonos = new ArrayList<>();

        String sql = "SELECT * FROM Telefonos WHERE personaId = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, personaId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int id = rs.getInt("id");
                    String numero = rs.getString("telefono");

                    Telefono t = new Telefono(id, personaId, numero);

                    telefonos.add(t);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telefonos;
    }


    // MODIFICAR TELEFONO
    public void modificarTelefono(Telefono telefono) {

        String sql = "UPDATE Telefonos SET personaId = ?, telefono = ? WHERE id = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, telefono.getPersonaId());
            ps.setString(2, telefono.getTelefono());
            ps.setInt(3, telefono.getId());

            ps.executeUpdate();

            System.out.println("Teléfono modificado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ELIMINAR TELEFONO
    public void eliminarTelefono(int id) {

        String sql = "DELETE FROM Telefonos WHERE id = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Teléfono eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}