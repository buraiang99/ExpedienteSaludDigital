package cr.ac.ucr.expedientesaluddigital;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.expedientesaluddigital.adapters.ListaCitasAdapter;
import cr.ac.ucr.expedientesaluddigital.models.Cita;
import cr.ac.ucr.expedientesaluddigital.models.Citas;
import cr.ac.ucr.expedientesaluddigital.models.Paciente;

public class ListaCitas extends AppCompatActivity {
    List<Cita> c= Citas.getCitas();
    Paciente p= Paciente.getPaciente();
    private RecyclerView listaCitasRecycler;
    private List<Citas> citas =new ArrayList<>();
    private ListaCitasAdapter adapter;
    private TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_citas);
        titulo=findViewById(R.id.lista_citas_titulo);
        titulo.setText("Citas");
        listaCitasRecycler=findViewById(R.id.lista_citas_recycler);
        adapter=new ListaCitasAdapter(this);
        listaCitasRecycler.setAdapter(adapter);
        listaCitasRecycler.setHasFixedSize(true);
        listaCitasRecycler.setLayoutManager(new LinearLayoutManager(this));
        if(!(c.size()>0)){
            titulo.setText("No hay registros para este usuario");
        }
    }


}
