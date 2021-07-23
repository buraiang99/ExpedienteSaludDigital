package cr.ac.ucr.expedientesaluddigital;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import cr.ac.ucr.expedientesaluddigital.interfaces.DomicilioAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.PacienteAPI;
import cr.ac.ucr.expedientesaluddigital.interfaces.ProvinciaAPI;
import cr.ac.ucr.expedientesaluddigital.models.Canton;
import cr.ac.ucr.expedientesaluddigital.models.Distrito;
import cr.ac.ucr.expedientesaluddigital.models.Domicilio;
import cr.ac.ucr.expedientesaluddigital.models.Paciente;
import cr.ac.ucr.expedientesaluddigital.models.Provincia;
import cr.ac.ucr.expedientesaluddigital.models.RespuestaDomicilio;
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
    private TextView textViewDireccion;

    private TextInputEditText textInputEditTextTelefono;
    private TextInputEditText textInputEditTextDireccion;

    private Spinner spinnerEstadoCivil;
    private Spinner spinnerProvincia;
    private Spinner spinnerCanton;
    private Spinner spinnerDistrito;

    private Button buttonActualizar;

    private Paciente paciente;
    private RespuestaDomicilio respuestaDomicilio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_editar_datos);

        paciente = Paciente.getInstance(null);

        textViewCedula = findViewById(R.id.tvCedula);
        textViewNombre = findViewById(R.id.tvNombreActualizar);
        textViewEdad = findViewById(R.id.tvEdadRegistrar);
        textViewTipoSangre = findViewById(R.id.tvTipoSangre);
        textViewEstadoCivil = findViewById(R.id.tvEstadoCivil);
        textViewTelefono = findViewById(R.id.tvTelefono);
        textViewProvincia = findViewById(R.id.textViewProvincia);
        textViewCanton = findViewById(R.id.textViewCanton);
        textViewDistrito = findViewById(R.id.textViewDistrito);
        textViewDireccion = findViewById(R.id.textViewDireccion);

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
        llenarDatos();

        buttonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Actualizando", Toast.LENGTH_LONG).show();
                actualizarPaciente();
            }
        });
    }// fin onCreate

    protected  void actualizarPaciente() {
        Paciente pacienteTemp;
        RespuestaDomicilio domicilioTemp = respuestaDomicilio;

        Provincia provincia = (Provincia) spinnerProvincia.getSelectedItem();
        Canton canton = (Canton) spinnerCanton.getSelectedItem();
        Distrito distrito = (Distrito) spinnerDistrito.getSelectedItem();
        String estadoCivil = (String) spinnerEstadoCivil.getSelectedItem();

        pacienteTemp = paciente;
        if (textInputEditTextTelefono.length() != 0) {
            pacienteTemp.setNumero(textInputEditTextTelefono.getText().toString());
        }
        if (!estadoCivil.equals("Estado Civil")) {
            pacienteTemp.setEstadoCivil((String) spinnerEstadoCivil.getSelectedItem());
        }
        if (textInputEditTextDireccion.length() != 0) {
            domicilioTemp.setDetalles(textInputEditTextDireccion.getText().toString());
        }
        if (provincia.getId() != 0) {
            domicilioTemp.setProvinciaNombre(provincia.getNombre());
        }
        if (canton.getId() != 0) {
            domicilioTemp.setCantonNombre(canton.getNombre());
        }
        if (distrito.getId() != 0) {
            domicilioTemp.setDistritoNombre(distrito.getNombre());
        }
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        PacienteAPI pacienteAPI = retrofit.create(PacienteAPI.class);
        Call<Paciente> pacienteCall = pacienteAPI.actualizar(pacienteTemp.getCedula(), pacienteTemp);
        pacienteCall.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                Toast.makeText(getApplicationContext(), "Code:"+response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
                Log.d("Mensaje",t.toString());
            }
        });

        DomicilioAPI domicilioAPI = retrofit.create(DomicilioAPI.class);
        Call<RespuestaDomicilio> domicilioCall = domicilioAPI.actualizar(pacienteTemp.getCedula(), domicilioTemp);
        domicilioCall.enqueue(new Callback<RespuestaDomicilio>() {
            @Override
            public void onResponse(Call<RespuestaDomicilio> call, Response<RespuestaDomicilio> response) {
                Toast.makeText(getApplicationContext(), "Code:"+response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RespuestaDomicilio> call, Throwable t) {
                Log.d("Mensaje",t.toString());
            }
        });

        Toast.makeText(getApplicationContext(), "Actualizado", Toast.LENGTH_LONG).show();
        Intent intentMenuPrincipal = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(intentMenuPrincipal);
    } // fin actualizarPaciente

    protected void llenarDatos() {

        textViewCedula.setText(paciente.getCedula());
        textViewNombre.setText(paciente.getNombre());
        textViewEdad.setText(String.valueOf(paciente.getEdad()));
        textViewTipoSangre.setText(paciente.getTipoSangre());
        textViewEstadoCivil.setText(paciente.getEstadoCivil());
        textViewTelefono.setText(paciente.getNumero());

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://gerardo42-001-site1.gtempurl.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        DomicilioAPI domicilioAPI = retrofit.create(DomicilioAPI.class);
        Call<RespuestaDomicilio> call = domicilioAPI.buscarDomicilio(paciente.getCedula());
        call.enqueue(new Callback<RespuestaDomicilio>() {
            @Override
            public void onResponse(Call<RespuestaDomicilio> call, Response<RespuestaDomicilio> response) {
                RespuestaDomicilio respuestaDomiciliotemp = response.body();
                respuestaDomicilio = respuestaDomiciliotemp;
                textViewProvincia.setText(respuestaDomicilio.getProvinciaNombre());
                textViewCanton.setText(respuestaDomicilio.getCantonNombre());
                textViewDistrito.setText(respuestaDomicilio.getDistritoNombre());
                textViewDireccion.setText(respuestaDomicilio.getDetalles());
            }

            @Override
            public void onFailure(Call<RespuestaDomicilio> call, Throwable t) {

            }
        });
    }// fin llenarDatos

    protected void llenarEstadoCivil() {

        ArrayList<String> arrayListEstadoCivil = new ArrayList<>();
        arrayListEstadoCivil.add("Casado");
        arrayListEstadoCivil.add("Soltero");
        arrayListEstadoCivil.add("Divorciado");
        arrayListEstadoCivil.add("Viudo");

        arrayListEstadoCivil.add(0,"Estado Civil");

        spinnerEstadoCivil.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayListEstadoCivil));
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
                    Toast.makeText(getApplicationContext(), "Code:"+response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Provincia> provinciaList = response.body();
                arrayListTemp.addAll(provinciaList);
                /*for (Provincia provincia:provinciaList) {
                    arrayListTemp.add(provincia);
                }
                provinciasListTemp = arrayListTemp;*/
                //Toast.makeText(getApplicationContext(), paciente.getNombre(), Toast.LENGTH_SHORT).show();
                Provincia provinciaTemp = new Provincia(0,"Provincias");
                arrayListTemp.add(0, provinciaTemp); //* Agrega como primer elemento del List.

                spinnerProvincia.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayListTemp));
            }

            @Override
            public void onFailure(Call<List<Provincia>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problemas al obtener provincias", Toast.LENGTH_SHORT).show();
            }
        }); //fin callback
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
                //cantonArrayList = cantonesList.toArray(cantonArrayList);
                cantonArrayList.addAll(cantonesList);
                /*for (Canton canton : cantonesList) {
                    cantonArrayList.add(canton);
                }*/
                //cantonArrayListTemp = cantonArrayList;
                cantonArrayList.add(0,new Canton(0,"Cantones"));
                spinnerCanton.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, cantonArrayList));
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
                distritoArrayList.addAll(distritoList);
                /*for(Distrito distrito : distritoList) {
                    distritoArrayList.add(distrito);
                }
                distritoArrayListTemp = distritoArrayList;*/
                distritoArrayList.add(0,new Distrito(0,"Distritos"));
                spinnerDistrito.setAdapter(new ArrayAdapter<Distrito>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, distritoArrayList));
            }

            @Override
            public void onFailure(Call<List<Distrito>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problemas al obtener los distritos", Toast.LENGTH_SHORT).show();
            }
        });
    }// fin llenarDistrito

}
