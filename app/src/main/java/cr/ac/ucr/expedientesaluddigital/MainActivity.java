package cr.ac.ucr.expedientesaluddigital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button iniciarSesionBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarSesionBtn = findViewById(R.id.iniciar_sesion_btn);

        iniciarSesionBtn.setOnClickListener(v->{
            Intent intentInicarSesion=new Intent(getApplicationContext(), LoginPaciente.class);
            startActivity(intentInicarSesion);
        });
    }
}