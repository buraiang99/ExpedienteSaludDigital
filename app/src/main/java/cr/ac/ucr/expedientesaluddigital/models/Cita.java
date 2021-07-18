package cr.ac.ucr.expedientesaluddigital.models;

public class Cita {
    private int iD_Cita;
    private String cedulaPaciente;
    private int centroSalud;
    private int especialidad;
    private String fecha;
    private String hora;
    private String diagnostico;
    private String nombreDoctor;
    private String apellidoDoctor;
    public Cita(){

    }

    public int getiD_Cita() {
        return iD_Cita;
    }

    public void setiD_Cita(int iD_Cita) {
        this.iD_Cita = iD_Cita;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public int getCentroSalud() {
        return centroSalud;
    }

    public void setCentroSalud(int centroSalud) {
        this.centroSalud = centroSalud;
    }

    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getApellidoDoctor() {
        return apellidoDoctor;
    }

    public void setApellidoDoctor(String apellidoDoctor) {
        this.apellidoDoctor = apellidoDoctor;
    }
}
