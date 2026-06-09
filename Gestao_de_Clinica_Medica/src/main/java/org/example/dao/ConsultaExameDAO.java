package org.example.dao;

import org.example.model.ConsultaExame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaExameDAO {
    ConnectionDAO conexao = new ConnectionDAO();

    public void inserirConsultaExame(ConsultaExame consultaExame) {

        Connection conn = conexao.connectionDb();

        String sql = """
            INSERT INTO consultaExame
            (id_consulta, id_exame, data_solicitacao)
            VALUES (?, ?, ?)
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, consultaExame.getId_consulta());
            pst.setInt(2, consultaExame.getId_exame());
            pst.setString(3, consultaExame.getData_solicitacao());

            int linhas = pst.executeUpdate();

            System.out.println("Linhas afetadas: " + linhas);

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar o registro: "
                    + e.getMessage());

        }
    }

    public void listarConsultaExame() {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM consulta_exame";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID da Consulta: " + rs.getInt("id_consulta"));
                System.out.println("ID do Exame: " + rs.getString("id_Exame"));
                System.out.println("Data da Solicitação: " + rs.getString("data_solicitacao"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar os exames das consultas: "
                    + e.getMessage());

        }
    }

    public void buscarConsultaExamePorData(String dataSolicitacao) {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM consulta_exame WHERE data_solicitacao = ?;";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + dataSolicitacao + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID da Consulta: " + rs.getInt("id_consulta"));
                System.out.println("ID do Exame: " + rs.getString("id_exame"));
                System.out.println("Data da Solicitação: " + rs.getString("data_solicitacao"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar registro: "
                    + e.getMessage());

        }
    }

    public void atualizarConsultaExame(ConsultaExame consultaExame) {

        Connection conn = conexao.connectionDb();

        String sql = """
            UPDATE consulta_exame
            SET data_solicitacao = ?
            WHERE id_consulta = ?
            AND id_exame = ?
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, consultaExame.getData_solicitacao());
            pst.setInt(2, consultaExame.getId_consulta());
            pst.setInt(3, consultaExame.getId_exame());

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                System.out.println("Registro atualizado com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar o registro: "
                    + e.getMessage());

        }
    }

    public void excluirConsultaExame(int id_consulta, int id_exame) {

        Connection conn = conexao.connectionDb();

        String sql = "DELETE FROM consulta_exame WHERE id_consulta = ? AND id_exame = ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, id_consulta);
            pst.setInt(2, id_exame);

            int linhas = pst.executeUpdate();

            if (linhas > 0) {

                System.out.println("Registro removido com sucesso!");

            } else {

                System.out.println("Registro não encontrado.");

            }

        } catch (SQLException e) {

            System.out.println("Erro ao excluir registro: "
                    + e.getMessage());

        }
    }
}
