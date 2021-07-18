package cr.ac.ucr.expedientesaluddigital.models;

import java.util.List;

public class Citas {
    private static List<Cita> citas;
    public Citas(){

    }

    public static List<Cita> getInstance(List<Cita> c){
        if(citas==null){
            citas=c;
        }
        return citas;
    }
}
