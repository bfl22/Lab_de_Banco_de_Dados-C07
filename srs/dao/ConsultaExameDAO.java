package dao;
import model.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaExameDAO {
    public void inserir(ConsultaExame ce) {
        String sql = "INSERT INTO consulta_exame (id_consulta, id_exame, data_solicitacao) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ce.getIdConsulta());
            stmt.setInt(2, ce.getIdExame());
            stmt.setString(3, ce.getDataSolicitacao());
            stmt.executeUpdate();
            System.out.println("Vinculo consulta e exame criado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarTodos() {
        String sql = "SELECT * FROM consulta_exame";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID Consulta: " + rs.getInt("id_consulta") + " | ID Exame: " + rs.getInt("id_exame") + " | Solicitacao: " + rs.getDate("data_solicitacao"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizarDataSolicitacao(int idConsulta, int idExame, String novaData) {
        String sql = "UPDATE consulta_exame SET data_solicitacao = ? WHERE id_consulta = ? AND id_exame = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novaData);
            stmt.setInt(2, idConsulta);
            stmt.setInt(3, idExame);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Data de solicitacao atualizada.");
            else System.out.println("Vinculo nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletar(int idConsulta, int idExame) {
        String sql = "DELETE FROM consulta_exame WHERE id_consulta = ? AND id_exame = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idConsulta);
            stmt.setInt(2, idExame);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) System.out.println("Vinculo consulta e exame deletado.");
            else System.out.println("Vinculo nao encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}