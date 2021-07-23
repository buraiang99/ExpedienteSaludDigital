package cr.ac.ucr.expedientesaluddigital.models;

public class RespuestaDomicilio {
    private String cedula;
    private String provinciaNombre;
    private String cantonNombre;
    private String distritoNombre;
    private String detalles;

    public RespuestaDomicilio() {
    }

    public RespuestaDomicilio(String cedula, String provinciaNombre, String cantonNombre, String distritoNombre, String detalles) {
        this.cedula = cedula;
        this.provinciaNombre = provinciaNombre;
        this.cantonNombre = cantonNombre;
        this.distritoNombre = distritoNombre;
        this.detalles = detalles;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getProvinciaNombre() {
        return provinciaNombre;
    }

    public void setProvinciaNombre(String provinciaNombre) {
        this.provinciaNombre = provinciaNombre;
    }

    public String getCantonNombre() {
        return cantonNombre;
    }

    public void setCantonNombre(String cantonNombre) {
        this.cantonNombre = cantonNombre;
    }

    public String getDistritoNombre() {
        return distritoNombre;
    }

    public void setDistritoNombre(String distritoNombre) {
        this.distritoNombre = distritoNombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
