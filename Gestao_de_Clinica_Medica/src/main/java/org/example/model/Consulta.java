package org.example.model;

public class Consulta {
    private int id_consulta;
    private String data_consulta;
    private String hora_consulta;
    private String valor_pago;
    private String queixa_principal;
    private String status;
    private int id_paciente;
    private int id_medico;

    public Consulta()
    {

    }

    public Consulta(int id_consulta, String data_consulta, String hora_consulta, String valor_pago, String queixa_principal, String status, int id_paciente, int id_medico) {
        this.id_consulta = id_consulta;
        this.data_consulta = data_consulta;
        this.hora_consulta = hora_consulta;
        this.valor_pago = valor_pago;
        this.queixa_principal = queixa_principal;
        this.status = status;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getHora_consulta() {
        return hora_consulta;
    }

    public void setHora_consulta(String hora_consulta) {
        this.hora_consulta = hora_consulta;
    }

    public String getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(String valor_pago) {
        this.valor_pago = valor_pago;
    }

    public String getQueixa_principal() {
        return queixa_principal;
    }

    public void setQueixa_principal(String queixa_principal) {
        this.queixa_principal = queixa_principal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }
}
