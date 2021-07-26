package cr.ac.ucr.expedientesaluddigital;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.adapters.ListaAlergiasAdapter;
import cr.ac.ucr.expedientesaluddigital.models.Alergia;
import cr.ac.ucr.expedientesaluddigital.models.Alergias;
import cr.ac.ucr.expedientesaluddigital.models.Paciente;

public class ListaAlergias extends AppCompatActivity {
    List<Alergia> c= Alergias.getAlergias();
    Paciente p= Paciente.getPaciente();
    private RecyclerView listaCitasRecycler;
    private ListaAlergiasAdapter adapter;
    private TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_citas);
        titulo=findViewById(R.id.lista_citas_titulo);
        titulo.setText("Alergias");
        listaCitasRecycler=findViewById(R.id.lista_citas_recycler);
        adapter=new ListaAlergiasAdapter(this);
        listaCitasRecycler.setAdapter(adapter);
        listaCitasRecycler.setHasFixedSize(true);
        listaCitasRecycler.setLayoutManager(new LinearLayoutManager(this));
        if(!(c.size()>0)){
            titulo.setText("No hay registros para este usuario");
        }
    }


}
