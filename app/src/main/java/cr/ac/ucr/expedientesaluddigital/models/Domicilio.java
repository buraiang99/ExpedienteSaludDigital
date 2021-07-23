package cr.ac.ucr.expedientesaluddigital.models;

public class Domicilio {
    private int idProvincia;
    private  int idCanton;
    private int idDistrito;
    private String detalles;

    public Domicilio() {
    }

    public Domicilio(int idProvincia, int idCanton, int idDistrito, String detalles) {
        this.idProvincia = idProvincia;
        this.idCanton = idCanton;
        this.idDistrito = idDistrito;
        this.detalles = detalles;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public int getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(int idCanton) {
        this.idCanton = idCanton;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
