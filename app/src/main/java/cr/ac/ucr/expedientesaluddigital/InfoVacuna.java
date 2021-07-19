package cr.ac.ucr.expedientesaluddigital;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.expedientesaluddigital.models.Vacuna;
import cr.ac.ucr.expedientesaluddigital.models.Vacunas;

public class InfoVacuna extends AppCompatActivity {
    public TextView nombretv;
    public TextView descripciontv;
    public TextView fechaAplicaciontv;
    public TextView fechaProximatv;
    public TextView centroSaludtv;
    private Vacuna c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_vacuna);
        fechaAplicaciontv=findViewById(R.id.info_fechaaplicacion_vacuna_tv);
        nombretv=findViewById(R.id.info_nombre_vacuna_tv);
        descripciontv=findViewById(R.id.info_descripcion_vacuna_tv);
        fechaProximatv=findViewById(R.id.info_fechaproxima_vacuna_tv);
        centroSaludtv=findViewById(R.id.info_centrosalud_vacuna_tv);
        Bundle extra = getIntent().getExtras();
        int pos=extra.getInt("idVacuna");
        c= Vacunas.getInstance(null).get(pos);
        nombretv.setText(c.getNombreVacuna());
        descripciontv.setText(c.getDescripcion());
        fechaAplicaciontv.setText(c.getFechaAplicacion());
        fechaProximatv.setText(c.getFechaProxima());
        centroSaludtv.setText(c.getCentroSalud());
    }
}
