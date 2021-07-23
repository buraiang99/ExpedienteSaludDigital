package cr.ac.ucr.expedientesaluddigital;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.expedientesaluddigital.interfaces.CantonAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.DistritoAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.ProvinciaAPI;
import cr.ac.ucr.expedientesaluddigital.models.Canton;
import cr.ac.ucr.expedientesaluddigital.models.Distrito;
import cr.ac.ucr.expedientesaluddigital.models.Paciente;
import cr.ac.ucr.expedientesaluddigital.models.Provincia;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActualizarPaciente extends AppCompatActivity {

    private TextView textViewCedula;
    private TextView textViewNombre;
    private TextView textViewEdad;
    private TextView textViewTipoSangre;
    private TextView textViewEstadoCivil;
    private TextView textViewTelefono;
    private TextView textViewProvincia;
    private TextView textViewCanton;
    private TextView textViewDistrito;

    private TextInputEditText textInputEditTextTelefono;
    private TextInputEditText textInputEditTextDireccion;

    private Spinner spinnerEstadoCivil;
    private Spinner spinnerProvincia;
    private Spinner spinnerCanton;
    private Spinner spinnerDistrito;

    private Button buttonActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_editar_datos);

        textViewCedula = findViewById(R.id.tvCedula);
        textViewNombre = findViewById(R.id.tvNombreActualizar);
        textViewEdad = findViewById(R.id.tvEdad);
        textViewTipoSangre = findViewById(R.id.tvTipoSangre);
        textViewEstadoCivil = findViewById(R.id.tvEstadoCivil);
        textViewTelefono = findViewById(R.id.tvTelefono);
        textViewProvincia = findViewById(R.id.textViewProvincia);
        textViewCanton = findViewById(R.id.textViewCanton);
        textViewDistrito = findViewById(R.id.textViewDistrito);

        textInputEditTextTelefono = findViewById(R.id.textIputEditTextTelefono);
        textInputEditTextDireccion = findViewById(R.id.textIputEditTextDireccion);

        spinnerEstadoCivil = findViewById(R.id.spinnerEstadoCivil);
        spinnerProvincia = findViewById(R.id.spinnerProvincia);
        spinnerCanton = findViewById(R.id.spinnerCanton);
        spinnerDistrito = findViewById(R.id.spinnerDistrito);

        buttonActualizar = findViewById(R.id.buttonActualizar);

        llenarEstadoCivil();
        llenarProvincias();
        llenarCanton();
        llenarDistrito();

    }// fin onCreate

    protected void llenarDatos() {


    }// fin llenarDatos

    protected void llenarEstadoCivil() {

        ArrayList<String> arrayListEstadoCivil = new ArrayList<>();
        arrayListEstadoCivil.add("Casado");
        arrayListEstadoCivil.add("Soltero");
        arrayListEstadoCivil.add("Divorciado");
        arrayListEstadoCivil.add("Viudo");

        spinnerEstadoCivil.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayListEstadoCivil));
    }// fin llenarEstadoCivil

    protected void llenarProvincias(){

        ArrayList<Provincia> arrayListTemp = new ArrayList<>();

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ProvinciaAPI provinciaAPI = retrofit.create(ProvinciaAPI.class);

        Call<List<Provincia>> listCall = provinciaAPI.getProvincia();

        listCall.enqueue(new Callback<List<Provincia>>() {
            @Override
            public void onResponse(Call<List<Provincia>> call, Response<List<Provincia>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Problemas al obtener provincias", Toast.LENGTH_SHORT).show();
                }
                List<Provincia> provinciaList = response.body();

                for (Provincia provincia:provinciaList) {
                    arrayListTemp.add(provincia);
                }
                Paciente paciente = Paciente.getInstance(null);
                Toast.makeText(getApplicationContext(), paciente.getNombre(), Toast.LENGTH_SHORT).show();
                spinnerProvincia.setAdapter(new ArrayAdapter<Provincia>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayListTemp));
            }

            @Override
            public void onFailure(Call<List<Provincia>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problemas al obtener provincias", Toast.LENGTH_SHORT).show();
            }
        });
    } // fin llenarProvincias

    protected void llenarCanton() {
        ArrayList<Canton> cantonArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CantonAPI cantonAPI = retrofit.create(CantonAPI.class);

        Call<List<Canton>> listCall = cantonAPI.getCanton();

        listCall.enqueue(new Callback<List<Canton>>() {
            @Override
            public void onResponse(Call<List<Canton>> call, Response<List<Canton>> response) {
                List<Canton> cantonesList = response.body();
                for (Canton canton : cantonesList) {
                    cantonArrayList.add(canton);
                }
                spinnerCanton.setAdapter(new ArrayAdapter<Canton>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, cantonArrayList));
            }
            @Override
            public void onFailure(Call<List<Canton>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problemas al obtener los cantones", Toast.LENGTH_SHORT).show();
            }
        });
    } // fin llenarCanton

    protected void llenarDistrito() {
        ArrayList<Distrito> distritoArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        DistritoAPI distritoAPI = retrofit.create(DistritoAPI.class);

        Call<List<Distrito>> listCall = distritoAPI.getDistrito();

        listCall.enqueue(new Callback<List<Distrito>>() {
            @Override
            public void onResponse(Call<List<Distrito>> call, Response<List<Distrito>> response) {
                List<Distrito> distritoList = response.body();

                for(Distrito distrito : distritoList) {
                    distritoArrayList.add(distrito);
                }
                spinnerDistrito.setAdapter(new ArrayAdapter<Distrito>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, distritoArrayList));
            }

            @Override
            public void onFailure(Call<List<Distrito>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problemas al obtener los distritos", Toast.LENGTH_SHORT).show();
            }
        });
    }// fin llenarDistrito
}
