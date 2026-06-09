package dao;
import model.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoDAO {
    public void inserir(Medico m) {
        String sql = "INSERT INTO medico (nome, crm, especialidade, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCrm());
            stmt.setString(3, m.getEspecialidade());
            stmt.setString(4, m.getTelefone());
            stmt.executeUpdate();
            System.out.println("Medico cadastrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void buscarPorCrm(String crm) {
        String sql = "SELECT * FROM medico WHERE crm = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, crm);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_medico") + " | Nome: " + rs.getString("nome") + " | Especialidade: " + rs.getString("especialidade"));
                } else {
                    System.out.println("Medico nao encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizar(String crm, String novoTelefone) {
        String sql = "UPDATE medico SET telefone = ? WHERE crm = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoTelefone);
            stmt.setString(2, crm);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Telefone do medico atualizado.");
            else System.out.println("Medico nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletar(String crm) {
        String sql = "DELETE FROM medico WHERE crm = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, crm);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Medico removido.");
            else System.out.println("Medico nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}