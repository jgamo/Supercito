package com.example.t410.supercito.Adaptadores;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.t410.supercito.Datos.Compra;
import com.example.t410.supercito.Datos.Historial;
import com.example.t410.supercito.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by T410 on 19/09/2017.
 */

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>{
    private Context context;
    private RecyclerViewClickListener listener;

    ArrayList<Historial> historial;

    public HistorialAdapter(ArrayList<Historial> historial, Context context, RecyclerViewClickListener listener){
        this.historial = historial;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public HistorialViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item3, viewGroup, false);
        HistorialViewHolder pvh = new HistorialViewHolder(v);
        return new RowViewHolder3(v, listener);
    }

    @Override
    public void onBindViewHolder(HistorialViewHolder holder, int position) {
        String date = historial.get(position).getFecha();
        String sub1 = date.substring(0,2);
        String sub2 = date.substring(2,4);
        String sub3 = date.substring(4,8);
        String f = "Compra: "+sub1+"/"+sub2+"/"+sub3;
        holder.fecha.setText(f);
        holder.compraDescript.setText(historial.get(position).getCantidad()+" elementos");
        holder.compraTotal.setText("$"+historial.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return historial.size();
    }

    public static class HistorialViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView fecha;
        TextView compraDescript;
        TextView compraTotal;

        HistorialViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv3);
            fecha = (TextView)itemView.findViewById(R.id.fecha);
            compraDescript = (TextView)itemView.findViewById(R.id.compra_description);
            compraTotal = (TextView)itemView.findViewById(R.id.compra_total);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
