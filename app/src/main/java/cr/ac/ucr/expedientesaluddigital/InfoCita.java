package cr.ac.ucr.expedientesaluddigital;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.expedientesaluddigital.models.Cita;
import cr.ac.ucr.expedientesaluddigital.models.Citas;

public class InfoCita extends AppCompatActivity {
    public TextView fechatv;
    public TextView horatv;
    public TextView centroSaludtv;
    public TextView especialidadtv;
    public TextView doctortv;
    public TextView diagnosticotv;
    private Cita c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cita);
        fechatv=findViewById(R.id.info_fecha_tv);
        horatv=findViewById(R.id.info_hora_tv);
        centroSaludtv=findViewById(R.id.info_centrosalud_tv);
        especialidadtv=findViewById(R.id.info_especialidad_tv);
        doctortv=findViewById(R.id.info_doctor_tv);
        diagnosticotv=findViewById(R.id.info_diagnostico_tv);
        Bundle extra = getIntent().getExtras();
        int pos=extra.getInt("idCita");
        c= Citas.getCitas().get(pos);
        fechatv.setText(c.getFecha());
        horatv.setText(c.getHora());
        centroSaludtv.setText(c.getCentroSalud());
        especialidadtv.setText(c.getEspecialidad());
        doctortv.setText(c.getNombreDoctor()+" "+c.getApellidosDoctor());
        diagnosticotv.setText(c.getDiagnostico());
    }
}
