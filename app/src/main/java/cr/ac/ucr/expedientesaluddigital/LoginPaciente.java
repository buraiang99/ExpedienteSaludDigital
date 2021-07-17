package cr.ac.ucr.expedientesaluddigital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.expedientesaluddigital.interfaces.PacienteAPI;
import cr.ac.ucr.expedientesaluddigital.models.Paciente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPaciente extends AppCompatActivity {
    EditText cedulaTxt;
    EditText passTxt;
    TextView pruebatv;
    Button loginBtn;
    Paciente TEMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        TEMP=new Paciente();
        cedulaTxt=findViewById(R.id.cedula_txt);
        passTxt=findViewById(R.id.pass_txt);
        pruebatv=findViewById(R.id.prueba_tv);
        loginBtn=findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                obtener(cedulaTxt.getText().toString());
            }
        });
    }

    private void obtener(String codigo){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        PacienteAPI paciente=retrofit.create(PacienteAPI.class);
        Call<Paciente> call=paciente.find(codigo);
        call.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                try {
                    if(response.isSuccessful()){
                        Paciente p=response.body();
                        TEMP=p;
                        if(p.getPass().equals(passTxt.getText().toString())){
                            Intent intentInicarSesion=new Intent(getApplicationContext(), LoginPaciente.class);
                            startActivity(intentInicarSesion);
                        }else{
                            pruebatv.setText("Datos erroneos");
                        }
                    }
                }catch (Exception ex){
                    Toast.makeText(LoginPaciente.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {

            }
        });
    }
}
