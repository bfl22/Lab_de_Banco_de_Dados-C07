package org.example.dao;

import org.example.model.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoDAO {
    ConnectionDAO conexao = new ConnectionDAO();

    public void inserirMedico (Medico medico) {

        Connection conn = conexao.connectionDb();

        String sql = """
            INSERT INTO medico
            (nome, crm, especialidade, telefone)
            VALUES (?, ?, ?, ?)
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, medico.getNome());
            pst.setString(2, medico.getCrm());
            pst.setString(3, medico.getEspecialidade());
            pst.setString(4, medico.getTelefone());

            int linhas = pst.executeUpdate();

            System.out.println("Linhas afetadas: " + linhas);

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar paciente: "
                    + e.getMessage());

        }
    }

    public void listarMedicos() {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM medico";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_medico"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CRM: " + rs.getString("crm"));
                System.out.println("Especialidade: " + rs.getString("especialidade"));
                System.out.println("Telefone: " + rs.getString("telefone"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar medicos: "
                    + e.getMessage());
        }
    }

    public void buscarMedicoPorCrm(String crm) {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM medico WHERE crm LIKE ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + crm + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_medico"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CRM: " + rs.getString("crm"));
                System.out.println("Especialidade: " + rs.getString("especialidade"));
                System.out.println("Telefone: " + rs.getString("telefone"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar medico: "
                    + e.getMessage());

        }
    }

    public void atualizarMedico(Medico medico) {

        Connection conn = conexao.connectionDb();

        String sql = """
            UPDATE medico
            SET nome = ?,
                crm = ?,
                especialidade = ?,
                telefone = ?
            WHERE id_medico = ?
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, medico.getNome());
            pst.setString(2, medico.getCrm());
            pst.setString(3, medico.getEspecialidade());
            pst.setString(4, medico.getTelefone());
            pst.setInt(6, medico.getIdMedico());

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                System.out.println("Medico atualizado com sucesso!");
            } else {
                System.out.println("Medico não encontrado.");
            }

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar medico: "
                    + e.getMessage());

        }
    }

    public void excluirMedico(int idMedico) {

        Connection conn = conexao.connectionDb();

        String sql = "DELETE FROM medico WHERE id_medico = ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idMedico);

            int linhas = pst.executeUpdate();

            if (linhas > 0) {

                System.out.println("Medico removido com sucesso!");

            } else {

                System.out.println("Medico não encontrado.");

            }

        } catch (SQLException e) {

            System.out.println("Erro ao excluir medico: "
                    + e.getMessage());

        }
    }
}
