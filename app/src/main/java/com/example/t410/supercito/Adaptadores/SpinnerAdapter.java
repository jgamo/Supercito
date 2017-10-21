package com.example.t410.supercito.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t410.supercito.Datos.Producto;
import com.example.t410.supercito.R;

import java.util.ArrayList;

/**
 * Created by T410 on 18/10/2017.
 */

public class SpinnerAdapter extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Producto> items;

    public SpinnerAdapter (Activity activity, ArrayList<Producto> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Producto> producto) {
        for (int i = 0; i < producto.size(); i++) {
            items.add(producto.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.spinner, null);
        }

        Producto dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.spinner_name);
        title.setText(dir.getNombre());

        return v;
    }
}
