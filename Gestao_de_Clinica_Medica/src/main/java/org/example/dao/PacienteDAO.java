package org.example.dao;

import org.example.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {
    ConnectionDAO conexao = new ConnectionDAO();

    public void inserirPaciente(Paciente paciente) {

        Connection conn = conexao.connectionDb();

        String sql = """
            INSERT INTO paciente
            (nome, cpf, data_nascimento, telefone, genero)
            VALUES (?, ?, ?, ?, ?)
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, paciente.getNome());
            pst.setString(2, paciente.getCpf());
            pst.setString(3, paciente.getDataNascimento());
            pst.setString(4, paciente.getTelefone());
            pst.setString(5, String.valueOf(paciente.getGenero()));

            int linhas = pst.executeUpdate();

            System.out.println("Linhas afetadas: " + linhas);

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar paciente: "
                    + e.getMessage());

        }
    }

    public void listarPacientes() {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM paciente";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_paciente"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Nascimento: " + rs.getString("data_nascimento"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Gênero: " + rs.getString("genero"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar pacientes: "
                    + e.getMessage());

        }
    }

    public void buscarPacientePorCpf(String cpf) {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM paciente WHERE cpf LIKE ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + cpf + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_paciente"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Nascimento: " + rs.getString("data_nascimento"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Gênero: " + rs.getString("genero"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar paciente: "
                    + e.getMessage());

        }
    }

    public void atualizarPaciente(Paciente paciente) {

        Connection conn = conexao.connectionDb();

        String sql = """
            UPDATE paciente
            SET nome = ?,
                cpf = ?,
                data_nascimento = ?,
                telefone = ?,
                genero = ?
            WHERE id_paciente = ?
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, paciente.getNome());
            pst.setString(2, paciente.getCpf());
            pst.setString(3, paciente.getDataNascimento());
            pst.setString(4, paciente.getTelefone());
            pst.setString(5, String.valueOf(paciente.getGenero()));
            pst.setInt(6, paciente.getIdPaciente());

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                System.out.println("Paciente atualizado com sucesso!");
            } else {
                System.out.println("Paciente não encontrado.");
            }

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar paciente: "
                    + e.getMessage());

        }
    }

    public void excluirPaciente(int idPaciente) {

        Connection conn = conexao.connectionDb();

        String sql = "DELETE FROM paciente WHERE id_paciente = ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idPaciente);

            int linhas = pst.executeUpdate();

            if (linhas > 0) {

                System.out.println("Paciente removido com sucesso!");

            } else {

                System.out.println("Paciente não encontrado.");

            }

        } catch (SQLException e) {

            System.out.println("Erro ao excluir paciente: "
                    + e.getMessage());

        }
    }
}
