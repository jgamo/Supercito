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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.t410.supercito.Adaptadores.ComprasAdapter;
import com.example.t410.supercito.Adaptadores.RVAdapter;
import com.example.t410.supercito.Adaptadores.RecyclerViewClickListener;
import com.example.t410.supercito.Adaptadores.SpinnerAdapter;
import com.example.t410.supercito.Datos.Compra;
import com.example.t410.supercito.Datos.ComprasCRUD;
import com.example.t410.supercito.Datos.Historial;
import com.example.t410.supercito.Datos.HistorialCRUD;
import com.example.t410.supercito.Datos.Producto;
import com.example.t410.supercito.Datos.ProductoCRUD;
import com.example.t410.supercito.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CurrentSuper extends AppCompatActivity {

    ArrayList<Producto> productos, penus;
    ArrayList<Compra> compras;
    ProductoCRUD crud;
    HistorialCRUD crud2;
    ComprasCRUD crud3;
    Spinner sp;
    EditText et;
    ComprasAdapter adapter;
    RecyclerView rv;
    int cant, tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_super);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cant=0;
        tot=0;
        rv=(RecyclerView)findViewById(R.id.rv2);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        et = (EditText) findViewById(R.id.edCantidad);
        sp = (Spinner)findViewById(R.id.spinner);
        compras = new ArrayList<Compra>();
        productos = new ArrayList<Producto>();

        crud = new ProductoCRUD(this);
        crud2 = new HistorialCRUD(this);
        crud3 = new ComprasCRUD(this);
        productos = crud.getProductos();
        initializeAdapter();
        initializeAdapter2();

        Button btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (et.getText().toString().equals("")||et.getText().toString().equals("0")){
                    Toast.makeText(CurrentSuper.this, "Debes seleccionar la cantidad: ", Toast.LENGTH_SHORT).show();
                }else {
                    Producto p = (Producto) sp.getSelectedItem();
                    cant++;
                    int cantidad = Integer.parseInt(et.getText().toString());
                    int total = cantidad * Integer.parseInt(p.getPrecio());
                    tot=tot+total;
                    compras.add(new Compra(Integer.parseInt(p.getId()),p.getNombre(),Integer.parseInt(p.getPrecio()),cantidad,total));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(CurrentSuper.this, "Elemento agregado ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        FloatingActionButton fbSave = (FloatingActionButton) findViewById(R.id.fb_save);
        fbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
                long id = crud2.newHistorial(new Historial(timeStamp, cant,tot));
                for (int i = 0; i < compras.size(); i++) {
                    compras.get(i).setIdCompra(id);
                    crud3.newCompra(compras.get(i));
                }
                Intent act = new Intent(CurrentSuper.this,HistorySuper.class);
                startActivity(act);
                Toast.makeText(CurrentSuper.this, "Compra registrada con exito ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeAdapter(){
        SpinnerAdapter adapter = new SpinnerAdapter(this,productos);
        sp.setAdapter(adapter);
    }

    private void initializeAdapter2(){
        adapter = new ComprasAdapter(compras,this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        });
        rv.setAdapter(adapter);
    }
}
