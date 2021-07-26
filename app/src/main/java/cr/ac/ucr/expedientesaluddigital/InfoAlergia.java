package cr.ac.ucr.expedientesaluddigital;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.expedientesaluddigital.models.Alergia;
import cr.ac.ucr.expedientesaluddigital.models.Alergias;

public class InfoAlergia extends AppCompatActivity {
    public TextView nombretv;
    public TextView descripciontv;
    public TextView fechaDiagnosticotv;
    public TextView medicamentostv;
    private Alergia c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_alergia);
        fechaDiagnosticotv=findViewById(R.id.info_fecha_diagnostico_alergia_tv);
        nombretv=findViewById(R.id.info_nombre_alergia_tv);
        descripciontv=findViewById(R.id.info_descripcion_alergia_tv);
        medicamentostv=findViewById(R.id.info_medicamentos_alergia_tv);
        Bundle extra = getIntent().getExtras();
        int pos=extra.getInt("idAlergia");
        c= Alergias.getAlergias().get(pos);
        nombretv.setText(c.getNombreAlergia());
        descripciontv.setText(c.getDescripcion());
        medicamentostv.setText(c.getMedicamentos());
        fechaDiagnosticotv.setText(c.getFechaDiagnostico());
    }
}
