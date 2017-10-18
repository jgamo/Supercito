package com.example.t410.supercito.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t410.supercito.Datos.Producto;
import com.example.t410.supercito.Datos.ProductoCRUD;
import com.example.t410.supercito.R;

public class Modify extends AppCompatActivity {
    private ProductoCRUD crud;

    private String indice;
    private String nombre;
    private String precio;
    private String url;
    private EditText edNombre;
    private EditText edPrecio;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        crud = new ProductoCRUD(this);
        indice = getIntent().getStringExtra("indice");
        nombre = getIntent().getStringExtra("nombre");
        precio = getIntent().getStringExtra("precio");
        url = getIntent().getStringExtra("url");

        edNombre = (EditText) findViewById(R.id.et1);
        edPrecio = (EditText) findViewById(R.id.et2);
        tv = (TextView)findViewById(R.id.tvUrl2);

        edNombre.setText(nombre);
        edPrecio.setText(precio);
        tv.setText(url);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fbOk = (FloatingActionButton) findViewById(R.id.fb_ok);
        FloatingActionButton fbDel = (FloatingActionButton) findViewById(R.id.fb_del);

        fbOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto item = new Producto(indice,edNombre.getText().toString(),edPrecio.getText().toString(),tv.getText().toString());
                crud.updateProducto(item);
                Toast.makeText(Modify.this, "Producto actualizado", Toast.LENGTH_SHORT).show();
                Intent act = new Intent(Modify.this, MainActivity.class);
                startActivity(act);
            }
        });

        fbDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto item = new Producto(indice,nombre,precio,url);
                crud.deleteProducto(item);
                Toast.makeText(Modify.this, "Producto eliminado ", Toast.LENGTH_SHORT).show();
                Intent act = new Intent(Modify.this, MainActivity.class);
                startActivity(act);
            }
        });
    }
}
