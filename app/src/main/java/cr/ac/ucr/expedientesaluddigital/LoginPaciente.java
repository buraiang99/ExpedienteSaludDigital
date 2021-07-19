package cr.ac.ucr.expedientesaluddigital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.interfaces.AlergiasCedulaAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.CitasCedulaAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.PacienteAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.VacunaCedulaAPI;
import cr.ac.ucr.expedientesaluddigital.models.Alergia;
import cr.ac.ucr.expedientesaluddigital.models.Alergias;
import cr.ac.ucr.expedientesaluddigital.models.Cita;
import cr.ac.ucr.expedientesaluddigital.models.Citas;
import cr.ac.ucr.expedientesaluddigital.models.Paciente;
import cr.ac.ucr.expedientesaluddigital.models.Vacuna;
import cr.ac.ucr.expedientesaluddigital.models.Vacunas;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
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
                        Paciente.getInstance(p);
                        if(p.getPass().equals(passTxt.getText().toString())){
                            obtenerCitas(codigo);
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
    private void obtenerCitas(String codigo){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        CitasCedulaAPI cita=retrofit.create(CitasCedulaAPI.class);
        Call<List<Cita>> call=cita.find(codigo);
        call.enqueue(new Callback<List<Cita>>() {
            @Override
            public void onResponse(Call<List<Cita>> call, Response<List<Cita>> response) {
                try {
                    if(response.isSuccessful()){
                        List<Cita> citas=response.body();
                        Citas.getInstance(citas);
                        obtenerVacunas(codigo);
                    }
                }catch (Exception ex){
                    Toast.makeText(LoginPaciente.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cita>> call, Throwable t) {

            }
        });
    }
    private void obtenerVacunas(String codigo){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        VacunaCedulaAPI vacuna=retrofit.create(VacunaCedulaAPI.class);
        Call<List<Vacuna>> call=vacuna.find(codigo);
        call.enqueue(new Callback<List<Vacuna>>() {
            @Override
            public void onResponse(Call<List<Vacuna>> call, Response<List<Vacuna>> response) {
                try {
                    if(response.isSuccessful()){
                        List<Vacuna> vacunas=response.body();
                        Vacunas.getInstance(vacunas);
                        obtenerAlergias(codigo);
                    }
                }catch (Exception ex){
                    Toast.makeText(LoginPaciente.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vacuna>> call, Throwable t) {

            }
        });
    }
    private void obtenerAlergias(String codigo){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        AlergiasCedulaAPI alergia=retrofit.create(AlergiasCedulaAPI.class);
        Call<List<Alergia>> call=alergia.find(codigo);
        call.enqueue(new Callback<List<Alergia>>() {
            @Override
            public void onResponse(Call<List<Alergia>> call, Response<List<Alergia>> response) {
                try {
                    if(response.isSuccessful()){
                        List<Alergia> alergias=response.body();
                        Alergias.getInstance(alergias);
                        Intent intentInicarSesion=new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(intentInicarSesion);
                    }
                }catch (Exception ex){
                    Toast.makeText(LoginPaciente.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Alergia>> call, Throwable t) {

            }
        });
    }
}
