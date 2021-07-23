package cr.ac.ucr.expedientesaluddigital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cr.ac.ucr.expedientesaluddigital.interfaces.CantonAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.DistritoAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.DomicilioAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.PacienteAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.ProvinciaAPI;
import cr.ac.ucr.expedientesaluddigital.models.Canton;
import cr.ac.ucr.expedientesaluddigital.models.Distrito;
import cr.ac.ucr.expedientesaluddigital.models.Domicilio;
import cr.ac.ucr.expedientesaluddigital.models.Paciente;
import cr.ac.ucr.expedientesaluddigital.models.Provincia;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarPaciente extends AppCompatActivity {

    Spinner spinner;
    Spinner spinnerCanton;
    Spinner spinnerDistrito;
    Spinner spinnerTipoSangre;
    Spinner spinnerEstadoCivil;

    private TextInputEditText tietCedula;
    private TextInputEditText tietNombre;
    private EditText etEdad;
    private TextInputEditText tietDireccion;
    private EditText etPasword;
    private TextView tvPrueba;
    private TextInputEditText tietNumero;

    private Button btnRegistrar;

    private DomicilioAPI domicilioAPI;

    List<Provincia> provincias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        spinner = findViewById(R.id.sProvincia);
        spinnerCanton = findViewById(R.id.sCanton);
        spinnerDistrito = findViewById(R.id.sDistrito);
        spinnerTipoSangre = findViewById(R.id.sTipoSangre);
        spinnerEstadoCivil = findViewById(R.id.sEstadoCivil);

        tietCedula = findViewById(R.id.tietCedula);
        tietNombre = findViewById(R.id.tietNombre);
        tietDireccion = findViewById(R.id.tietDireccion);
        tietNumero = findViewById(R.id.tietNumeroRegistrar);

        etEdad = findViewById(R.id.etnEdad);
        etPasword = findViewById(R.id.etpPasword);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        llenarSangreCivil();
        llenarProvincias();
        llenarCanton();
        llenarDistrito();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registrando", Toast.LENGTH_LONG).show();
                registarPaciente();
            }
        });
    } // fin onCreate

    private void registarPaciente() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        PacienteAPI pacienteAPI = retrofit.create(PacienteAPI.class);

        if (tietCedula.length() == 0 && tietNombre.length() == 0 && etEdad.length() == 0 && etPasword.length() == 0 && tietDireccion.length() == 0) {
            Toast.makeText(this, "Todos los campos debe estar llenos", Toast.LENGTH_LONG).show();
        } else {

            registarDomicilio();
            Paciente paciente = new Paciente();
            paciente.setCedula(tietCedula.getText().toString());
            paciente.setNombre(tietNombre.getText().toString());
            paciente.setEdad(Integer.parseInt(etEdad.getText().toString()));
            paciente.setEstadoCivil((String) spinnerEstadoCivil.getSelectedItem());
            paciente.setPass(etPasword.getText().toString());
            paciente.setTipoSangre((String) spinnerTipoSangre.getSelectedItem());
            paciente.setDomicilio("vacio");
            paciente.setNumero(tietNumero.getText().toString());

            //Toast.makeText(this, paciente.toString(), Toast.LENGTH_SHORT).show();

            registarDomicilio();

            Call<Paciente> pacienteCall = pacienteAPI.insertarPaciente(paciente);
            pacienteCall.enqueue(new Callback<Paciente>() {
                @Override
                public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                    tvPrueba.setText("Code: "+response.code());
                    return;
                }

                @Override
                public void onFailure(Call<Paciente> call, Throwable t) {

                }
            });
            Toast.makeText(getApplicationContext(), "Registrado correctamente", Toast.LENGTH_LONG).show();
            Intent intentPaginaPrincipal = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentPaginaPrincipal);
        }
    }//fin registrarPaciente

    protected void llenarSangreCivil() {
        ArrayList<String> arrayListTipoSangre = new ArrayList<>();
        ArrayList<String> arrayListEstadoCivil = new ArrayList<>();

        arrayListTipoSangre.add("A+");
        arrayListTipoSangre.add("B+");
        arrayListTipoSangre.add("O+");
        arrayListTipoSangre.add("AB+");
        arrayListTipoSangre.add("A-");
        arrayListTipoSangre.add("B-");
        arrayListTipoSangre.add("O-");
        arrayListTipoSangre.add("AB-");

        arrayListEstadoCivil.add("Casado");
        arrayListEstadoCivil.add("Soltero");
        arrayListEstadoCivil.add("Divorciado");
        arrayListEstadoCivil.add("Viudo");

        spinnerEstadoCivil.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayListEstadoCivil));
        spinnerTipoSangre.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayListTipoSangre));
    }//fin llenarSangreCivil

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
                spinner.setAdapter(new ArrayAdapter<Provincia>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayListTemp));
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

    protected void registarDomicilio() {

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        domicilioAPI = retrofit.create(DomicilioAPI.class);

        Provincia provincia = (Provincia) spinner.getSelectedItem();
        Canton canton = (Canton) spinnerCanton.getSelectedItem();
        Distrito distrito = (Distrito) spinnerDistrito.getSelectedItem();

        Domicilio domicilio = new Domicilio();
        domicilio.setIdProvincia(provincia.getId());
        domicilio.setIdCanton(canton.getId());
        domicilio.setIdDistrito(distrito.getId());
        domicilio.setDetalles(tietDireccion.getText().toString());

        Call<Domicilio> domicilioCall = domicilioAPI.insertarDomicilio(domicilio);
        Toast.makeText(getApplicationContext(), "Registrado correctamente", Toast.LENGTH_LONG).show();
        domicilioCall.enqueue(new Callback<Domicilio>() {
            @Override
            public void onResponse(Call<Domicilio> call, Response<Domicilio> response) {
                if (!response.isSuccessful()) {
                    //Toast.makeText(getApplicationContext(), "Code: ", Toast.LENGTH_LONG).show();
                    /*//tvPrueba.setText("Code: "+response.code());
                    Domicilio domicilioResponce = response.body();
                    String content = "";
                    content+= "Code: "+response.code()+"\n";
                    content += "ID Provincia: "+ domicilioResponce.getIdProvincia()+"\n";
                    content += "ID Canton: "+domicilioResponce.getIdCanton()+"\n";
                    tvPrueba.setText(content);*/
                    return;
                }
            }

            @Override
            public void onFailure(Call<Domicilio> call, Throwable t) {
                tvPrueba.setText(t.toString());
            }
        });
    }// fin
} // fin class
