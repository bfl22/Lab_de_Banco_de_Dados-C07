package dao;
import model.*;
import java.sql.Connection;
import dao.*;
import model.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProntuarioDAO {
    public void inserir(Prontuario pr) {
        String sql = "INSERT INTO prontuario (historico_geral, data_abertura, id_paciente) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pr.getHistoricoGeral());
            stmt.setString(2, pr.getDataAbertura());
            stmt.setInt(3, pr.getIdPaciente());
            stmt.executeUpdate();
            System.out.println("Prontuario aberto.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void buscarPorIdPaciente(int idPaciente) {
        String sql = "SELECT * FROM prontuario WHERE id_paciente = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Prontuario ID: " + rs.getInt("id_prontuario") + " | Historico: " + rs.getString("historico_geral"));
                } else {
                    System.out.println("Prontuario nao encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizar(int idProntuario, String novoHistorico) {
        String sql = "UPDATE prontuario SET historico_geral = ? WHERE id_prontuario = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoHistorico);
            stmt.setInt(2, idProntuario);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Historico do prontuario atualizado.");
            else System.out.println("Prontuario nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletar(int idProntuario) {
        String sql = "DELETE FROM prontuario WHERE id_prontuario = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProntuario);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Prontuario removido.");
            else System.out.println("Prontuario nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}