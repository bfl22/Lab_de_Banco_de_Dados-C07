package model;
public class Prontuario {
    private int idProntuario;
    private String historicoGeral;
    private String dataAbertura;
    private int idPaciente;

    public Prontuario() {}

    public Prontuario(int idProntuario, String historicoGeral, String dataAbertura, int idPaciente) {
        this.idProntuario = idProntuario;
        this.historicoGeral = historicoGeral;
        this.dataAbertura = dataAbertura;
        this.idPaciente = idPaciente;
    }

    public int getIdProntuario() { return idProntuario; }
    public void setIdProntuario(int idProntuario) { this.idProntuario = idProntuario; }
    public String getHistoricoGeral() { return historicoGeral; }
    public void setHistoricoGeral(String historicoGeral) { this.historicoGeral = historicoGeral; }
    public String getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(String dataAbertura) { this.dataAbertura = dataAbertura; }
    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
}