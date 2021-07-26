package cr.ac.ucr.expedientesaluddigital.models;

import java.util.List;

public class Vacunas {
    private static List<Vacuna> vacunas;
    public Vacunas(){

    }

    public static List<Vacuna> getVacunas() {
        return vacunas;
    }

    public static void setVacunas(List<Vacuna> vacunas) {
        Vacunas.vacunas = vacunas;
    }
}
