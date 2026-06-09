package dao;
import model.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClinicaDAO {

    public void inserirPaciente(String nome, String cpf, String dataNascimento, String telefone, String genero) {
        String sql = "INSERT INTO paciente (nome, cpf, data_nascimento, telefone, genero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, dataNascimento);
            stmt.setString(4, telefone);
            stmt.setString(5, genero);
            stmt.executeUpdate();
            System.out.println("\n[Sucesso] Paciente inserido no banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir paciente: " + e.getMessage());
        }
    }

    public void buscarPacientePorCpf(String cpf) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("\n--- DADOS DO PACIENTE ---");
                System.out.println("ID: " + rs.getInt("id_paciente"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Nascimento: " + rs.getDate("data_nascimento"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Gênero: " + rs.getString("genero"));
            } else {
                System.out.println("\nPaciente não encontrado com o CPF informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
    }

    public void atualizarTelefonePaciente(String cpf, String novoTelefone) {
        String sql = "UPDATE paciente SET telefone = ? WHERE cpf = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoTelefone);
            stmt.setString(2, cpf);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("\n[Sucesso] Telefone atualizado com sucesso!");
            } else {
                System.out.println("\nNenhum paciente encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    public void deletarPaciente(String cpf) {
        String sql = "DELETE FROM paciente WHERE cpf = ?";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("\n[Sucesso] Paciente removido do banco de dados!");
            } else {
                System.out.println("\nNenhum paciente encontrado para exclusão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar paciente (Verifique se ele tem consultas atreladas): " + e.getMessage());
        }
    }

    public void relatorioProntuarios() {
        String sql = "SELECT p.nome, p.cpf, pr.historico_geral, pr.data_abertura " +
                     "FROM paciente p " +
                     "INNER JOIN prontuario pr ON p.id_paciente = pr.id_paciente";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- RELATÓRIO DE PRONTUÁRIOS ---");
            while (rs.next()) {
                System.out.println("Paciente: " + rs.getString("nome") + " | Abertura: " + rs.getDate("data_abertura") + " | Histórico: " + rs.getString("historico_geral"));
            }
        } catch (SQLException e) {
            System.out.println("Erro no JOIN de Prontuários: " + e.getMessage());
        }
    }

    public void relatorioConsultasEmAberto() {
        String sql = "SELECT * FROM consultas_incompletas";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- CONSULTAS EM ABERTO ---");
            while (rs.next()) {
                System.out.println("Paciente: " + rs.getString("Paciente") + " | Médico: " + rs.getString("Médico") + " (" + rs.getString("Especialidade") + ") | Status: " + rs.getString("Status da Consulta"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Consultas em Aberto: " + e.getMessage());
        }
    }

    public void relatorioExamesCompletos() {
        String sql = "SELECT p.nome AS paciente, c.data_consulta, e.nome_exame, e.resultado " +
                     "FROM consulta_exame ce " +
                     "INNER JOIN consulta c ON ce.id_consulta = c.id_consulta " +
                     "INNER JOIN exame e ON ce.id_exame = e.id_exame " +
                     "INNER JOIN paciente p ON c.id_paciente = p.id_paciente";
        try (Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- RELATÓRIO DE EXAMES REALIZADOS ---");
            while (rs.next()) {
                System.out.println("Data: " + rs.getDate("data_consulta") + " | Paciente: " + rs.getString("paciente") + " | Exame: " + rs.getString("nome_exame") + " | Resultado: " + rs.getString("resultado"));
            }
        } catch (SQLException e) {
            System.out.println("Erro no JOIN de Exames: " + e.getMessage());
        }
    }
}