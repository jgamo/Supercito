/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.example.t410.supercito.Adaptadores;

import android.view.View;

// TODO: 8.- Creamos una nueva clase que extienda un ViewHolder e implemente un onClickListener

public class RowViewHolder2 extends ComprasAdapter.CompraViewHolder implements View.OnClickListener {

    private RecyclerViewClickListener listener;

    public RowViewHolder2(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        this.listener = listener;
        // TODO: 9.- Asignamos a nuestro itemView el evento listener de la clase que está implementando
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO: 10.- Cuando se de click el evento onClick de nuestra interfaz va a tomar la acción
        listener.onClick(view, getAdapterPosition());
    }
}
