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
import com.example.t410.supercito.Datos.Producto;
import com.example.t410.supercito.R;

import java.util.ArrayList;

/**
 * Created by T410 on 19/09/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ProductViewHolder>{
    private Context context;
    private RecyclerViewClickListener listener;

    ArrayList<Producto> product;

    public RVAdapter(ArrayList<Producto> product, Context context, RecyclerViewClickListener listener){
        this.product = product;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return new RowViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.productName.setText(product.get(position).getNombre());
        holder.productPrice.setText("$"+product.get(position).getPrecio());
            Glide.with(context)
                    .load(product.get(position).getUrl())
                    .crossFade()
                    .centerCrop()
                    .into(holder.productUrl);
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView productName;
        TextView productPrice;
        ImageView productUrl;

        ProductViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            productName = (TextView)itemView.findViewById(R.id.person_name);
            productPrice = (TextView)itemView.findViewById(R.id.person_age);
            productUrl = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
