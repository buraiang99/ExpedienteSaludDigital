package cr.ac.ucr.expedientesaluddigital.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.expedientesaluddigital.InfoCita;
import cr.ac.ucr.expedientesaluddigital.R;
import cr.ac.ucr.expedientesaluddigital.models.Cita;
import cr.ac.ucr.expedientesaluddigital.models.Citas;

public class ListaCitasAdapter extends RecyclerView.Adapter<ListaCitasAdapter.ViewHolder>{

    private List<Cita> citas;
    private Context context;

    public ListaCitasAdapter(Context context) {
        this.citas = Citas.getInstance(null);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_cita, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cita p = citas.get(position);
        holder.fechatv.setText(p.getFecha());
        holder.horatv.setText(p.getHora());
        holder.centroSaludtv.setText(String.valueOf(p.getCentroSalud()));
        holder.id=p.getiD_Cita();

        holder.setOnclickListener(p.getiD_Cita());
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public int id;
        public TextView fechatv;
        public TextView horatv;
        public TextView centroSaludtv;

        public ViewHolder(View itemView) {
            super(itemView);
            fechatv = (TextView) itemView.findViewById(R.id.fecha_tv);
            horatv = (TextView) itemView.findViewById(R.id.hora_tv);
            centroSaludtv = (TextView) itemView.findViewById(R.id.centro_salud_tv);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), InfoCita.class);
            intent.putExtra("idPokemon", this.id);
            view.getContext().startActivity(intent);
        }

        public void setOnclickListener(int numeroPokemon){
            this.id = numeroPokemon;
        }
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }
}
