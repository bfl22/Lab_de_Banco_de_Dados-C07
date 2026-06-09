package dao;
import model.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExameDAO {
    public void inserir(Exame ex) {
        String sql = "INSERT INTO exame (nome_exame, descricao, resultado) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ex.getNomeExame());
            stmt.setString(2, ex.getDescricao());
            stmt.setString(3, ex.getResultado());
            stmt.executeUpdate();
            System.out.println("Exame registrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void buscarPorNome(String nomeExame) {
        String sql = "SELECT * FROM exame WHERE nome_exame LIKE ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nomeExame + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_exame") + " | Nome: " + rs.getString("nome_exame") + " | Resultado: " + rs.getString("resultado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizarResultado(int idExame, String novoResultado) {
        String sql = "UPDATE exame SET resultado = ? WHERE id_exame = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoResultado);
            stmt.setInt(2, idExame);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Resultado do exame atualizado.");
            else System.out.println("Exame nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletar(int idExame) {
        String sql = "DELETE FROM exame WHERE id_exame = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idExame);
            int dbLinhas = stmt.executeUpdate();
            if (dbLinhas > 0) System.out.println("Exame removido.");
            else System.out.println("Exame nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}