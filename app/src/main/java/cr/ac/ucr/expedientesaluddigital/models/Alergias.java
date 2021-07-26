package cr.ac.ucr.expedientesaluddigital.models;

import java.util.List;

public class Alergias {
    private static List<Alergia> alergias;
    public Alergias(){

    }

    public static List<Alergia> getAlergias() {
        return alergias;
    }

    public static void setAlergias(List<Alergia> alergias) {
        Alergias.alergias = alergias;
    }
}
