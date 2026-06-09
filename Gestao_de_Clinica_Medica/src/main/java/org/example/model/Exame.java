package org.example.model;

public class Exame {
    private int idExame;
    private String nome_exame;
    private String descricao;
    private String resultado;

    public Exame()
    {

    }

    public Exame(int idExame, String nome_exame, String descricao, String resultado) {
        this.idExame = idExame;
        this.nome_exame = nome_exame;
        this.descricao = descricao;
        this.resultado = resultado;
    }

    public int getIdExame() {
        return idExame;
    }

    public void setIdExame(int idExame) {
        this.idExame = idExame;
    }

    public String getNome_exame() {
        return nome_exame;
    }

    public void setNome_exame(String nome_exame) {
        this.nome_exame = nome_exame;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
