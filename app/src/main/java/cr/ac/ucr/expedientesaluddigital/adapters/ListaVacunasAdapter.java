package cr.ac.ucr.expedientesaluddigital.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.InfoVacuna;
import cr.ac.ucr.expedientesaluddigital.R;
import cr.ac.ucr.expedientesaluddigital.models.Vacuna;
import cr.ac.ucr.expedientesaluddigital.models.Vacunas;

public class ListaVacunasAdapter extends RecyclerView.Adapter<ListaVacunasAdapter.ViewHolder>{

    private List<Vacuna> vacunas;
    private Context context;
    private View.OnClickListener listener;

    public ListaVacunasAdapter(Context context) {
        this.vacunas = Vacunas.getInstance(null);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_vacuna, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vacuna p = vacunas.get(position);
        holder.fechatv.setText(p.getFechaAplicacion());
        holder.horatv.setText(p.getNombreVacuna());
        holder.centroSaludtv.setText(p.getCentroSalud());
        holder.id=position;

        holder.setOnclickListener(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public int id;
        public ConstraintLayout item;
        public TextView fechatv;
        public TextView horatv;
        public TextView centroSaludtv;

        public ViewHolder(View itemView) {
            super(itemView);
            fechatv = (TextView) itemView.findViewById(R.id.fecha_vacuna_tv);
            horatv = (TextView) itemView.findViewById(R.id.nombre_vacuna_tv);
            centroSaludtv = (TextView) itemView.findViewById(R.id.centro_salud_vacuna_tv);
            item=(ConstraintLayout)itemView.findViewById(R.id.item_vacuna_xml);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), InfoVacuna.class);
            intent.putExtra("idVacuna", this.id);
            view.getContext().startActivity(intent);
        }

        public void setOnclickListener(int numeroPokemon){
            item.setOnClickListener(this);
            this.id = numeroPokemon;
        }
    }

    @Override
    public int getItemCount() {
        return vacunas.size();
    }
}
