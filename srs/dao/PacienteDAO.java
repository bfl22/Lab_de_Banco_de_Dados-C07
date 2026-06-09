package dao;
import model.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {
    public void inserir(Paciente p) {
        String sql = "INSERT INTO paciente (nome, cpf, data_nascimento, telefone, genero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getDataNascimento());
            stmt.setString(4, p.getTelefone());
            stmt.setString(5, p.getGenero());
            stmt.executeUpdate();
            System.out.println("Paciente inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_paciente") + " | Nome: " + rs.getString("nome") + " | Gênero: " + rs.getString("genero"));
                } else {
                    System.out.println("Paciente nao encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizar(String cpf, String novoTelefone) {
        String sql = "UPDATE paciente SET telefone = ? WHERE cpf = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoTelefone);
            stmt.setString(2, cpf);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Telefone atualizado.");
            else System.out.println("Paciente nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletar(String cpf) {
        String sql = "DELETE FROM paciente WHERE cpf = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Paciente removido.");
            else System.out.println("Paciente nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}