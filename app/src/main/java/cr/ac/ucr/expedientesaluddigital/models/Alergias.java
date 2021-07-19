package cr.ac.ucr.expedientesaluddigital.models;

import java.util.List;

public class Alergias {
    private static List<Alergia> alergias;
    public Alergias(){

    }

    public static List<Alergia> getInstance(List<Alergia> a){
        if(alergias==null){
            alergias=a;
        }
        return alergias;
    }
}
