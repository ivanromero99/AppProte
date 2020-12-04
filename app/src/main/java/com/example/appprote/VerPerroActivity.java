package com.example.appprote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appprote.model.Perro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import static com.example.appprote.MainActivity.databaseReference;

public class VerPerroActivity extends AppCompatActivity {

    ImageView fotoPerro;
    TextView nombre, raza, edad, sexo, descripcion, modulo;
    private Bitmap fotoCargada;
    private String url;
    boolean borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perro);
        borrar = false;
        FloatingActionButton boton = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        if(MainActivity.getUsuarioLogueado()== null || (!MainActivity.getUsuarioLogueado().getModulo().equals(ModuloActivity.getModuloSeleccionado()))) {
            boton.hide();
            if(MainActivity.getUsuarioLogueado()!= null && MainActivity.getUsuarioLogueado().isAdmin()){
                boton.show();
            }
        } else {
            boton.show();
        }

        nombre = (TextView) findViewById(R.id.perroSeleccionadoNombre);
        raza = (TextView) findViewById(R.id.perroSeleccionadoRaza);
        edad = (TextView) findViewById(R.id.perroSeleccionadoEdad);
        sexo = (TextView) findViewById(R.id.perroSeleccionadoSexo);
        descripcion = (TextView) findViewById(R.id.perroSeleccionadoDescripcion);
        modulo = (TextView) findViewById(R.id.perroSeleccionadoModulo);

        nombre.setText(ListadoActivity.getPerroSeleccionado().getNombre());
        raza.setText(ListadoActivity.getPerroSeleccionado().getRaza());
        edad.setText(Integer.toString(ListadoActivity.getPerroSeleccionado().getEdad()));
        sexo.setText(ListadoActivity.getPerroSeleccionado().getSexo());
        descripcion.setText(ListadoActivity.getPerroSeleccionado().getDescripcion());
        modulo.setText(ListadoActivity.getPerroSeleccionado().getModulo());

        url = ListadoActivity.getPerroSeleccionado().getUrl();

        fotoPerro = (ImageView) findViewById(R.id.verFotoPerro);

        if(url.equals("")){
            url= "https://www.protectoramalaga.com/images/logotipo.png";
        }
        Picasso.get().load(url).into(fotoPerro);


    }

    public void borrarPerro(View view) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle(R.string.tituloEmergente);
        dialogo1.setMessage(R.string.mensajeEmergente);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        dialogo1.show();

    }
    public void aceptar() {
        Toast t=Toast.makeText(this,R.string.borrado, Toast.LENGTH_SHORT);
        databaseReference.child("Perro").child(ListadoActivity.getPerroSeleccionado().getId()).removeValue();
        Intent intent = new Intent(VerPerroActivity.this, ListadoActivity.class);
        startActivity(intent);
        t.show();
    }

    public void irFormulario(View view) {

        Intent intent;
        if(MainActivity.getUsuarioLogueado()!=null && MainActivity.getUsuarioLogueado().getModulo().equals(ListadoActivity.getPerroSeleccionado().getModulo())){
            intent = new Intent(VerPerroActivity.this, ListadoFormularioActivity.class);
        } else {
            intent = new Intent(VerPerroActivity.this, FormularioActivity.class);
        }
        startActivity(intent);
    }

    public void volver(View view) {
        Intent intent = new Intent(VerPerroActivity.this, ListadoActivity.class);
        startActivity(intent);
    }
}