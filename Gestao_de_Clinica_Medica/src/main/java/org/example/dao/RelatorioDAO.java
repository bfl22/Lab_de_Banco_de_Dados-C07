package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioDAO extends ConnectionDAO {

    // JOIN 1 - Paciente + Prontuário
    public void relatorioProntuarios() {

        String sql =
                "SELECT p.nome, p.cpf, pr.historico_geral, pr.data_abertura " +
                        "FROM paciente p " +
                        "INNER JOIN prontuario pr " +
                        "ON p.id_paciente = pr.id_paciente";

        try {

            Connection conn = connectionDb();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            System.out.println("\n===== RELATÓRIO DE PRONTUÁRIOS =====");

            while (rs.next()) {

                System.out.println("----------------------------------");
                System.out.println("Paciente: "
                        + rs.getString("nome"));
                System.out.println("CPF: "
                        + rs.getString("cpf"));
                System.out.println("Histórico: "
                        + rs.getString("historico_geral"));
                System.out.println("Data de Abertura: "
                        + rs.getString("data_abertura"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao gerar relatório: "
                    + e.getMessage());
        }
    }

    // JOIN 2 - Utilizando a VIEW consultas_incompletas
    public void relatorioConsultasIncompletas() {

        String sql =
                "SELECT * FROM consultas_incompletas";

        try {

            Connection conn = connectionDb();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            System.out.println("\n===== CONSULTAS INCOMPLETAS =====");

            while (rs.next()) {

                System.out.println("----------------------------------");
                System.out.println("Paciente: "
                        + rs.getString("Paciente"));
                System.out.println("Médico: "
                        + rs.getString("Médico"));
                System.out.println("Especialidade: "
                        + rs.getString("Especialidade"));
                System.out.println("Data da Consulta: "
                        + rs.getString("Data da Consulta"));
                System.out.println("Status: "
                        + rs.getString("Status da Consulta"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao gerar relatório: "
                    + e.getMessage());
        }
    }

    // JOIN 3 - Consulta + Consulta_Exame + Exame + Paciente
    public void relatorioExamesCompletos() {

        String sql =
                "SELECT p.nome AS paciente, " +
                        "c.data_consulta, " +
                        "e.nome_exame, " +
                        "e.resultado " +
                        "FROM consulta_exame ce " +
                        "INNER JOIN consulta c " +
                        "ON ce.id_consulta = c.id_consulta " +
                        "INNER JOIN exame e " +
                        "ON ce.id_exame = e.id_exame " +
                        "INNER JOIN paciente p " +
                        "ON c.id_paciente = p.id_paciente";

        try {

            Connection conn = connectionDb();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            System.out.println("\n===== HISTÓRICO DE EXAMES =====");

            while (rs.next()) {

                System.out.println("----------------------------------");
                System.out.println("Paciente: "
                        + rs.getString("paciente"));
                System.out.println("Data da Consulta: "
                        + rs.getString("data_consulta"));
                System.out.println("Exame: "
                        + rs.getString("nome_exame"));
                System.out.println("Resultado: "
                        + rs.getString("resultado"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao gerar relatório: "
                    + e.getMessage());
        }
    }
}