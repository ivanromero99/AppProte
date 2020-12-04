package com.example.appprote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appprote.model.Perro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.appprote.MainActivity.databaseReference;

public class ListadoActivity extends AppCompatActivity {

    List<Perro> listaPerros = new ArrayList<Perro>();
    ArrayAdapter<Perro> arrayAdapterPerro;
    ListView list_Perros;
    private static Perro perroSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        FloatingActionButton boton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        if(MainActivity.getUsuarioLogueado()== null || (!MainActivity.getUsuarioLogueado().getModulo().equals(ModuloActivity.getModuloSeleccionado()))) {
            boton.hide();
            if(MainActivity.getUsuarioLogueado()!= null && MainActivity.getUsuarioLogueado().isAdmin()){
                boton.show();
            }
        } else {
            boton.show();
        }

        list_Perros = findViewById(R.id.listado_Perros);
        perroSeleccionado = null;

        list_Perros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Perro p = listaPerros.get(position);

                perroSeleccionado = p;

                Intent intent = new Intent(ListadoActivity.this, VerPerroActivity.class);
                startActivity(intent);

            }
        });

        listarDatos();
    }




    private void listarDatos() {
        databaseReference.child("Perro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaPerros.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Perro p = objSnaptshot.getValue(Perro.class);
                    if(p.getModulo().equals(ModuloActivity.getModuloSeleccionado())){
                        listaPerros.add(p);
                    }
                }
                arrayAdapterPerro = new ArrayAdapter<Perro>(ListadoActivity.this, android.R.layout.simple_list_item_1, listaPerros);
                list_Perros.setAdapter(arrayAdapterPerro);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void crearPerro(View view) {
        Intent intent = new Intent(ListadoActivity.this, CrearActivity.class);
        startActivity(intent);
    }

    public void volver(View view) {
        Intent intent = new Intent(ListadoActivity.this, ModuloActivity.class);
        startActivity(intent);
    }

    public static Perro getPerroSeleccionado() {
        return perroSeleccionado;
    }

    public static void setPerroSeleccionado(Perro perroSeleccionado) {
        ListadoActivity.perroSeleccionado = perroSeleccionado;
    }
}