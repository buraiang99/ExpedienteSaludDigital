package cr.ac.ucr.expedientesaluddigital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.expedientesaluddigital.models.Paciente;

public class MainMenu extends AppCompatActivity {
    TextView nombretv;
    Button citasbtn;
    Button alergiasbtn;
    Button vacunasbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        nombretv=findViewById(R.id.nombre_tv);
        nombretv.setText("Bienvenido "+Paciente.getInstance(null).getNombre());
        citasbtn=findViewById(R.id.citas_btn);
        alergiasbtn=findViewById(R.id.alergias_btn);
        vacunasbtn=findViewById(R.id.vacunas_btn);
        citasbtn.setOnClickListener(v->{
            Intent intentInicarSesion=new Intent(getApplicationContext(), ListaCitas.class);
            startActivity(intentInicarSesion);
        });
        alergiasbtn.setOnClickListener(v->{
            Intent intentInicarSesion=new Intent(getApplicationContext(), ListaAlergias.class);
            startActivity(intentInicarSesion);
        });
        vacunasbtn.setOnClickListener(v->{
            Intent intentInicarSesion=new Intent(getApplicationContext(), ListaVacunas.class);
            startActivity(intentInicarSesion);
        });
    }
}
