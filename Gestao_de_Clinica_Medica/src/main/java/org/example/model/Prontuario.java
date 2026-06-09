package org.example.model;

public class Prontuario {
    private int IdProntuario;
    private String historico_geral;
    private String data_abertura;
    private int idPaciente;

    public Prontuario()
    {

    }

    public Prontuario(int idProntuario, String historico_geral, String data_abertura, int idPaciente) {
        IdProntuario = idProntuario;
        this.historico_geral = historico_geral;
        this.data_abertura = data_abertura;
        this.idPaciente = idPaciente;
    }

    public int getIdProntuario() {
        return IdProntuario;
    }

    public void setIdProntuario(int idProntuario) {
        IdProntuario = idProntuario;
    }

    public String getHistorico_geral() {
        return historico_geral;
    }

    public void setHistorico_geral(String historico_geral) {
        this.historico_geral = historico_geral;
    }

    public String getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(String data_abertura) {
        this.data_abertura = data_abertura;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
