package org.example.dao;

import org.example.model.Prontuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProntuarioDAO {
    ConnectionDAO conexao = new ConnectionDAO();

    public void inserirProntuario(Prontuario prontuario) {

        Connection conn = conexao.connectionDb();

        String sql = """
            INSERT INTO prontuario
            (historico_geral, data_abertura, idPaciente)
            VALUES (?, ?, ?)
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, prontuario.getHistorico_geral());
            pst.setString(2, prontuario.getData_abertura());
            pst.setInt(3, prontuario.getIdPaciente());

            int linhas = pst.executeUpdate();

            System.out.println("Linhas afetadas: " + linhas);

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar prontuário: "
                    + e.getMessage());

        }
    }

    public void listarProntuarios() {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM prontuario";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_prontuario"));
                System.out.println("Histórico Geral: " + rs.getString("historico_geral"));
                System.out.println("Data de Abertura: " + rs.getString("data_abertura"));
                System.out.println("ID do Paciente: " + rs.getString("id_paciente"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar prontuários: "
                    + e.getMessage());

        }
    }

    public void buscarProntuarioPorPaciente(int idPaciente)
    {
        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM prontuario WHERE id_paciente = ?;";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idPaciente);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_prontuario"));
                System.out.println("Histórico Geral: " + rs.getString("historico_geral"));
                System.out.println("Data de Abertura: " + rs.getString("data_abertura"));
                System.out.println("ID de Paciente: " + rs.getString("id_paciente"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar prontuario: "
                    + e.getMessage());
        }
    }

    public void atualizarProntuario(Prontuario prontuario) {

        Connection conn = conexao.connectionDb();

        String sql = """
            UPDATE prontuario
            SET historico_geral = ?,
                data_abertura = ?,
                idPaciente = ?
            WHERE id_prontuario = ?
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, prontuario.getHistorico_geral());
            pst.setString(2, prontuario.getData_abertura());
            pst.setInt(3, prontuario.getIdPaciente());
            pst.setInt(4, prontuario.getIdProntuario());

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                System.out.println("Prontuário atualizado com sucesso!");
            } else {
                System.out.println("Prontuário não encontrado.");
            }

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar prontuário: "
                    + e.getMessage());

        }
    }

    public void excluirProntuario(int idProntuario) {

        Connection conn = conexao.connectionDb();

        String sql = "DELETE FROM prontuario WHERE id_prontuario = ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idProntuario);

            int linhas = pst.executeUpdate();

            if (linhas > 0) {

                System.out.println("Prontuário removido com sucesso!");

            } else {

                System.out.println("Prontuário não encontrado.");

            }

        } catch (SQLException e) {

            System.out.println("Erro ao excluir prontuário: "
                    + e.getMessage());

        }
    }
}
