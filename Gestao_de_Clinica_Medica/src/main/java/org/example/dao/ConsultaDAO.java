package org.example.dao;

import org.example.model.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaDAO {
    ConnectionDAO conexao = new ConnectionDAO();

    public void inserirConsulta(Consulta consulta) {

        Connection conn = conexao.connectionDb();

        String sql = """
            INSERT INTO consulta
            (data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, consulta.getData_consulta());
            pst.setString(2, consulta.getHora_consulta());
            pst.setString(3, consulta.getValor_pago());
            pst.setString(4, consulta.getQueixa_principal());
            pst.setString(5, consulta.getStatus());
            pst.setInt(6, consulta.getId_paciente());
            pst.setInt(7, consulta.getId_medico());

            int linhas = pst.executeUpdate();

            System.out.println("Linhas afetadas: " + linhas);

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar consulta: "
                    + e.getMessage());

        }
    }

    public void listarConsultas() {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM consulta";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_consulta"));
                System.out.println("Data da Consulta: " + rs.getString("data_consulta"));
                System.out.println("Hora da Consulta: " + rs.getString("hora_consulta"));
                System.out.println("Valor Pago: " + rs.getString("valor_pago"));
                System.out.println("Queixa Principal: " + rs.getString("queixa_principal"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("ID do Paciente: " + rs.getString("id_paciente"));
                System.out.println("ID do Médico: " + rs.getString("id_medico"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar consultas: "
                    + e.getMessage());

        }
    }

    public void buscarConsultaPorStatus(String status) {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM consulta WHERE status = ?;";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + status + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_consulta"));
                System.out.println("Data da Consulta: " + rs.getString("data_consulta"));
                System.out.println("Hora da Consulta: " + rs.getString("hora_consulta"));
                System.out.println("Valor Pago: " + rs.getString("valor_pago"));
                System.out.println("Queixa Principal: " + rs.getString("queixa_principal"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("ID do Paciente: " + rs.getInt("id_paciente"));
                System.out.println("ID do Médico: " + rs.getInt("id_medico"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar consulta: "
                    + e.getMessage());

        }
    }

    public void atualizarConsulta(Consulta consulta) {

        Connection conn = conexao.connectionDb();

        String sql = """
            UPDATE consulta
            SET data_consulta = ?,
                hora_consulta = ?,
                valor_pago = ?,
                queixa_principal = ?,
                status = ?,
                id_paciente = ?,
                id_medico = ?
            WHERE id_consulta = ?
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, consulta.getData_consulta());
            pst.setString(2, consulta.getHora_consulta());
            pst.setString(3, consulta.getValor_pago());
            pst.setString(4, consulta.getQueixa_principal());
            pst.setString(5, consulta.getStatus());
            pst.setInt(6, consulta.getId_paciente());
            pst.setInt(7, consulta.getId_medico());
            pst.setInt(8, consulta.getId_consulta());

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                System.out.println("Consulta atualizada com sucesso!");
            } else {
                System.out.println("Consulta não encontrada.");
            }

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar consulta: "
                    + e.getMessage());
        }
    }

    public void excluirConsulta(int idConsulta) {

        Connection conn = conexao.connectionDb();

        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idConsulta);

            int linhas = pst.executeUpdate();

            if (linhas > 0) {

                System.out.println("Consulta removida com sucesso!");

            } else {

                System.out.println("Consulta não encontrada.");

            }

        } catch (SQLException e) {

            System.out.println("Erro ao excluir consulta: "
                    + e.getMessage());

        }
    }
}
