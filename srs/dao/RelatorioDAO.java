package dao;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioDAO {
    public void relatorioProntuarios() {
        String sql = "SELECT p.nome, p.cpf, pr.historico_geral, pr.data_abertura FROM paciente p INNER JOIN prontuario pr ON p.id_paciente = pr.id_paciente";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- RELATORIO DE PRONTUARIOS (JOIN 1) ---");
            while (rs.next()) {
                System.out.println("Paciente: " + rs.getString("nome") + " | CPF: " + rs.getString("cpf") + " | Historico: " + rs.getString("historico_geral"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void relatorioConsultasIncompletas() {
        String sql = "SELECT * FROM consultas_incompletas";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- PAINEL DE CONSULTAS EM ABERTO (JOIN 2 - VIEW) ---");
            while (rs.next()) {
                System.out.println("Paciente: " + rs.getString("Paciente") + " | Medico: " + rs.getString("Medico") + " | Status: " + rs.getString("Status da Consulta"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void relatorioExamesCompletos() {
        String sql = "SELECT p.nome AS paciente, c.data_consulta, e.nome_exame, e.resultado FROM consulta_exame ce INNER JOIN consulta c ON ce.id_consulta = c.id_consulta INNER JOIN exame e ON ce.id_exame = e.id_exame INNER JOIN paciente p ON c.id_paciente = p.id_paciente";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- HISTORICO LABORATORIAL COMPLETO (JOIN 3) ---");
            while (rs.next()) {
                System.out.println("Paciente: " + rs.getString("paciente") + " | Exame: " + rs.getString("nome_exame") + " | Resultado: " + rs.getString("resultado"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}