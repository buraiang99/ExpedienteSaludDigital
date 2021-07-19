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

import cr.ac.ucr.expedientesaluddigital.InfoAlergia;
import cr.ac.ucr.expedientesaluddigital.R;
import cr.ac.ucr.expedientesaluddigital.models.Alergia;
import cr.ac.ucr.expedientesaluddigital.models.Alergias;

public class ListaAlergiasAdapter extends RecyclerView.Adapter<ListaAlergiasAdapter.ViewHolder>{

    private List<Alergia> alergias;
    private Context context;
    private View.OnClickListener listener;

    public ListaAlergiasAdapter(Context context) {
        this.alergias = Alergias.getInstance(null);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_alergia, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Alergia p = alergias.get(position);
        holder.nombretv.setText(p.getNombreAlergia());
        holder.fechatv.setText(p.getFechaDiagnostico());
        holder.id=position;

        holder.setOnclickListener(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public int id;
        public ConstraintLayout item;
        public TextView fechatv;
        public TextView nombretv;

        public ViewHolder(View itemView) {
            super(itemView);
            fechatv = (TextView) itemView.findViewById(R.id.fecha_diagnostico_alergia_tv);
            nombretv = (TextView) itemView.findViewById(R.id.nombre_alergia_tv);
            item=(ConstraintLayout)itemView.findViewById(R.id.item_alergia_xml);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), InfoAlergia.class);
            intent.putExtra("idAlergia", this.id);
            view.getContext().startActivity(intent);
        }

        public void setOnclickListener(int numeroPokemon){
            item.setOnClickListener(this);
            this.id = numeroPokemon;
        }
    }

    @Override
    public int getItemCount() {
        return alergias.size();
    }
}
