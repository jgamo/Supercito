package com.example.t410.supercito.Adaptadores;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.t410.supercito.Datos.Compra;
import com.example.t410.supercito.Datos.Producto;
import com.example.t410.supercito.R;

import java.util.ArrayList;

/**
 * Created by T410 on 19/09/2017.
 */

public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.CompraViewHolder>{
    private Context context;
    private RecyclerViewClickListener listener;

    ArrayList<Compra> compra;

    public ComprasAdapter(ArrayList<Compra> compra, Context context, RecyclerViewClickListener listener){
        this.compra = compra;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public CompraViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item2, viewGroup, false);
        CompraViewHolder pvh = new CompraViewHolder(v);
        return new RowViewHolder2(v, listener);
    }

    @Override
    public void onBindViewHolder(CompraViewHolder holder, int position) {
        holder.productName.setText(compra.get(position).getName());
        holder.productDescript.setText(compra.get(position).getCantidad()+" de "+compra.get(position).getPrecio());
        holder.productTotal.setText("$"+compra.get(position).getTotal());
        Log.d("animal","Total: "+compra.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return compra.size();
    }

    public static class CompraViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView productName;
        TextView productDescript;
        TextView productTotal;

        CompraViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv2);
            productName = (TextView)itemView.findViewById(R.id.product_name);
            productDescript = (TextView)itemView.findViewById(R.id.product_description);
            productTotal = (TextView)itemView.findViewById(R.id.product_total);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
