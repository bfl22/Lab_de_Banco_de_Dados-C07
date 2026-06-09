package model;
public class ConsultaExame {
    private int idConsulta;
    private int idExame;
    private String dataSolicitacao;

    public ConsultaExame() {}

    public ConsultaExame(int idConsulta, int idExame, String dataSolicitacao) {
        this.idConsulta = idConsulta;
        this.idExame = idExame;
        this.dataSolicitacao = dataSolicitacao;
    }

    public int getIdConsulta() { return idConsulta; }
    public void setIdConsulta(int idConsulta) { this.idConsulta = idConsulta; }
    public int getIdExame() { return idExame; }
    public void setIdExame(int idExame) { this.idExame = idExame; }
    public String getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(String dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
}