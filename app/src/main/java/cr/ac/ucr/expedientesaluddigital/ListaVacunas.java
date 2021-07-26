package cr.ac.ucr.expedientesaluddigital;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.adapters.ListaVacunasAdapter;
import cr.ac.ucr.expedientesaluddigital.models.Paciente;
import cr.ac.ucr.expedientesaluddigital.models.Vacuna;
import cr.ac.ucr.expedientesaluddigital.models.Vacunas;

public class ListaVacunas extends AppCompatActivity {
    List<Vacuna> c= Vacunas.getVacunas();
    Paciente p= Paciente.getPaciente();
    private RecyclerView listaVacunasRecycler;
    private ListaVacunasAdapter adapter;
    private TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_citas);
        titulo=findViewById(R.id.lista_citas_titulo);
        titulo.setText("Vacunas");
        listaVacunasRecycler=findViewById(R.id.lista_citas_recycler);
        adapter=new ListaVacunasAdapter(this);
        listaVacunasRecycler.setAdapter(adapter);
        listaVacunasRecycler.setHasFixedSize(true);
        listaVacunasRecycler.setLayoutManager(new LinearLayoutManager(this));
        if(!(c.size()>0)){
            titulo.setText("No hay registros para este usuario");
        }
    }


}
