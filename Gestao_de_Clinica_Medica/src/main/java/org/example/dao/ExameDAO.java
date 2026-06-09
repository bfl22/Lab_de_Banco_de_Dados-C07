package org.example.dao;

import org.example.model.Exame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExameDAO {
    ConnectionDAO conexao = new ConnectionDAO();

    public void inserirExame(Exame exame) {

        Connection conn = conexao.connectionDb();

        String sql = """
            INSERT INTO exame
            (nome_exame, descricao, resultado)
            VALUES (?, ?, ?)
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, exame.getNome_exame());
            pst.setString(2, exame.getDescricao());
            pst.setString(3, exame.getResultado());

            int linhas = pst.executeUpdate();

            System.out.println("Linhas afetadas: " + linhas);

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar exame: "
                    + e.getMessage());

        }
    }

    public void listarExames() {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM exame";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_exame"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Descrição: " + rs.getString("descricao"));
                System.out.println("Resultado: " + rs.getString("resultado"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar exames: "
                    + e.getMessage());

        }
    }

    public void buscarExamePorNome(String nome) {

        Connection conn = conexao.connectionDb();

        String sql = "SELECT * FROM exame WHERE nome_exame LIKE ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + nome + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");
                System.out.println("ID: " + rs.getInt("id_exame"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Descrição: " + rs.getString("descricao"));
                System.out.println("Resultado: " + rs.getString("resultado"));
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar exame: "
                    + e.getMessage());

        }
    }

    public void atualizarExame(Exame exame) {

        Connection conn = conexao.connectionDb();

        String sql = """
            UPDATE exame
            SET nome_exame = ?,
                descricao = ?,
                resultado = ?
            WHERE id_exame = ?
            """;

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, exame.getNome_exame());
            pst.setString(2, exame.getDescricao());
            pst.setString(3, exame.getResultado());
            pst.setInt(4, exame.getIdExame());

            int linhas = pst.executeUpdate();

            if (linhas > 0) {
                System.out.println("Exame atualizado com sucesso!");
            } else {
                System.out.println("Exame não encontrado.");
            }

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar exame: "
                    + e.getMessage());

        }
    }

    public void excluirExame(int idExame) {

        Connection conn = conexao.connectionDb();

        String sql = "DELETE FROM exame WHERE id_exame = ?";

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idExame);

            int linhas = pst.executeUpdate();

            if (linhas > 0) {

                System.out.println("Exame removido com sucesso!");

            } else {

                System.out.println("Exame não encontrado.");

            }

        } catch (SQLException e) {

            System.out.println("Erro ao excluir exame: "
                    + e.getMessage());

        }
    }
}
