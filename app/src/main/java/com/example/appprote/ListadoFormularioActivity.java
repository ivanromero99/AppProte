package com.example.appprote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.appprote.model.Formulario;
import com.example.appprote.model.Perro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.appprote.MainActivity.databaseReference;

public class ListadoFormularioActivity extends AppCompatActivity {

    List<Formulario> listaFormulario = new ArrayList<Formulario>();
    ArrayAdapter<Formulario> arrayAdapterFormulario;
    ListView list_Formulario;
    static Formulario formularioSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_formulario);


        list_Formulario = findViewById(R.id.listadoFormulario);
        formularioSeleccionado = null;

        list_Formulario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Formulario f = listaFormulario.get(position);

                formularioSeleccionado = f;

                Intent intent = new Intent(ListadoFormularioActivity.this, FormularioVerActivity.class);
                startActivity(intent);

            }
        });

        listarDatos();
    }

    private void listarDatos() {
        databaseReference.child("Formulario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaFormulario.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Formulario f = objSnaptshot.getValue(Formulario.class);
                    if(f.getPerro().getModulo().equals(MainActivity.getUsuarioLogueado().getModulo())) {
                        listaFormulario.add(f);
                    }
                }
                arrayAdapterFormulario = new ArrayAdapter<Formulario>(ListadoFormularioActivity.this, android.R.layout.simple_list_item_1, listaFormulario);
                list_Formulario.setAdapter(arrayAdapterFormulario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static Formulario getFormularioSeleccionado() {
        return formularioSeleccionado;
    }

    public void volver(View view) {
        Intent intent = new Intent(ListadoFormularioActivity.this, VerPerroActivity.class);
        startActivity(intent);
    }

}