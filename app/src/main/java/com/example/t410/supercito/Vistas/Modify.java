package com.example.t410.supercito.Vistas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.t410.supercito.Datos.Producto;
import com.example.t410.supercito.Datos.ProductoCRUD;
import com.example.t410.supercito.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Modify extends AppCompatActivity {
    private ProductoCRUD crud;
    private ImageView ivFoto;
    private String indice;
    private String nombre;
    private String precio;
    private String url;
    private EditText edNombre;
    private EditText edPrecio;
    private TextView tv;
    private Button bTomar, bSeleccionar;

    private static final int SELECT_PHOTO = 100;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int REQUEST_CAMERA = 200;
    private static final String FILE_PROVIDER = "com.example.t410.supercito.fileprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        crud = new ProductoCRUD(this);
        indice = getIntent().getStringExtra("indice");
        nombre = getIntent().getStringExtra("nombre");
        precio = getIntent().getStringExtra("precio");
        url = getIntent().getStringExtra("url");

        ivFoto = (ImageView) findViewById(R.id.ivFoto2);
        edNombre = (EditText) findViewById(R.id.et1);
        edPrecio = (EditText) findViewById(R.id.et2);
        tv = (TextView)findViewById(R.id.tvUrl2);

        edNombre.setText(nombre);
        edPrecio.setText(precio);
        tv.setText(url);

        bTomar = (Button) findViewById(R.id.bTomar2);
        bSeleccionar = (Button) findViewById(R.id.bSeleccionar2);

        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .into(ivFoto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fbOk = (FloatingActionButton) findViewById(R.id.fb_ok);
        FloatingActionButton fbDel = (FloatingActionButton) findViewById(R.id.fb_del);

        bSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 4.- Mandamos a validar permisos
                validarPermisosStorage();
            }
        });

        bTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 10.- Mandamos a validar permisos
                validarPermisosCamara();
            }
        });

        fbOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edNombre.getText().toString().equals("")||edPrecio.getText().toString().equals("")||edPrecio.getText().toString().equals("0")){
                    Toast.makeText(Modify.this, "Debes rellenar todos los campos y el precio no puede ser 0: ", Toast.LENGTH_SHORT).show();
                }else {
                    Producto item = new Producto(indice, edNombre.getText().toString(), edPrecio.getText().toString(), tv.getText().toString());
                    crud.updateProducto(item);
                    Toast.makeText(Modify.this, "Producto actualizado", Toast.LENGTH_SHORT).show();
                    Intent act = new Intent(Modify.this, MainActivity.class);
                    startActivity(act);
                }
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

    public void validarPermisosStorage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Debemos mostrar un mensaje?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Mostramos una explicación de que no aceptó dar permiso para acceder a la librería
            } else {
                // Pedimos permiso
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            }
        }else{
            iniciarIntentSeleccionarFotos();
        }
    }

    private void iniciarIntentSeleccionarFotos() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    //TODO: 7.- Revisamos si se le dio permiso al usuario o no
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                // Si la petición se cancela se regresa un arreglo vacío
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido
                    iniciarIntentSeleccionarFotos();
                } else {
                    // Permiso negado
                }
                return;
            // TODO 14.- Validamos el permiso para el acceso a la cámara
            case REQUEST_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Se concedió acceso
                    iniciarIntentTomarFoto();
                } else {
                    // permiso negado
                }
                return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            //TODO: 8.- Si obtuvimos una imagen entonces la procesamos
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri imagenSeleccionada = imageReturnedIntent.getData();
                    try{
                        InputStream imagenStream = getContentResolver().openInputStream(imagenSeleccionada);
                        Bitmap imagen = BitmapFactory.decodeStream(imagenStream);
                        ivFoto.setImageBitmap(imagen);
                        tv.setText(imagenSeleccionada.toString());

                    }catch (FileNotFoundException fnte){
                        Toast.makeText(this, fnte.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                    return;
                }
                else {
                    tv.setText("");
                }
                // TODO 15.- Si obtuvimos la imagen y la guardamos la mostramos
            case REQUEST_CAMERA:
                if(resultCode == RESULT_OK){
                    Picasso.with(this).load(tv.getText().toString()).into(ivFoto);
                }
                return;
        }
    }

    //TODO: 11.- Validamos permisos de cámara
    public void validarPermisosCamara() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            }
        }else{
            iniciarIntentTomarFoto();
        }
    }

    //TODO: 12.- Iniciamos intent para tomar foto
    private  void iniciarIntentTomarFoto(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Validamos que hay una actividad de cámara
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // Creamos un nuevo objeto para almacenar la foto
            File photoFile = null;
            Uri photoURI;
            try {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT){
                    photoFile = createImageFile();
                    if (photoFile != null) {
                        photoURI = Uri.fromFile(photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(cameraIntent, REQUEST_CAMERA);
                    }
                } else {
                    photoFile = createImageFile2();
                    if (photoFile != null) {
                        photoURI = FileProvider.getUriForFile(this, FILE_PROVIDER, photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(cameraIntent, REQUEST_CAMERA);
                    }
                }
            }catch (NullPointerException npe){
                Toast.makeText(this,"Error en file provider",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO: 13.- Función para crear archivo y URL
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen = "JPEG_" + timeStamp + "_";
        File imagesFolder = new File( Environment.getExternalStorageDirectory(), "Supercito");
        if (!imagesFolder.exists()) {
            imagesFolder.mkdirs();
        }
        File image = new File(imagesFolder, nombreImagen+".jpg");
        String urlName = "file://" + image.getAbsolutePath();
        tv.setText(urlName);
        return image;
    }

    private File createImageFile2() throws IOException {
        // Creamos el archivo
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                nombreImagen,  /* prefijp */
                ".jpg",         /* sufijo */
                storageDir      /* directorio */
        );


        // Obtenemos la URL
        String urlName = "file://" + image.getAbsolutePath();
        tv.setText(urlName);
        return image;
    }
}


