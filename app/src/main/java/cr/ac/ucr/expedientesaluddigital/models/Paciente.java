package cr.ac.ucr.expedientesaluddigital.models;

public class Paciente {
    private String cedula;
    private String nombre;
    private int edad;
    private String tipoSangre;
    private String estadoCivil;
    private String domicilio;
    private String pass;
    private static Paciente paciente;

    public Paciente() {
    }

    public static Paciente getInstance(Paciente p){
        if(paciente==null){
            paciente=p;
        }
        return paciente;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
