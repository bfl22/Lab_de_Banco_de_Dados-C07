package model;
public class Exame {
    private int idExame;
    private String nomeExame;
    private String descricao;
    private String resultado;

    public Exame() {}

    public Exame(int idExame, String nomeExame, String descricao, String resultado) {
        this.idExame = idExame;
        this.nomeExame = nomeExame;
        this.descricao = descricao;
        this.resultado = resultado;
    }

    public int getIdExame() { return idExame; }
    public void setIdExame(int idExame) { this.idExame = idExame; }
    public String getNomeExame() { return nomeExame; }
    public void setNomeExame(String nomeExame) { this.nomeExame = nomeExame; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
}