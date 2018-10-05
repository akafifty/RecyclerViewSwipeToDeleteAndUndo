package rvstd.iso.com.recyclerviewswipetodelete;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.DataObjectHolder> {

    private Context context;
    private ArrayList<Coche> listaCoches;

    public RViewAdapter(Context context, ArrayList<Coche> listaCoches) {
        this.context = context;
        this.listaCoches = listaCoches;
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView txtNombre;
        public RelativeLayout layoutABorrar;

        public DataObjectHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img);
            this.txtNombre = itemView.findViewById(R.id.txtNombre);
            this.layoutABorrar = itemView.findViewById(R.id.layoutABorrar);
        }
    }

    public void removeItem(int position){
        listaCoches.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Coche coche,int position){
        listaCoches.add(position, coche);
        notifyItemInserted(position);
    }


    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_view, viewGroup, false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataObjectHolder holder, int position) {

        holder.txtNombre.setText(listaCoches.get(position).getNombre());

        Glide.with(context).load(listaCoches.get(position).getImg()).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Position: " +
                        holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCoches.size();
    }

}
