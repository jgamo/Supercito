package com.example.t410.supercito.Vistas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.t410.supercito.Adaptadores.ComprasAdapter;
import com.example.t410.supercito.Adaptadores.HistorialAdapter;
import com.example.t410.supercito.Adaptadores.RecyclerViewClickListener;
import com.example.t410.supercito.Datos.Compra;
import com.example.t410.supercito.Datos.ComprasCRUD;
import com.example.t410.supercito.Datos.Historial;
import com.example.t410.supercito.Datos.HistorialCRUD;
import com.example.t410.supercito.R;

import java.util.ArrayList;

public class DetallesCompra extends AppCompatActivity {

    private RecyclerViewClickListener listener;
    private RecyclerView rv;
    private ArrayList<Compra> detalles;
    private ComprasCRUD crud;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_compras);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getIntExtra("id_compra",0);

        detalles = new ArrayList<Compra>();
        crud = new ComprasCRUD(this);
        detalles = crud.getCompras();

        rv=(RecyclerView)findViewById(R.id.rv4);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeAdapter();
    }

    private void initializeAdapter(){
        ArrayList<Compra> auxiliar = new ArrayList<Compra>();
        for (int i = 0; i < detalles.size(); i++) {
            if (detalles.get(i).getIdCompra()==id){
                auxiliar.add(detalles.get(i));
            }
        }
        ComprasAdapter adapter = new ComprasAdapter(auxiliar,this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        });
        rv.setAdapter(adapter);
    }
}
