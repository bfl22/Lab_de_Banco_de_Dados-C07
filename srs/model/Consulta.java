package model;
public class Consulta {
    private int idConsulta;
    private String dataConsulta;
    private String horaConsulta;
    private double valorPago;
    private String queixaPrincipal;
    private String status;
    private int idPaciente;
    private int idMedico;
    private String categoriaConsulta;

    public Consulta() {}

    public Consulta(int idConsulta, String dataConsulta, String horaConsulta, double valorPago, String queixaPrincipal, String status, int idPaciente, int idMedico, String categoriaConsulta) {
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.valorPago = valorPago;
        this.queixaPrincipal = queixaPrincipal;
        this.status = status;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.categoriaConsulta = categoriaConsulta;
    }

    public int getIdConsulta() { return idConsulta; }
    public void setIdConsulta(int idConsulta) { this.idConsulta = idConsulta; }
    public String getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(String dataConsulta) { this.dataConsulta = dataConsulta; }
    public String getHoraConsulta() { return horaConsulta; }
    public void setHoraConsulta(String horaConsulta) { this.horaConsulta = horaConsulta; }
    public double getValorPago() { return valorPago; }
    public void setValorPago(double valorPago) { this.valorPago = valorPago; }
    public String getQueixaPrincipal() { return queixaPrincipal; }
    public void setQueixaPrincipal(String queixaPrincipal) { this.queixaPrincipal = queixaPrincipal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
    public int getIdMedico() { return idMedico; }
    public void setIdMedico(int idMedico) { this.idMedico = idMedico; }
    public String getCategoriaConsulta() { return categoriaConsulta; }
    public void setCategoriaConsulta(String categoriaConsulta) { this.categoriaConsulta = categoriaConsulta; }
}