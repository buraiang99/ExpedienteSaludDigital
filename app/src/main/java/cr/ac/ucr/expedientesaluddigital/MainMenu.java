package cr.ac.ucr.expedientesaluddigital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.expedientesaluddigital.models.Paciente;

public class MainMenu extends AppCompatActivity {
    Paciente p= Paciente.getInstance(null);
    TextView nombretv;
    Button citasbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        nombretv=findViewById(R.id.nombre_tv);
        nombretv.setText("Bienvenido "+p.getNombre());
        citasbtn=findViewById(R.id.citas_btn);
        citasbtn.setOnClickListener(v->{
            Intent intentInicarSesion=new Intent(getApplicationContext(), LoginPaciente.class);
            startActivity(intentInicarSesion);
        });
    }
}
