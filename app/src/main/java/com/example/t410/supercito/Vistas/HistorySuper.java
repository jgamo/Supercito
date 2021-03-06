package com.example.t410.supercito.Vistas;

import android.content.Intent;
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

import com.example.t410.supercito.Adaptadores.HistorialAdapter;
import com.example.t410.supercito.Adaptadores.RVAdapter;
import com.example.t410.supercito.Adaptadores.RecyclerViewClickListener;
import com.example.t410.supercito.Datos.Historial;
import com.example.t410.supercito.Datos.HistorialCRUD;
import com.example.t410.supercito.Datos.Producto;
import com.example.t410.supercito.Datos.ProductoCRUD;
import com.example.t410.supercito.R;

import java.util.ArrayList;

public class HistorySuper extends AppCompatActivity {

    private RecyclerViewClickListener listener;
    private RecyclerView rv;
    private ArrayList<Historial> history;
    private HistorialCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_super);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        history = new ArrayList<Historial>();
        crud = new HistorialCRUD(this);
        history = crud.getHistorial();

        rv=(RecyclerView)findViewById(R.id.rv3);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeAdapter();
    }

    private void initializeAdapter(){
        HistorialAdapter adapter = new HistorialAdapter(history,this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
               Intent act = new Intent(HistorySuper.this, DetallesCompra.class);
                act.putExtra("id_compra", history.get(position).getId_compra());
                startActivity(act);
            }
        });
        rv.setAdapter(adapter);
    }
}
