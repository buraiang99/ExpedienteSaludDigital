package cr.ac.ucr.expedientesaluddigital.models;

import java.util.List;

public class Vacunas {
    private static List<Vacuna> vacunas;
    public Vacunas(){

    }

    public static List<Vacuna> getInstance(List<Vacuna> v){
        if(vacunas==null){
            vacunas=v;
        }
        return vacunas;
    }
}
