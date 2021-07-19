package cr.ac.ucr.expedientesaluddigital.models;

public class Alergia {
    private int idAlergia;
    private String cedulaPaciente;
    private String nombreAlergia;
    private String descripcion;
    private String fechaDiagnostico;
    private String medicamentos;
    public Alergia(){

    }

    public int getIdAlergia() {
        return idAlergia;
    }

    public void setIdAlergia(int idAlergia) {
        this.idAlergia = idAlergia;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public String getNombreAlergia() {
        return nombreAlergia;
    }

    public void setNombreAlergia(String nombreAlergia) {
        this.nombreAlergia = nombreAlergia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(String fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }
}
