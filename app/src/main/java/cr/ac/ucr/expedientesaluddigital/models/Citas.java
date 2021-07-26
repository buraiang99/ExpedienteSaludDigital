package cr.ac.ucr.expedientesaluddigital.models;

import java.util.List;

public class Citas {
    private static List<Cita> citas;
    public Citas(){

    }

    public static List<Cita> getCitas() {
        return citas;
    }

    public static void setCitas(List<Cita> citas) {
        Citas.citas = citas;
    }
}
