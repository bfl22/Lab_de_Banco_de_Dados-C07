package org.example.model;

public class ConsultaExame {
    private int id_consulta;
    private int id_exame;
    private String data_solicitacao;

    public ConsultaExame()
    {

    }

    public ConsultaExame(int id_consulta, int id_exame, String data_solicitacao) {
        this.id_consulta = id_consulta;
        this.id_exame = id_exame;
        this.data_solicitacao = data_solicitacao;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public int getId_exame() {
        return id_exame;
    }

    public void setId_exame(int id_exame) {
        this.id_exame = id_exame;
    }

    public String getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(String data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
    }
}
