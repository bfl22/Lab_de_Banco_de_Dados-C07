package dao;
import model.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaDAO {
    public void inserir(Consulta c) {
        String sql = "INSERT INTO consulta (data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getDataConsulta());
            stmt.setString(2, c.getHoraConsulta());
            stmt.setDouble(3, c.getValorPago());
            stmt.setString(4, c.getQueixaPrincipal());
            stmt.setString(5, c.getStatus());
            stmt.setInt(6, c.getIdPaciente());
            stmt.setInt(7, c.getIdMedico());
            stmt.executeUpdate();
            System.out.println("Consulta agendada.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void buscarPorStatus(String status) {
        String sql = "SELECT * FROM consulta WHERE status = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_consulta") + " | Data: " + rs.getDate("data_consulta") + " | Categoria: " + rs.getString("categoria_consulta"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizarStatus(int idConsulta, String novoStatus) {
        String sql = "UPDATE consulta SET status = ? WHERE id_consulta = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, idConsulta);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Status da consulta atualizado.");
            else System.out.println("Consulta nao encontrada.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletar(int idConsulta) {
        String sql = "DELETE FROM consulta WHERE id_consulta = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idConsulta);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Consulta excluida.");
            else System.out.println("Consulta nao encontrada.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}